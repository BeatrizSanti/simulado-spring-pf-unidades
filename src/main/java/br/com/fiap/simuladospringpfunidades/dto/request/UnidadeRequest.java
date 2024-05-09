package br.com.fiap.simuladospringpfunidades.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UnidadeRequest(

        @Size(min = 5, max = 255)
        @NotNull(message = "Nome é obrigatório!")
        String nome,

        @Size(min = 2, max = 255)
        @NotNull(message = "Sigla é obrigatório!")
        String sigla,

        @Size(min = 5, max = 255)
        String descricao,

        @Valid
        AbstractRequest macro


) {
}
