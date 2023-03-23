package br.com.starlymed.starlymed.orm;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clinica")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
    @OneToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;
}
