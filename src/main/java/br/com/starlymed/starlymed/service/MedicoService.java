package br.com.starlymed.starlymed.service;

import br.com.starlymed.starlymed.orm.Medico;
import br.com.starlymed.starlymed.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    public void cadastra(Medico medico){
        medicoRepository.save(medico);
    }

    public List<Medico> getAllMedicos(){
        return medicoRepository.findAll();
    }

    public Medico getMedicoById(Long id) {
        Optional<Medico> byId = medicoRepository.findById(id);
        if (byId.isPresent()) {
            Medico medico = byId.get();
            return medico;

        } else{
            return null;
        }
    }

    public void removeById(Long id){
        medicoRepository.deleteById(id);
    }

    public void atualiza (Medico medico){
        medicoRepository.save(medico);

    }
}
