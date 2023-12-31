package com.h2healing.schedule.controllers.produtoController;

import com.h2healing.schedule.model.produto.ProdutoModel;
import com.h2healing.schedule.model.produto.RegistrarProdutoDTO;
import com.h2healing.schedule.repository.repositoryProduto.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;
    @GetMapping
    public ResponseEntity getAllProdutos(){
        var allProdutos = repository.findAll();
        return ResponseEntity.ok(allProdutos);
    }
    @PostMapping
    public ResponseEntity registrarProduto(@RequestBody RegistrarProdutoDTO data){
        ProdutoModel newProdutoModel = new ProdutoModel(data);
        repository.save(newProdutoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto registrado com sucesso");
    }
}