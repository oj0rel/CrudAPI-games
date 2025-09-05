package com.senac.games.service;

import com.senac.games.dto.request.jogo.JogoDTORequest;
import com.senac.games.dto.response.jogo.JogoDTOResponse;
import com.senac.games.entity.Categoria;
import com.senac.games.entity.Jogo;
import com.senac.games.repository.CategoriaRepository;
import com.senac.games.repository.JogoRepository;
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

    public List<JogoDTOResponse> listarJogos() {
        List<Jogo> jogos = jogoRepository.listarJogos();
        return jogos.stream()
                .map(jogo -> modelMapper.map(jogo, JogoDTOResponse.class))
                .collect(Collectors.toList());
    }


    public Jogo listarJogoPorId(Integer jogoId) {
        return this.jogoRepository.listarJogoPeloId(jogoId);
    }

    @Transactional
    public JogoDTOResponse criarJogo(JogoDTORequest jogoDTORequest) {

        Jogo jogo = new Jogo();
        jogo.setNome(jogoDTORequest.getNome());
        jogo.setStatus(jogoDTORequest.getStatus());

        Categoria categoria = categoriaRepository.findById(jogoDTORequest.getCategoriaId())
                .orElseThrow(() -> new RuntimeException(
                        "Categoria não encontrada para o ID:" + jogoDTORequest.getCategoriaId()
                ));

        jogo.setCategoria(categoria);
        Jogo jogoSalvo = jogoRepository.save(jogo);
        return modelMapper.map(jogoSalvo, JogoDTOResponse.class);
    }

    public JogoDTOResponse atualizarJogo(Integer jogoId, JogoDTORequest jogoDTORequest) {
        Jogo jogoBuscada = this.listarJogoPorId(jogoId);

        if (jogoBuscada != null) {
            modelMapper.map(jogoDTORequest, Jogo.class);
            Jogo tempJogo = jogoRepository.save(jogoBuscada);
            return modelMapper.map(tempJogo, JogoDTOResponse.class);
        } else {
            return null;
        }
    }

    public void apagarJogo(Integer jogoId) { jogoRepository.apagadoLogicoJogo(jogoId); }
}
