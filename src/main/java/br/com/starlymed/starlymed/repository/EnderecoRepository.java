package br.com.starlymed.starlymed.repository;

import br.com.starlymed.starlymed.orm.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
