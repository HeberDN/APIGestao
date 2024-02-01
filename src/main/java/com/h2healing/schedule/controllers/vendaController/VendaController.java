package com.h2healing.schedule.controllers.vendaController;

import com.h2healing.schedule.model.venda.VendaDTO;
import com.h2healing.schedule.model.venda.VendaModel;
import com.h2healing.schedule.services.venda.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public ResponseEntity<List<VendaModel>> listarVendas() {
        List<VendaModel> vendas = vendaService.listarVendas();
        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterVendaPorId(@PathVariable UUID id) {
        Optional<VendaModel> venda = vendaService.obterVendaPorId(id);
        return venda != null
                ? new ResponseEntity<>(venda, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<VendaModel> criarNovaVenda(@RequestBody VendaDTO data) {
        VendaModel novaVenda = vendaService.criarNovaVenda(data);
        return new ResponseEntity<>(novaVenda, HttpStatus.CREATED);
    }
    // Adicionar métodos para atualizar, excluir, etc.
}
