package br.com.starlymed.starlymed.repository;

import br.com.starlymed.starlymed.orm.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
