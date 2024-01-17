package com.h2healing.schedule.controllers.produtoController;

import com.h2healing.schedule.exception.dominio.produto.ProdutoException;
import com.h2healing.schedule.model.produto.*;
import com.h2healing.schedule.repository.repositoryProduto.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import com.h2healing.schedule.services.produto.ProdutoServiceImpl;
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private ProdutoServiceImpl produtoServiceImpl;
    @GetMapping
    public ResponseEntity <List<ProdutoDTO>> getallProdutos(){
        List<ProdutoDTO> produtosDTO = produtoServiceImpl.getAllProdutosDTO();
        return ResponseEntity.ok(produtosDTO);
    }
    @PostMapping
    public ResponseEntity cadastrarProduto (@RequestBody @Valid RegistrarProdutoUnicoDTO data){
        try{
            ProdutoUnicoModel produtoUnicoModel = produtoServiceImpl.cadastrarProduto(data);
            return ResponseEntity.status(HttpStatus.CREATED).body("Produto registrado com sucesso "+ data);
        }catch (ProdutoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizarProduto (@RequestBody @Valid RegistrarProdutoDTO data){
        Optional<ProdutoModel> optionalProdutoModel = repository.findByCodigo(data.codigo());
        if(optionalProdutoModel.isPresent()){
            ProdutoModel produtoModel = optionalProdutoModel.get();
            produtoModel.setNomeProduto(data.nomeProduto());
            produtoModel.setUnidade(data.unidade());
            produtoModel.setCustoUnitario(data.custoUnitario());
            produtoModel.setValorVendaUnitario(data.valorVendaUnitario());
            return ResponseEntity.ok(produtoModel);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping
    @Transactional
    public ResponseEntity deletarProduto (@RequestBody @Valid DeletarDTO data){
        Optional<ProdutoModel> optionalProdutoModel = repository.findByCodigo(data.codigo());
        if(optionalProdutoModel.isPresent()){
            ProdutoModel produtoModel = optionalProdutoModel.get();
            repository.delete(produtoModel);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/kit")
    public ResponseEntity cadastrarProdutoKit (@RequestBody @Valid RegistrarProdutoKitDTO data){
        try{
            ProdutoKitModel produtoKit = (ProdutoKitModel) produtoServiceImpl.cadastrarProdutoKit(data);
            return ResponseEntity.status(HttpStatus.CREATED).body("Produto kit registrado com sucesso: " + data);
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar produto kit " + e);
        }
    }
}