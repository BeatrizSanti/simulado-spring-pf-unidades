package br.com.fiap.simuladospringpfunidades.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UsuarioRequest(

        @Valid
        @NotNull(message = "É necessário informar os dados da pessoa")
        PessoaRequest pessoa,

        @Email(message = "Username deve ser um email válido")
        @NotNull(message = "Username não pode ser nulo")
        String username,


        /**
         * /^
         *   (?=.*\d)              // deve conter ao menos um dígito
         *   (?=.*[a-z])           // deve conter ao menos uma letra minúscula
         *   (?=.*[A-Z])           // deve conter ao menos uma letra maiúscula
         *   (?=.*[$*&@#])         // deve conter ao menos um caractere especial
         *   [0-9a-zA-Z$*&@#]{8,16}  // deve conter ao menos 8 dos caracteres mencionados e no máximo 16
         * $/
         */
      //  @Pattern(regexp = "/^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,16}$/")
        @NotNull(message = "O password não pode ser null")
        String password

) {
}
