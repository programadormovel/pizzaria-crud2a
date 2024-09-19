package com.itb.escola.pizzaria.services;

import com.itb.escola.pizzaria.model.Categoria;


import java.util.List;

public interface CategoriaService {

    public Categoria save(Categoria categoria);
    public Categoria findById(Long id);
    public Categoria findByIdActive(Long id);
    public List<Categoria> findAll();
    public void addProdutoToCategoria(Long categoriaId, Long  produtoId);
    public Categoria update (Categoria categoria, Long id);
    public boolean delete(Long id);
    public Categoria deleteLogic( Long id);
    public List<Categoria> findByAllActive();




}
