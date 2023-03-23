package br.com.starlymed.starlymed;

import br.com.starlymed.starlymed.especialidade.Especialidade;
import br.com.starlymed.starlymed.orm.Endereco;
import br.com.starlymed.starlymed.orm.Medico;
import br.com.starlymed.starlymed.service.EnderecoService;
import br.com.starlymed.starlymed.service.MedicoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class StarlymedApplication implements CommandLineRunner {

	private EnderecoService enderecoService;
	private MedicoService medicoService;

	public StarlymedApplication(EnderecoService enderecoService, MedicoService medicoService){
		this.enderecoService = enderecoService;
		this.medicoService = medicoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(StarlymedApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Digite 1 para interagir com endereco");
		System.out.println("Digite 2 para para interagir com médico");
		Scanner scanner = new Scanner(System.in);
		int opcao = scanner.nextInt();
		while(opcao != 0){
		if(opcao == 1){


			cadastroEndereco(scanner);


		}
		if(opcao == 2){
			System.out.println("Digite 1 para cadastrar médico");
			System.out.println("Digite 2 para listar médicos");
			System.out.println("Digite 3 para listar 1 médico pelo ID");
			System.out.println("Digite 4 para remover médico pelo ID");
			System.out.println("Digite 5 para atualizar dados de um médico");
			System.out.println("Digite 0 para sair");
			int escolha = scanner.nextInt();
			switch (escolha){
				case 1:
					cadastroMedico(scanner);
					break;
					case 2 :
						listaMedicos();
						break;
				case 3 :
					listaMedicoPeloId(scanner);
					break;
				case 4:
					this.removeMedicoPeloId(scanner);
					break;

				case 5:
					this.atualizaDadosMedico(scanner);
					break;
				default:
					run();


			}


		}
	}}

	private void cadastroMedico(Scanner scanner) {
		Medico medico = new Medico();
		System.out.println("Digite seu CRM");
		Integer crm = scanner.nextInt();
		System.out.println();
		System.out.println("Digite seu nome:");
		String nome = scanner.next();
		System.out.println();
//			System.out.println("Digite seu especialidade");
//			String especialidadeString = scanner.next();
		System.out.println("Digite o id do endereco");
		Long id = scanner.nextLong();
		Optional<Endereco> enderecoById = enderecoService.getEnderecoById(id);
		if(enderecoById.isPresent()){
			Endereco endereco = enderecoById.get();
			medico.setEndereco(endereco);
		}

		medico.setNome(nome);
		medico.setCrm(crm);

		medicoService.cadastra(medico);
	}

	private void cadastroEndereco(Scanner scanner) {
		System.out.println("Digite o nome do pais");
		String pais = scanner.next();
		System.out.println();
		System.out.println("Digite o estado");
		String estado = scanner.next();
		System.out.println();
		System.out.println("Digite a cidade");
		String cidade = scanner.next();
		System.out.println();
		System.out.println("Digite o bairro");
		String bairro = scanner.next();
		System.out.println();
		System.out.println("Digite o cep");
		String cep = scanner.next();
		System.out.println();
		Endereco endereco = new Endereco(null, pais,
				estado, cidade, bairro, cep);
		enderecoService.cadastra(endereco);
	}

	public void listaMedicos(){

		List<Medico> allMedicos = medicoService.getAllMedicos();
		allMedicos.forEach(a -> System.out.println(a.getNome()));

	}

	public void listaMedicoPeloId(Scanner scanner){
		System.out.println("Digite o id do médico a ser listado");
		Long id = scanner.nextLong();
		System.out.println(medicoService.getMedicoById(id).getNome());
	}

	public void removeMedicoPeloId(Scanner scanner){
		System.out.println("Digite o id do médico a ser deletado: ");
		Long id = scanner.nextLong();
		Medico medico = new Medico();
		medico.setId(id);
		medicoService.removeById(id);
		System.out.println("Médico "+ medico.getNome()+", Removido com sucesso");

	}

	public void atualizaDadosMedico(Scanner scanner){
		System.out.println("Digite o ID do médico a ser alterado");
		Long id = scanner.nextLong();
		Medico medicoById = medicoService.getMedicoById(id);
		Medico medico = medicoById;
		System.out.println("Digite 1 para mudar seu nome");
		System.out.println("Digite 2 para mudar seu endereco: ");
		int opcao = scanner.nextInt();
		switch (opcao){
			case 1:
				System.out.println("Digite seu nome completo");
				String nome = scanner.next();
				medico.setNome(nome);
				medicoService.atualiza(medico);
				break;
			case 2:
				atualizaEnderecoMedico(scanner, medico);
				break;


		}

	}

	//para este método, precisamos trazer o endereco, porque se apenas setar o id, ele vai setar todos os outros campos como null
	private void atualizaEnderecoMedico(Scanner scanner, Medico medico) {
		int escolha;
		do {
			System.out.println("Digite 1 para mudar o país");
			System.out.println("Digite 2 para mudar o estado");
			System.out.println("Digite 3 para mudar a cidade");
			System.out.println("Digite 4 para mudar o bairro");
			System.out.println("Digite 5 para mudar o cep");
			System.out.println("Digite 0 para sair");
			escolha = scanner.nextInt();
			Optional<Endereco> optionalEndereco = enderecoService.getEnderecoById(medico.getEndereco().getId());
			Endereco endereco = null;
			if(optionalEndereco.isPresent()){
				endereco = optionalEndereco.get();
			}
			if(escolha == 1){
				System.out.println("Digite o nome do país");
				String pais = scanner.next();
				endereco.setPais(pais);
				this.enderecoService.atualizaEndereco(endereco);
				System.out.println("Atualizado com sucesso");


			}
			if(escolha == 2){
				System.out.println("Digite o nome do estado");
				String estado = scanner.next();
				endereco.setEstado(estado);
				this.enderecoService.atualizaEndereco(endereco);
				System.out.println("Atualizado com sucesso");
			}
			if(escolha == 3){
				System.out.println("Digite o nome da cidade");
				String cidade = scanner.next();
				endereco.setCidade(cidade);
				enderecoService.atualizaEndereco(endereco);
				System.out.println("Atualizado com sucesso");
			}
			if(escolha == 4){
				System.out.println("Digite o nome do bairro");
				String bairro = scanner.next();
				endereco.setBairro(bairro);
				this.enderecoService.atualizaEndereco(endereco);
				System.out.println("Atualizado com sucesso");
			}
			//deixar os de mais, igual este
			//só este está funcionando
			if(escolha == 5){
				System.out.println("Digite o cep");
				String cep = scanner.next();
				endereco.setCep(cep);
				this.enderecoService.atualizaEndereco(endereco);
				System.out.println("Atualizado com sucesso");
			}




		}while (escolha != 0);


	}
}
