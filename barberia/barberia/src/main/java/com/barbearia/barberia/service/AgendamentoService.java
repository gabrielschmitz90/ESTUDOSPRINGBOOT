package com.barbearia.barberia.service;

import com.barbearia.barberia.model.Agendamento;
import com.barbearia.barberia.model.Barbeiro;
import com.barbearia.barberia.model.Cliente;
import com.barbearia.barberia.model.Servico;
import com.barbearia.barberia.model.StatusAgendamento;
import com.barbearia.barberia.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository repository;
    private final ClienteService clienteService;
    private final BarbeiroService barbeiroService;
    private final ServicoService servicoService;

    public Agendamento criar(Agendamento agendamento) {
        Cliente cliente = clienteService.buscarPorId(agendamento.getCliente().getId());
        Barbeiro barbeiro = barbeiroService.buscarPorId(agendamento.getBarbeiro().getId());
        Servico servico = servicoService.buscarPorId(agendamento.getServico().getId());

        // Valida conflito de horário
        boolean conflito = repository.existsByBarbeiroAndDataHora(barbeiro, agendamento.getDataHora());
        if (conflito) {
            throw new IllegalArgumentException("Barbeiro já possui um agendamento neste horário");
        }

        agendamento.setCliente(cliente);
        agendamento.setBarbeiro(barbeiro);
        agendamento.setServico(servico);
        agendamento.setStatus(StatusAgendamento.AGENDADO);

        return repository.save(agendamento);
    }

    public List<Agendamento> listarTodos() {
        return repository.findAll();
    }

    public List<Agendamento> listarPorBarbeiro(Long barbeiroId) {
        return repository.findByBarbeiroId(barbeiroId);
    }

    public List<Agendamento> listarPorCliente(Long clienteId) {
        return repository.findByClienteId(clienteId);
    }

    public Agendamento buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));
    }

    public Agendamento atualizarStatus(Long id, StatusAgendamento novoStatus) {
        Agendamento agendamento = buscarPorId(id);
        agendamento.setStatus(novoStatus);
        return repository.save(agendamento);
    }

    public void cancelar(Long id) {
        Agendamento agendamento = buscarPorId(id);
        agendamento.setStatus(StatusAgendamento.CANCELADO);
        repository.save(agendamento);
    }
}