package com.senac.games.service;

import com.senac.games.dto.request.categoria.CategoriaDTORequest;
import com.senac.games.dto.request.participante.ParticipanteDTORequest;
import com.senac.games.dto.response.categoria.CategoriaDTOResponse;
import com.senac.games.entity.Categoria;
import com.senac.games.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


    public List<CategoriaDTOResponse> listarCategoriasAtivas(){
        List<Categoria> categorias = categoriaRepository.listarCategorias();
        return categorias.stream()
                .map(categoria -> modelMapper.map(categoria, CategoriaDTOResponse.class))
                .collect(Collectors.toList());
    }

    public CategoriaDTOResponse criarCategoria(CategoriaDTORequest categoriaDTORequest) {
        Categoria categoria = modelMapper.map(categoriaDTORequest, Categoria.class);
        Categoria categoriaSave = this.categoriaRepository.save(categoria);
        CategoriaDTOResponse categoriaDTOResponse = modelMapper.map(categoriaSave, CategoriaDTOResponse.class);
        return categoriaDTOResponse;

    }

    public void apagarCategoria(Integer categoriaId) {
        if (!categoriaRepository.existsById(categoriaId)) {
            throw new EntityNotFoundException("Categoria não encontrada com ID: " + categoriaId);
        }
        categoriaRepository.apagadoLogicoCategoria(categoriaId);
    }

    public CategoriaDTOResponse editarPorCategoriaId(Integer categoriaId, CategoriaDTORequest categoriaDTORequest) {
        return categoriaRepository.findById(categoriaId)
                .map(categoriaExistente -> {
                    // Atualiza apenas os campos que foram fornecidos no DTO
                    modelMapper.map(categoriaDTORequest, categoriaExistente);

                    Categoria categoriaAtualizado = categoriaRepository.save(categoriaExistente);
                    return modelMapper.map(categoriaAtualizado, CategoriaDTOResponse.class);
                })
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrado com id " + categoriaId));
    }

    public CategoriaDTOResponse listarPorCategoriaId(Integer categoriaId) {
        Categoria categoria = categoriaRepository.obterCategoriaPeloId(categoriaId);

        if (categoria == null) {
            throw new RuntimeException("Categoria não encontrada com ID: " + categoriaId);
        }

        return modelMapper.map(categoria, CategoriaDTOResponse.class);
    }
}
