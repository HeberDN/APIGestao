package com.h2healing.schedule.services.venda;

import com.h2healing.schedule.model.venda.VendaDTO;
import com.h2healing.schedule.model.venda.VendaModel;
import com.h2healing.schedule.model.produto.InterfaceProdutoDTO;
import com.h2healing.schedule.repository.repositoryVenda.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VendaService {
    @Autowired
    VendaRepository vendaRepository;
    public List<VendaModel> listarVendas() {
        return vendaRepository.findAll();
    }
    public Optional<VendaModel> obterVendaPorId(UUID id) {
        return vendaRepository.findById(id);
    }
    public VendaModel criarNovaVenda(VendaDTO venda){
        VendaModel vendaModel = criarNovaVendaModel (venda);
        try {
            Long numeroSequencial = obterNumeroSequencial();
            int ano = obterAnoAtual();
            vendaModel.setNumeroSequencial(numeroSequencial);
            vendaModel.setAno(ano);
            return vendaRepository.save(vendaModel);
        }catch (Exception e){
            throw new RuntimeException("Erro ao criar nova venda",e);
        }
    }

    private int obterAnoAtual() {
        return LocalDateTime.now().getYear();
    }

    private Long obterNumeroSequencial() {
        Optional<Long> ultimoNumeroSequencial = vendaRepository.findMaxNumeroSequencial(obterAnoAtual());
        return ultimoNumeroSequencial.orElse(0L) + 1;
    }

    private VendaModel criarNovaVendaModel(VendaDTO venda){
        VendaModel vendaModel = new VendaModel();
        vendaModel.setCliente(venda.getCliente());
        vendaModel.setProdutos(venda.getProdutos().stream().map(InterfaceProdutoDTO::toProdutoModel).collect(Collectors.toList()));
        vendaModel.setFormaDePagamento(venda.getFormaDePagamento());
        vendaModel.setObservacao(venda.getObservacao());

        return vendaModel;
    }
}
