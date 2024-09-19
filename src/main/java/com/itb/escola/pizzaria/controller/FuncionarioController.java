package com.itb.escola.pizzaria.controller;


import com.itb.escola.pizzaria.exceptions.BadRequest;
import com.itb.escola.pizzaria.model.Categoria;
import com.itb.escola.pizzaria.model.Produto;
import com.itb.escola.pizzaria.services.CategoriaService;
import com.itb.escola.pizzaria.services.ProdutoService;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class FuncionarioController {

    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;

    public FuncionarioController(ProdutoService produtoService, CategoriaService categoriaService) {
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
    }


    @GetMapping("/categoria/{id}")
    public ResponseEntity<Categoria> findCategoriaById(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(categoriaService.findByIdActive(Long.parseLong(id)));
        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' não é um número inteiro válido. Por favor, forneça um valor inteiro, como 5.");
        }
    }

    @GetMapping("/categoria")
    @Transactional
    public ResponseEntity<List<Categoria>> findAllCategorias() {
        // System.out.println(categoriaService.findAllPersonalizado().get(0).getProdutos().get(0).getNome());
        return ResponseEntity.ok().body(categoriaService.findByAllActive());
    }

    @PostMapping("/categoria")
    @Transactional
    public ResponseEntity<Categoria> salvarCategoria(@RequestBody Categoria categoria) {
        categoria.setCodStatus(true);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/categoria").toUriString());
        return ResponseEntity.created(uri).body(categoriaService.save(categoria));
    }

    @PutMapping("/categoria/{id}")
    public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria categoria, @PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(categoriaService.update(categoria, Long.parseLong(id)));
        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10.");
        }

    }

    @PutMapping("/delete-logic/categoria/{id}")
    @Transactional
    public ResponseEntity<Categoria> deleteLogicCategoria(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(categoriaService.deleteLogic(Long.parseLong(id)));
        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' não é um número inteiro válido. Por favor, forneça um valor inteiro, como 42.");
        }
    }

    @DeleteMapping("/categoria/{id}")
    @Transactional
    public ResponseEntity<Object> deleteCategoria(@PathVariable(value = "id") String id) {
        try {
            if (categoriaService.delete(Long.parseLong(id))) {
                return ResponseEntity.ok().body("Categoria com o id " + id + " excluída com sucesso");
            }
        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' não é um número inteiro válido. Por favor, forneça um valor inteiro, como 5.");
        }
        return ResponseEntity.ok().body("Não foi possível a exclusão da categoria com o id " + id);
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> findProdutoById(@PathVariable(value = "id") String id) {
        try {
            Long value = Long.parseLong(id);
            return ResponseEntity.ok().body(produtoService.findByIdActive(value));
        } catch (NumberFormatException ex) {
            // caso linha abaixo, o retorno seri um Object e não Produto
            //return ResponseEntity.badRequest().body("Erro: '" + id + "' não é um número inteiro válido. Por favor, forneça um valor inteiro, como 42.");
            throw new BadRequest("'" + id + "' não é um número inteiro válido. Por favor, forneça um valor inteiro, como 42.");
        }

    }

    @GetMapping("/produto")
    public ResponseEntity<List<Produto>> findAllProdutos() {

        return ResponseEntity.ok().body(produtoService.findByAllActive());
    }

    @PostMapping("/produto")
    public ResponseEntity<Produto> saveProduto(@RequestBody Produto produto) {
        produto.setCodStatus(true);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/produto").toUriString());
        return ResponseEntity.created(uri).body(produtoService.save(produto));

    }

    @PutMapping("/produto/{id}")
    public ResponseEntity<Produto> updateProduto(@RequestBody Produto produto, @PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(produtoService.update(produto, Long.parseLong(id)));
        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' não é um número inteiro válido. Por favor, forneça um valor inteiro, como 42.");
        }

    }

    @PutMapping("/delete-logic/produto/{id}")
    @Transactional
    public ResponseEntity<Produto> deleteLogicProduto(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(produtoService.deleteLogic(Long.parseLong(id)));
        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' não é um número inteiro válido. Por favor, forneça um valor inteiro, como 42.");
        }
    }

    @DeleteMapping("/produto/{id}")
    @Transactional
    public ResponseEntity<Object> deleteProduto(@PathVariable(value = "id") String id) {
        try {
            if (produtoService.delete(Long.parseLong(id))) {
                return ResponseEntity.ok().body("Produto com o id " + id + " excluído com sucesso");
            }
        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' não é um número inteiro válido. Por favor, forneça um valor inteiro, como 5.");
        }
        return ResponseEntity.ok().body("Não foi possível a exclusão do produto com o id " + id);
    }


}
