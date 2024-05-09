package br.com.fiap.simuladospringpfunidades.service;

import br.com.fiap.simuladospringpfunidades.dto.request.UsuarioRequest;
import br.com.fiap.simuladospringpfunidades.dto.response.UsuarioResponse;
import br.com.fiap.simuladospringpfunidades.entity.Usuario;
import br.com.fiap.simuladospringpfunidades.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService implements ServiceDTO<Usuario, UsuarioRequest, UsuarioResponse> {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private PessoaService pessoaService;

    @Override
    public Usuario toEntity(UsuarioRequest r) {

        if (Objects.isNull( r )) return null;

        var pessoa = pessoaService.toEntity( r.pessoa() );

        return Usuario.builder()
                .pessoa( pessoa )
                .username( r.username() )
                .password( r.password() )
                .build();
    }

    @Override
    public UsuarioResponse toResponse(Usuario e) {

        if (Objects.isNull( e )) return null;

        var pessoa = pessoaService.toResponse( e.getPessoa() );

        return UsuarioResponse.builder()
                .pessoa( pessoa )
                .username( e.getUsername() )
                .id( e.getId() )
                .build();
    }

    @Override
    public List<Usuario> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Usuario> findAll(Example<Usuario> example) {
        return repo.findAll( example );
    }

    @Override
    public Usuario findById(Long id) {
        return repo.findById( id ).orElse( null );
    }

    @Override
    public Usuario save(Usuario e) {
        return repo.save( e );
    }
}
