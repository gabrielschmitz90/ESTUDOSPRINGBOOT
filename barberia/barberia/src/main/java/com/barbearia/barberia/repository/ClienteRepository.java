package com.barbearia.barberia.repository;

import com.barbearia.barberia.model.Cliente;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Object> findByEmail(@Email(message = "Email inválido") String email);
}
