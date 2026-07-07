package com.barbearia.sistema.service;

import com.barbearia.sistema.model.Barbeiro;
import com.barbearia.sistema.repository.BarbeiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarbeiroService {

    private final BarbeiroRepository barbeiroRepository;

    public List<Barbeiro> listarTodos() {
        return barbeiroRepository.findAll();
    }

    public List<Barbeiro> listarAtivos() {
        return barbeiroRepository.findByAtivoTrue();
    }

    public Barbeiro buscarPorId(Long id) {
        return barbeiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));
    }

    public Barbeiro salvar(Barbeiro barbeiro) {
        return barbeiroRepository.save(barbeiro);
    }

    public void excluir(Long id) {
        barbeiroRepository.deleteById(id);
    }
}
