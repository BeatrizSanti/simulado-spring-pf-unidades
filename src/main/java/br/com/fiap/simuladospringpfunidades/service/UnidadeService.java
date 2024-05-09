package br.com.fiap.simuladospringpfunidades.service;

import br.com.fiap.simuladospringpfunidades.dto.request.UnidadeRequest;
import br.com.fiap.simuladospringpfunidades.dto.response.UnidadeResponse;
import br.com.fiap.simuladospringpfunidades.entity.Unidade;
import br.com.fiap.simuladospringpfunidades.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeService implements ServiceDTO<Unidade, UnidadeRequest, UnidadeResponse> {

    @Autowired
    private UnidadeRepository repo;

    @Override
    public Unidade toEntity(UnidadeRequest r) {

        var macro = this.findById( r.macro().id() );

        return Unidade.builder()
                .nome( r.nome() )
                .descricao( r.descricao() )
                .sigla( r.sigla() )
                .macro( macro )
                .build();

    }

    @Override
    public UnidadeResponse toResponse(Unidade e) {

        var macro = this.toResponse( e.getMacro() );

        return UnidadeResponse.builder()
                .id( e.getId() )
                .nome( e.getNome() )
                .descricao( e.getDescricao() )
                .sigla( e.getSigla() )
                .macro( macro )
                .build();


    }

    @Override
    public List<Unidade> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Unidade> findAll(Example<Unidade> example) {
        return repo.findAll( example );
    }

    @Override
    public Unidade findById(Long id) {
        return repo.findById( id ).orElse( null );
    }

    @Override
    public Unidade save(Unidade e) {
        return repo.save( e );
    }
}
