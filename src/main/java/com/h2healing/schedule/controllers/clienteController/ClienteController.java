package com.h2healing.schedule.controllers.clienteController;

import com.h2healing.schedule.model.cliente.ClienteDTO;
import com.h2healing.schedule.model.cliente.ClienteModel;
import com.h2healing.schedule.repository.repositoryCliente.ClienteRepository;
import com.h2healing.schedule.services.cliente.ClienteValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final ClienteValidationService clienteValidationService;
    @Autowired
    public ClienteController(ClienteRepository clienteRepository, ClienteValidationService clienteValidationService) {
        this.clienteRepository = clienteRepository;
        this.clienteValidationService = clienteValidationService;
    }

    // Endpoint para obter todos os clientes
    @GetMapping
    public ResponseEntity<List<ClienteModel>> getAllClientes() {
        List<ClienteModel> clientes = clienteRepository.findAll();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    // Endpoint para obter um cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> getClienteById(@PathVariable UUID id) {
        Optional<ClienteModel> cliente = clienteRepository.findById(id);
        return cliente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para criar um novo cliente
    @PostMapping
    public ResponseEntity<?> createCliente(@RequestBody ClienteDTO clienteDTO) {
        List<String> validationErrors = clienteValidationService.validarCliente(clienteDTO);

        if (!validationErrors.isEmpty()) {
            return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
        }
        ClienteModel novoCliente = new ClienteModel(
                UUID.randomUUID(),
                clienteDTO.nome(),
                clienteDTO.documento(),
                clienteDTO.telefone(),
                clienteDTO.email()
        );
        clienteRepository.save(novoCliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    // Endpoint para atualizar um cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteModel> updateCliente(@PathVariable UUID id, @RequestBody ClienteDTO clienteDTO) {
        Optional<ClienteModel> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isPresent()) {
            ClienteModel cliente = clienteOptional.get();
            cliente.setNome(clienteDTO.nome());
            cliente.setDocumento(clienteDTO.documento());
            cliente.setTelefone(clienteDTO.telefone());
            cliente.setEmail(clienteDTO.email());
            clienteRepository.save(cliente);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para excluir um cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable UUID id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
