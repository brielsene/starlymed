package br.com.starlymed.starlymed.service;

import br.com.starlymed.starlymed.orm.Clinica;
import br.com.starlymed.starlymed.repository.ClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicaService {
    @Autowired
    private ClinicaRepository clinicaRepository;

    public void cadastra (Clinica clinica){
        clinicaRepository.save(clinica);

    }
}
