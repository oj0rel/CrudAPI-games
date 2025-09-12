package com.senac.games.service;

import com.senac.games.dto.request.jogo.JogoDTORequest;
import com.senac.games.dto.response.jogo.JogoDTOResponse;
import com.senac.games.entity.Categoria;
import com.senac.games.entity.Jogo;
import com.senac.games.repository.CategoriaRepository;
import com.senac.games.repository.JogoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JogoService {
    private final JogoRepository jogoRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public JogoService(JogoRepository jogoRepository, CategoriaRepository categoriaRepository) {
        this.jogoRepository = jogoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public JogoDTOResponse criarJogo(JogoDTORequest jogoDTORequest) {
        Jogo jogo = new Jogo();
        jogo.setNome(jogoDTORequest.getNome());
        jogo.setStatus(jogoDTORequest.getStatus());

        Categoria categoria = categoriaRepository.findById(jogoDTORequest.getCategoriaId())
                .orElseThrow(() -> new RuntimeException(
                        "Categoria não encontrada para o id: " + jogoDTORequest.getCategoriaId()));

        jogo.setCategoria(categoria);
        Jogo jogoSalvo = jogoRepository.save(jogo);
        return modelMapper.map(jogoSalvo, JogoDTOResponse.class);
    }

    public List<JogoDTOResponse> listarJogos() {
        List<Jogo> jogos = jogoRepository.listarJogos();
        return jogos.stream()
                .map(jogo -> modelMapper.map(jogo, JogoDTOResponse.class))
                .collect(Collectors.toList());
    }

    public JogoDTOResponse listarPorJogoId(Integer jogoId) {
        Jogo jogo = jogoRepository.obterJogoPeloId(jogoId);
        return modelMapper.map(jogo, JogoDTOResponse.class);
    }
    @Transactional
    public void deletarPorJogoId(Integer jogoId) {
        if (!jogoRepository.existsById(jogoId)) {
            throw new EntityNotFoundException("Jogo com ID " + jogoId + " não encontrado");
        }
        jogoRepository.apagadoLogicoJogo(jogoId);
    }
    @Transactional
    public JogoDTOResponse editarPorJogoId(Integer jogoId, JogoDTORequest jogoDTORequest) {
        return jogoRepository.findById(jogoId)
                .map(jogoExistente -> {
                    // Atualiza apenas os campos que foram fornecidos no DTO
                    modelMapper.map(jogoDTORequest, jogoExistente);

                    // Se houver mudança na categoria, busca a nova categoria
                    if (jogoDTORequest.getCategoriaId() != null &&
                            !jogoDTORequest.getCategoriaId().equals(jogoExistente.getCategoria().getId())) {

                        Categoria novaCategoria = categoriaRepository.findById(jogoDTORequest.getCategoriaId())
                                .orElseThrow(() -> new EntityNotFoundException("Categoria com ID " + jogoDTORequest.getCategoriaId() + " não encontrada"));

                        jogoExistente.setCategoria(novaCategoria);
                    }

                    Jogo jogoAtualizado = jogoRepository.save(jogoExistente);
                    return modelMapper.map(jogoAtualizado, JogoDTOResponse.class);
                })
                .orElseThrow(() -> new EntityNotFoundException("Jogo não encontrado com id " + jogoId));
    }
}
