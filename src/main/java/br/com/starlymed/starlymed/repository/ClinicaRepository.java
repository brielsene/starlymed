package br.com.starlymed.starlymed.repository;

import br.com.starlymed.starlymed.orm.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Long> {
}
