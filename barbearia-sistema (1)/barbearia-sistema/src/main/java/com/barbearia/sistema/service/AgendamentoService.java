package com.barbearia.sistema.service;

import com.barbearia.sistema.model.Agendamento;
import com.barbearia.sistema.model.StatusAgendamento;
import com.barbearia.sistema.model.Usuario;
import com.barbearia.sistema.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAllByOrderByDataHoraDesc();
    }

    public List<Agendamento> listarPorUsuario(Usuario usuario) {
        return agendamentoRepository.findByUsuarioOrderByDataHoraDesc(usuario);
    }

    public Agendamento buscarPorId(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    public Agendamento salvar(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public void atualizarStatus(Long id, StatusAgendamento status) {
        Agendamento agendamento = buscarPorId(id);
        agendamento.setStatus(status);
        agendamentoRepository.save(agendamento);
    }

    public void cancelar(Long id) {
        atualizarStatus(id, StatusAgendamento.CANCELADO);
    }

    public void excluir(Long id) {
        agendamentoRepository.deleteById(id);
    }
}
