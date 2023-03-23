package br.com.starlymed.starlymed.service;

import br.com.starlymed.starlymed.orm.Endereco;
import br.com.starlymed.starlymed.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    public void cadastra(Endereco endereco){
        enderecoRepository.save(endereco);
    }

    public Optional<Endereco> getEnderecoById(Long id){
//        Endereco referenceById = enderecoRepository.getReferenceById(id);
//        return referenceById;
        Optional<Endereco> byId = enderecoRepository.findById(id);
        return byId;
    }

    public void atualizaEndereco (Endereco endereco){
        enderecoRepository.save(endereco);
    }
}
