package com.barbearia.barberia.service;

import com.barbearia.barberia.model.Barbeiro;
import com.barbearia.barberia.repository.BarbeiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarbeiroService {

    private final BarbeiroRepository repository;

    public Barbeiro criar(Barbeiro barbeiro) {
        return repository.save(barbeiro);
    }

    public List<Barbeiro> listarTodos() {
        return repository.findAll();
    }

    public Barbeiro buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Barbeiro não encontrado"));
    }

    public Barbeiro atualizar(Long id, Barbeiro dadosAtualizados) {
        Barbeiro barbeiro = buscarPorId(id);
        barbeiro.setNome(dadosAtualizados.getNome());
        barbeiro.setEspecialidade(dadosAtualizados.getEspecialidade());
        barbeiro.setHorarioInicio(dadosAtualizados.getHorarioInicio());
        barbeiro.setHorarioFim(dadosAtualizados.getHorarioFim());
        return repository.save(barbeiro);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}