package com.barbearia.barberia.repository;

import com.barbearia.barberia.model.Agendamento;
import com.barbearia.barberia.model.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    boolean existsByBarbeiroAndDataHora(Barbeiro barbeiro, LocalDateTime dataHora);

    List<Agendamento> findByBarbeiroId(Long barbeiroId);

    List<Agendamento> findByClienteId(Long clienteId);
}