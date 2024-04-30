package br.com.fiap.simuladospringpfunidades.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

/**
 * UK para garantir que não se tenha mais de uma pessoa com o mesmo e-mail.
 */
@Entity
@Table(name = "TB_PESSOA", uniqueConstraints = {
        @UniqueConstraint( name = "UK_EMAIL_PESSOA", columnNames = {"EMAIL_PESSOA"} ),
        @UniqueConstraint( name = "UK_CPF_PESSOA", columnNames = {"CPF_PESSOA"} )
})
public class Pessoa {

    @Id
    @SequenceGenerator(name = "SQ_PESSOA", sequenceName = "SQ_PESSOA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PESSOA")
    @Column(name = "ID_PESSOA")
    private Long id;

    @Column(name = "NM_PESSOA")
    private String nome;

    @Column(name = "SN_PESSOA")
    private String sobrenome;

    @Column(name = "EMAIL_PESSOA", nullable = false)
    private String email;

    @Column(name = "CPF_PESSOA")
    private String cpf;

    @Column(name = "DT_NASCIMENTO")
    private LocalDate nascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "TP_PESSOA", nullable = false)
    private Tipo tipo;

}
