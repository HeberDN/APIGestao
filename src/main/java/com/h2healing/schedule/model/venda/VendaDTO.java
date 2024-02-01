package com.h2healing.schedule.model.venda;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.h2healing.schedule.model.cliente.ClienteModel;
import com.h2healing.schedule.model.produto.InterfaceProdutoDTO;
import com.h2healing.schedule.model.produto.ProdutoKitDTO;
import com.h2healing.schedule.model.produto.ProdutoUnicoDTO;

import java.util.List;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProdutoUnicoDTO.class, name = "produtoUnico"),
        @JsonSubTypes.Type(value = ProdutoKitDTO.class, name = "produtoKit")
})
public record VendaDTO(
        ClienteModel cliente,
        List<InterfaceProdutoDTO> produtos,
        FormaPagamento formaDePagamento,
        String observacao) {
}
