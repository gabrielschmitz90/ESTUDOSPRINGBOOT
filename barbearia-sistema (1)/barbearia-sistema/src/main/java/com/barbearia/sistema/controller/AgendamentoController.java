package com.barbearia.sistema.controller;

import com.barbearia.sistema.model.Agendamento;
import com.barbearia.sistema.model.Usuario;
import com.barbearia.sistema.model.dto.AgendamentoForm;
import com.barbearia.sistema.service.AgendamentoService;
import com.barbearia.sistema.service.BarbeiroService;
import com.barbearia.sistema.service.ServicoService;
import com.barbearia.sistema.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    private final BarbeiroService barbeiroService;
    private final ServicoService servicoService;
    private final UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model, Authentication authentication) {
        Usuario usuario = usuarioService.buscarPorEmail(authentication.getName());
        model.addAttribute("agendamentos", agendamentoService.listarPorUsuario(usuario));
        return "agendamentos/list";
    }

    @GetMapping("/novo")
    public String formularioNovo(Model model) {
        model.addAttribute("agendamentoForm", new AgendamentoForm());
        model.addAttribute("barbeiros", barbeiroService.listarAtivos());
        model.addAttribute("servicos", servicoService.listarAtivos());
        return "agendamentos/form";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("agendamentoForm") AgendamentoForm form,
                          BindingResult result,
                          Model model,
                          Authentication authentication) {

        if (result.hasErrors()) {
            model.addAttribute("barbeiros", barbeiroService.listarAtivos());
            model.addAttribute("servicos", servicoService.listarAtivos());
            return "agendamentos/form";
        }

        Usuario usuario = usuarioService.buscarPorEmail(authentication.getName());

        Agendamento agendamento = new Agendamento();
        agendamento.setUsuario(usuario);
        agendamento.setBarbeiro(barbeiroService.buscarPorId(form.getBarbeiroId()));
        agendamento.setServico(servicoService.buscarPorId(form.getServicoId()));
        agendamento.setDataHora(form.getDataHora());
        agendamento.setObservacoes(form.getObservacoes());

        agendamentoService.salvar(agendamento);
        return "redirect:/agendamentos";
    }

    @PostMapping("/{id}/cancelar")
    public String cancelar(@PathVariable Long id) {
        agendamentoService.cancelar(id);
        return "redirect:/agendamentos";
    }
}
