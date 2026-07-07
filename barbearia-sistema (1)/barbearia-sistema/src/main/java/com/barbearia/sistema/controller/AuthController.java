package com.barbearia.sistema.controller;

import com.barbearia.sistema.model.Usuario;
import com.barbearia.sistema.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registrar")
    public String formularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registrar";
    }

    @PostMapping("/registrar")
    public String registrar(@Valid @ModelAttribute("usuario") Usuario usuario,
                             BindingResult result,
                             Model model) {

        if (usuarioService.emailJaExiste(usuario.getEmail())) {
            result.rejectValue("email", "email.duplicado", "Este e-mail já está cadastrado");
        }

        if (result.hasErrors()) {
            return "registrar";
        }

        usuarioService.cadastrar(usuario);
        model.addAttribute("sucesso", true);
        return "login";
    }
}
