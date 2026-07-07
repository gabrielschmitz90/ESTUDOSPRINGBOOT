package com.barbearia.sistema.config;

import com.barbearia.sistema.model.*;
import com.barbearia.sistema.repository.BarbeiroRepository;
import com.barbearia.sistema.repository.ServicoRepository;
import com.barbearia.sistema.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Popula o banco (H2 em memória) com um usuário ADMIN padrão e alguns
 * barbeiros/serviços de exemplo, apenas para facilitar os testes.
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final BarbeiroRepository barbeiroRepository;
    private final ServicoRepository servicoRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setEmail("admin@barbearia.com");
            admin.setSenha(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            usuarioRepository.save(admin);

            Usuario cliente = new Usuario();
            cliente.setNome("Cliente Teste");
            cliente.setEmail("cliente@barbearia.com");
            cliente.setSenha(passwordEncoder.encode("cliente123"));
            cliente.setRole(Role.USUARIO);
            usuarioRepository.save(cliente);
        }

        if (barbeiroRepository.count() == 0) {
            barbeiroRepository.save(new Barbeiro(null, "Carlos Silva", "Cortes clássicos", true));
            barbeiroRepository.save(new Barbeiro(null, "João Pereira", "Barba e navalha", true));
            barbeiroRepository.save(new Barbeiro(null, "Marcos Souza", "Cortes modernos", true));
        }

        if (servicoRepository.count() == 0) {
            servicoRepository.save(new Servico(null, "Corte de Cabelo", "Corte tradicional na tesoura ou máquina", new BigDecimal("35.00"), 30, true));
            servicoRepository.save(new Servico(null, "Barba", "Barba feita com navalha e toalha quente", new BigDecimal("25.00"), 20, true));
            servicoRepository.save(new Servico(null, "Corte + Barba", "Combo completo", new BigDecimal("55.00"), 50, true));
            servicoRepository.save(new Servico(null, "Sobrancelha", "Design de sobrancelha na navalha", new BigDecimal("15.00"), 10, true));
        }
    }
}
