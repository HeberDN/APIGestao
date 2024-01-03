package com.h2healing.schedule.controllers.produtoController;

import com.h2healing.schedule.model.produto.DeletarDTO;
import com.h2healing.schedule.model.produto.ProdutoDTO;
import com.h2healing.schedule.model.produto.ProdutoModel;
import com.h2healing.schedule.model.produto.RegistrarProdutoDTO;
import com.h2healing.schedule.repository.repositoryProduto.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import com.h2healing.schedule.services.produto.ProdutoService;
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private ProdutoService produtoService;
    @GetMapping
    public ResponseEntity <List<ProdutoDTO>> getallProdutos(){
        List<ProdutoDTO> produtosDTO = produtoService.getAllProdutosDTO();
        return ResponseEntity.ok(produtosDTO);
    }
    @PostMapping
    public ResponseEntity registrarProduto(@RequestBody @Valid RegistrarProdutoDTO data){
        ProdutoModel newProdutoModel = new ProdutoModel(data);
        repository.save(newProdutoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto registrado com sucesso");
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
}