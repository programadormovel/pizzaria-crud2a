package com.itb.escola.pizzaria.services;

import com.itb.escola.pizzaria.model.Produto;

import java.util.List;


public interface ProdutoService {

    public Produto findById(Long id);
    public Produto findByIdActive(Long id);
    public List<Produto> findAll();
    public List<Produto> findByAllActive();
    public Produto save(Produto produto);
    public Produto update(Produto produto, Long id);
    public boolean delete(Long id);
    public Produto deleteLogic(Long id);



}
