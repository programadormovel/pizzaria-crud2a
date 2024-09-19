package com.itb.escola.pizzaria.repository;

import com.itb.escola.pizzaria.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(value = "SELECT * FROM produtos p WHERE p.cod_status='1'", nativeQuery = true)
    public List<Produto> findByAllActive();

    @Query(value = "SELECT * FROM produtos p WHERE p.id=?1 AND p.cod_status='1'", nativeQuery = true)
    public Produto findByIdActive(Long id);
}
