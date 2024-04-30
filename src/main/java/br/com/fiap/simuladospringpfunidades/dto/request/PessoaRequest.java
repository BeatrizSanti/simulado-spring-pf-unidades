package br.com.fiap.simuladospringpfunidades.dto.request;


import br.com.fiap.simuladospringpfunidades.entity.Tipo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PessoaRequest(

        @Size(min = 2, max = 255, message = "A quantidade de caracteres do nome deve estar entre")
        @NotNull(message = "O nome é campo obrigatório")
        String nome,

        @Size(min = 2, max = 255, message = "A quantidade de caracteres do nome deve estar entre")
        @NotNull(message = "O sobrenome é campo obrigatório")
        String sobrenome,

        @Email(message = "Email é inválido")
        @NotNull(message = "Email é campo obrigatório")
        String email,

        @PastOrPresent(message = "Não aceitamos data no futuro")
        @NotNull(message = "A data de nascimento é obrigatória")
        LocalDate nascimento,

        @NotNull(message = "Informe o tipo da pessoa")
        Tipo tipo,

        //@CPF
        @NotNull(message = "CPF não pode ser nulo")
        String cpf

) {
}
