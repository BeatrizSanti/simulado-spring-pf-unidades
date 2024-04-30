package br.com.fiap.simuladospringpfunidades.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "TB_CHEFE", uniqueConstraints = {

        /**
         * UK para garantir que não se tenha mais de um usuário (chefe), na mesma unidade com a data fim.
         * Ou seja, devemos evitar de ter para o mesmo usuário dois registros ativos de chefia para uma unidade.
         */

        @UniqueConstraint(name = "UK_CHEFIA_ATIVA", columnNames = {
                "USUARIO", "UNIDADE", "DT_FIM"
        })

})
public class Chefe {

    @Id
    @SequenceGenerator(name = "SQ_CHEFE", sequenceName = "SQ_CHEFE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CHEFE")
    @Column(name = "ID_CHEFE")
    private Long id;

    @Column(name = "SUBSTITUTO")
    private Boolean substituto;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "USUARIO",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(
                    name = "FK_USUARIO_CHEFE"
            )
    )
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "UNIDADE",
            referencedColumnName = "ID_UNIDADE",
            foreignKey = @ForeignKey(
                    name = "FK_UNIDADE_CEFE"
            )
    )
    private Unidade unidade;

    @Column(name = "DT_INICIO")
    private LocalDateTime inicio;

    @Column(name = "DT_FIM")
    private LocalDateTime fim;

}
