package br.com.fiap.simuladospringpfunidades.resource;

import br.com.fiap.simuladospringpfunidades.dto.request.UsuarioRequest;
import br.com.fiap.simuladospringpfunidades.dto.response.UsuarioResponse;
import br.com.fiap.simuladospringpfunidades.entity.Pessoa;
import br.com.fiap.simuladospringpfunidades.entity.Tipo;
import br.com.fiap.simuladospringpfunidades.entity.Usuario;
import br.com.fiap.simuladospringpfunidades.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource implements ResourceDTO<UsuarioRequest, UsuarioResponse> {

    @Autowired
    private UsuarioService service;


    @GetMapping
    public ResponseEntity<Collection<UsuarioResponse>> findAll(
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "pessoa.nome", required = false) String pessoaNome,
            @RequestParam(name = "pessoa.nascimento", required = false) LocalDate pessoaNascimento,
            @RequestParam(name = "pessoa.sobrenome", required = false) String pessoaSobrenome,
            @RequestParam(name = "pessoa.tipo", required = false) Tipo pessoaTipo,
            @RequestParam(name = "pessoa.email", required = false) String pessoaEmail,
            @RequestParam(name = "pessoa.cpf", required = false) String pessoaCPF
    ) {

        var pessoa = Pessoa.builder()
                .cpf( pessoaCPF )
                .tipo( pessoaTipo )
                .nome( pessoaNome )
                .sobrenome( pessoaSobrenome )
                .nascimento( pessoaNascimento )
                .email( pessoaEmail )
                .build();

        var usuario = Usuario.builder()
                .pessoa( pessoa )
                .username( username )
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<Usuario> example = Example.of( usuario, matcher );

        List<Usuario> usuarios = service.findAll( example );

        var response = usuarios.stream().map( service::toResponse ).toList();

        return ResponseEntity.ok( response );

    }


    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Long id) {
        var usuario = service.findById( id );
        if (usuario == null) return ResponseEntity.notFound().build();
        var response = service.toResponse( usuario );
        return ResponseEntity.ok( response );
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<UsuarioResponse> save(@RequestBody @Valid UsuarioRequest r) {
        var entity = service.toEntity( r );
        service.save( entity );
        var response = service.toResponse( entity );

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path( "/{id}" )
                .buildAndExpand( entity.getId() ).toUri();

        return ResponseEntity.created( uri ).body( response );
    }
}
