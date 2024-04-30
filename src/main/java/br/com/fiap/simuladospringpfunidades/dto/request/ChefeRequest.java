package br.com.fiap.simuladospringpfunidades.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ChefeRequest(

        @NotNull(message = "Informe se é ou um chefe titular(false) substituto(true)")
        Boolean substituto,

        @Valid @NotNull(message = "É necessário informar os dados do Usuário")
        AbstractRequest usuario,

        @Valid @NotNull(message = "É necessário informar os dados da Unidade")
        AbstractRequest unidade,

        @NotNull(message = "A Data de inicio é necessária")
        LocalDateTime inicio,

        LocalDateTime fim

) {
}
