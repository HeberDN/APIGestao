package com.h2healing.schedule.model.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clientes")
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    @Column(unique = true)
    private String documento;
    private String telefone;
    private String email;
}
