package com.barbearia.sistema.repository;

import com.barbearia.sistema.model.Agendamento;
import com.barbearia.sistema.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByUsuarioOrderByDataHoraDesc(Usuario usuario);

    List<Agendamento> findAllByOrderByDataHoraDesc();
}
