package br.com.starlymed.starlymed;

import br.com.starlymed.starlymed.orm.Clinica;
import br.com.starlymed.starlymed.orm.Endereco;
import br.com.starlymed.starlymed.orm.Medico;
import br.com.starlymed.starlymed.service.ClinicaService;
import br.com.starlymed.starlymed.service.EnderecoService;
import br.com.starlymed.starlymed.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class TesteCadastroClinica implements CommandLineRunner {
    @Autowired
    private ClinicaService clinicaService;
    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private MedicoService medicoService;



    @Override
    public void run(String... args) throws Exception {
        System.out.println("Digite o nome da clínica");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.next();
        Clinica clinica = new Clinica();
        clinica.setNome(nome);
        System.out.println("Digite o id do endereco");
        Long id = scanner.nextLong();
        Optional<Endereco> enderecoById = enderecoService.getEnderecoById(id);
        Endereco endereco = null;
        if(enderecoById.isPresent()){
            endereco = enderecoById.get();
            clinica.setEndereco(endereco);
        }

        System.out.println("Digite o id do médico: ");
        Long idMedico = scanner.nextLong();
        Medico medicoById = medicoService.getMedicoById(id);
        Medico medico = medicoById;
        clinica.setMedico(medico);

        clinicaService.cadastra(clinica);
        System.out.println("Clinica cadastrada com sucesso");


    }
}
