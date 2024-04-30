package br.com.fiap.simuladospringpfunidades.dto.response;

import lombok.Builder;

@Builder
public record UsuarioResponse(

        Long id,
        PessoaResponse pessoa,
        String username
) {
}
