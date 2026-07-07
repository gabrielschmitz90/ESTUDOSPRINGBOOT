package com.barbearia.sistema.controller;

import com.barbearia.sistema.model.Agendamento;
import com.barbearia.sistema.model.Barbeiro;
import com.barbearia.sistema.model.Servico;
import com.barbearia.sistema.model.StatusAgendamento;
import com.barbearia.sistema.service.AgendamentoService;
import com.barbearia.sistema.service.BarbeiroService;
import com.barbearia.sistema.service.ServicoService;
import com.barbearia.sistema.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final BarbeiroService barbeiroService;
    private final ServicoService servicoService;
    private final AgendamentoService agendamentoService;
    private final UsuarioService usuarioService;

    // ---------- Dashboard ----------

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("totalBarbeiros", barbeiroService.listarTodos().size());
        model.addAttribute("totalServicos", servicoService.listarTodos().size());
        model.addAttribute("totalAgendamentos", agendamentoService.listarTodos().size());
        model.addAttribute("totalUsuarios", usuarioService.listarTodos().size());
        return "admin/dashboard";
    }

    // ---------- Barbeiros ----------

    @GetMapping("/barbeiros")
    public String listarBarbeiros(Model model) {
        model.addAttribute("barbeiros", barbeiroService.listarTodos());
        return "admin/barbeiros";
    }

    @GetMapping("/barbeiros/novo")
    public String formularioNovoBarbeiro(Model model) {
        model.addAttribute("barbeiro", new Barbeiro());
        return "admin/barbeiro-form";
    }

    @GetMapping("/barbeiros/{id}/editar")
    public String formularioEditarBarbeiro(@PathVariable Long id, Model model) {
        model.addAttribute("barbeiro", barbeiroService.buscarPorId(id));
        return "admin/barbeiro-form";
    }

    @PostMapping("/barbeiros")
    public String salvarBarbeiro(@Valid @ModelAttribute Barbeiro barbeiro, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/barbeiro-form";
        }
        barbeiroService.salvar(barbeiro);
        return "redirect:/admin/barbeiros";
    }

    @PostMapping("/barbeiros/{id}/excluir")
    public String excluirBarbeiro(@PathVariable Long id) {
        barbeiroService.excluir(id);
        return "redirect:/admin/barbeiros";
    }

    // ---------- Serviços ----------

    @GetMapping("/servicos")
    public String listarServicos(Model model) {
        model.addAttribute("servicos", servicoService.listarTodos());
        return "admin/servicos";
    }

    @GetMapping("/servicos/novo")
    public String formularioNovoServico(Model model) {
        model.addAttribute("servico", new Servico());
        return "admin/servico-form";
    }

    @GetMapping("/servicos/{id}/editar")
    public String formularioEditarServico(@PathVariable Long id, Model model) {
        model.addAttribute("servico", servicoService.buscarPorId(id));
        return "admin/servico-form";
    }

    @PostMapping("/servicos")
    public String salvarServico(@Valid @ModelAttribute Servico servico, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/servico-form";
        }
        servicoService.salvar(servico);
        return "redirect:/admin/servicos";
    }

    @PostMapping("/servicos/{id}/excluir")
    public String excluirServico(@PathVariable Long id) {
        servicoService.excluir(id);
        return "redirect:/admin/servicos";
    }

    // ---------- Agendamentos (visão geral) ----------

    @GetMapping("/agendamentos")
    public String listarTodosAgendamentos(Model model) {
        model.addAttribute("agendamentos", agendamentoService.listarTodos());
        model.addAttribute("statusList", StatusAgendamento.values());
        return "admin/agendamentos";
    }

    @PostMapping("/agendamentos/{id}/status")
    public String atualizarStatus(@PathVariable Long id, @RequestParam StatusAgendamento status) {
        agendamentoService.atualizarStatus(id, status);
        return "redirect:/admin/agendamentos";
    }
}
