package com.senac.games.service;

import com.senac.games.dto.request.categoria.CategoriaDTORequest;
import com.senac.games.dto.request.participante.ParticipanteDTORequest;
import com.senac.games.dto.response.categoria.CategoriaDTOResponse;
import com.senac.games.entity.Categoria;
import com.senac.games.repository.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarCategorias() { return this.categoriaRepository.listarCategorias(); }

    public Categoria listarCategoriaPorId(Integer categoriaId) {
        return this.categoriaRepository.obterCategoriaPeloId(categoriaId);
    }

    public CategoriaDTOResponse criarCategoria(CategoriaDTORequest categoriaDTORequest) {
        Categoria categoria = modelMapper.map(categoriaDTORequest, Categoria.class);

        Categoria categoriaSave = this.categoriaRepository.save(categoria);

        CategoriaDTOResponse categoriaDTOResponse = modelMapper.map(categoriaSave, CategoriaDTOResponse.class);

        return categoriaDTOResponse;
    }

    public CategoriaDTOResponse atualizarCategoria(Integer categoriaId, CategoriaDTORequest categoriaDTORequest) {
        Categoria categoriaBuscada = this.listarCategoriaPorId(categoriaId);

        if (categoriaBuscada != null) {
            modelMapper.map(categoriaDTORequest, Categoria.class);
            Categoria tempCategoria = categoriaRepository.save(categoriaBuscada);
            return modelMapper.map(tempCategoria, CategoriaDTOResponse.class);
        } else {
            return null;
        }
    }

    public void apagarCategoria(Integer categoriaId) {
        categoriaRepository.apagadoLogicoCategoria(categoriaId);
    }
}
