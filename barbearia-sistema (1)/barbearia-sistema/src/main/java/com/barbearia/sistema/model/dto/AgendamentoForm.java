package com.barbearia.sistema.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO usado no formulário Thymeleaf de criação de agendamento,
 * evitando expor a entidade JPA diretamente no binding do form.
 */
@Data
public class AgendamentoForm {

    @NotNull(message = "Selecione um barbeiro")
    private Long barbeiroId;

    @NotNull(message = "Selecione um serviço")
    private Long servicoId;

    @NotNull(message = "Informe a data e hora")
    private LocalDateTime dataHora;

    private String observacoes;
}
