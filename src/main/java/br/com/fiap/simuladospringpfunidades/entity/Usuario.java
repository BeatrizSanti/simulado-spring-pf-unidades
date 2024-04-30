package br.com.fiap.simuladospringpfunidades.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * UKs para garantir que não se tenha mais de um usuário
 * para a mesma pessoa e, que também,
 * não se possa ter mais de um usuário com o mesmo username
 */
@Entity
@Table(name = "TB_USUARIO", uniqueConstraints = {

        @UniqueConstraint(name = "UK_NAO_ACEITO_DOIS_USER_PARA_PESSOA", columnNames = "PESSOA"),
        @UniqueConstraint(name = "UK_NAO_ACEITO_DUAS_PESSOAS_COM_MESMO_USERNAME", columnNames = "USERNAME")

})
public class Usuario {

    @Id
    @SequenceGenerator(name = "SQ_USUARIO", sequenceName = "SQ_USUARIO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USUARIO")
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "PESSOA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(
                    name = "FK_USUARIO_PESSOA"
            )
    )
    private Pessoa pessoa;

}
