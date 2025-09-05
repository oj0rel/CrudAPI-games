package com.senac.games.service;

import com.senac.games.dto.request.jogo.JogoDTORequest;
import com.senac.games.dto.response.jogo.JogoDTOResponse;
import com.senac.games.entity.Jogo;
import com.senac.games.repository.JogoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {
    private final JogoRepository jogoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public JogoService(JogoRepository jogoRepository) { this.jogoRepository = jogoRepository; }

    public List<Jogo> listarJogos() { return this.jogoRepository.listarJogos(); }

    public Jogo listarJogoPorId(Integer jogoId) {
        return this.jogoRepository.listarJogoPeloId(jogoId);
    }

    public JogoDTOResponse criarJogo(JogoDTORequest jogoDTORequest) {
        Jogo jogo = modelMapper.map(jogoDTORequest, Jogo.class);

        Jogo jogoSave = this.jogoRepository.save(jogo);

        JogoDTOResponse jogoDTOResponse = modelMapper.map(jogoSave, JogoDTOResponse.class);

        return jogoDTOResponse;
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
