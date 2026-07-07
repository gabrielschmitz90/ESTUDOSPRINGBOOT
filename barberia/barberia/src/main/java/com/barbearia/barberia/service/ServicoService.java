package com.barbearia.barberia.service;

import com.barbearia.barberia.model.Servico;
import com.barbearia.barberia.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository repository;

    public Servico criar(Servico servico) {
        return repository.save(servico);
    }

    public List<Servico> listarTodos() {
        return repository.findAll();
    }

    public Servico buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado"));
    }

    public Servico atualizar(Long id, Servico dadosAtualizados) {
        Servico servico = buscarPorId(id);
        servico.setNome(dadosAtualizados.getNome());
        servico.setPreco(dadosAtualizados.getPreco());
        servico.setDuracaoMinutos(dadosAtualizados.getDuracaoMinutos());
        return repository.save(servico);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}