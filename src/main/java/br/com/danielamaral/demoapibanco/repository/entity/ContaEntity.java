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
    private String numero;
    private Double saldo;

    public ContaDto toDto(){
        return new ContaDto(this.id, this.numero, this.saldo);
    }
}
