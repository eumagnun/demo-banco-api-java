package br.com.danielamaral.demoapibanco.repository.entity;
import br.com.danielamaral.demoapibanco.dto.ContaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String conta;
    private Double saldo;

    public ContaDto toDto(){
        return new ContaDto(this.id, this.conta, this.saldo);
    }
}
