package com.h2healing.schedule.controllers.usuarioController;

import com.h2healing.schedule.model.usuario.UsuarioDTO;
import com.h2healing.schedule.model.usuario.UsuarioModel;
import com.h2healing.schedule.repository.repositoryUsuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        //this.encoder = encoder;
    }

    @GetMapping
    public List <UsuarioModel> listar () {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioModel> buscar (@PathVariable String usuarioId){
        return usuarioRepository.findById(usuarioId).map(usuario -> ResponseEntity.ok(usuario)).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{usuarioId}")
    ResponseEntity<UsuarioModel> atualizar (@PathVariable String usuarioId, @RequestBody @Valid UsuarioDTO usuarioDTO){
        Optional<UsuarioModel> optionalUsuarioModel = usuarioRepository.findById(usuarioId);
        if(optionalUsuarioModel.isPresent()){
            UsuarioModel usuarioModel = optionalUsuarioModel.get();
            usuarioModel.setNome(usuarioDTO.nome());
            usuarioModel.setCpf(usuarioDTO.cpf());
            usuarioModel.setLogin(usuarioDTO.login());
            String encryptedPassword = "{bcrypt}" + passwordEncoder.encode(usuarioDTO.password());
            usuarioModel.setPassword(encryptedPassword);
            usuarioModel.setDataNascimento(usuarioDTO.dataNascimento());
            usuarioModel.setStatus(usuarioDTO.status());
            usuarioModel.setRole(usuarioDTO.role());
            usuarioRepository.save(usuarioModel);
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
