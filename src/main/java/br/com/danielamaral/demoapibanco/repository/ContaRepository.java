package br.com.danielamaral.demoapibanco.repository;

import br.com.danielamaral.demoapibanco.repository.entity.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<ContaEntity, Long> {
    public ContaEntity findByConta(String conta);
}
