package com.h2healing.schedule.controllers.movimentacaoEstoqueController;

import com.h2healing.schedule.model.estoque.MovimentacaoEstoqueDTO;
import com.h2healing.schedule.services.movimentacaoEstoque.MovimentacaoEstoqueService;
import com.h2healing.schedule.exception.dominio.produto.ProdutoNaoEncontradoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimentacoes-estoque")
public class MovimentacaoEstoqueController {
    @Autowired
    private MovimentacaoEstoqueService movimentacaoEstoqueService;
    @PostMapping("/entrada")
    public ResponseEntity registrarEntradaEstoque(@RequestBody @Valid MovimentacaoEstoqueDTO entradaEstoqueDTO){
        try{
            movimentacaoEstoqueService.registrarEntradaEstoque(entradaEstoqueDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ProdutoNaoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PostMapping("/saida")
    public ResponseEntity registrarSaidaEstoque(@RequestBody @Valid MovimentacaoEstoqueDTO saidaEstoqueDTO){
        try{
            movimentacaoEstoqueService.registrarSaidaEstoque(saidaEstoqueDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ProdutoNaoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
