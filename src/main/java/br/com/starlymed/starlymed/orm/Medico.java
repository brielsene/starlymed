package br.com.starlymed.starlymed.orm;

import br.com.starlymed.starlymed.especialidade.Especialidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medico")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer crm;
    private String nome;
    private Especialidade especialidade;
    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
}
