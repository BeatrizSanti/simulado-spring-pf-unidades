package br.com.fiap.simuladospringpfunidades.service;

import br.com.fiap.simuladospringpfunidades.dto.request.ChefeRequest;
import br.com.fiap.simuladospringpfunidades.dto.response.ChefeResponse;
import br.com.fiap.simuladospringpfunidades.entity.Chefe;
import br.com.fiap.simuladospringpfunidades.repository.ChefeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefeService implements ServiceDTO<Chefe, ChefeRequest, ChefeResponse> {

    @Autowired
    private ChefeRepository repo;

    @Autowired
    private UnidadeService unidadeService;

    @Autowired
    private UsuarioService usuarioService;


    @Override
    public Chefe toEntity(ChefeRequest r) {

        var unidade = unidadeService.findById( r.unidade().id() );
        var usuario = usuarioService.findById( r.usuario().id() );

        return Chefe.builder()
                .substituto( r.substituto() )
                .unidade( unidade )
                .usuario( usuario )
                .inicio( r.inicio() )
                .fim( r.fim() )
                .build();

    }

    @Override
    public ChefeResponse toResponse(Chefe e) {

        var unidade = unidadeService.toResponse( e.getUnidade() );
        var usuario = usuarioService.toResponse( e.getUsuario() );

        return ChefeResponse.builder()
                .substituto( e.getSubstituto() )
                .unidade( unidade )
                .usuario( usuario )
                .inicio( e.getInicio() )
                .fim( e.getFim() )
                .build();

    }

    @Override
    public List<Chefe> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Chefe> findAll(Example<Chefe> example) {
        return repo.findAll( example );
    }

    @Override
    public Chefe findById(Long id) {
        return repo.findById( id ).orElse( null );
    }

    @Override
    public Chefe save(Chefe e) {
        return repo.save( e );
    }
}
