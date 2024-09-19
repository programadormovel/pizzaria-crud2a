package com.itb.escola.pizzaria.repository;

import com.itb.escola.pizzaria.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query(value = "SELECT * FROM categorias c WHERE c.cod_status='1'", nativeQuery = true)
    public List<Categoria> findByAllActive();

    @Query(value = "SELECT * FROM categorias c WHERE c.id=?1 AND c.cod_status='1'", nativeQuery = true)
    public Categoria findByIdActive(Long id);
}
