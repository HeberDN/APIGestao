package com.h2healing.schedule.controller;

import com.h2healing.schedule.model.usuario.UsuarioModel;
import com.h2healing.schedule.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

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

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public UsuarioModel cadastrar (@RequestBody UsuarioModel usuario){
//        usuario.setPassword(encoder.encode(usuario.getPassword()));
//        return usuarioRepository.save(usuario);
//    }

//    @PutMapping("/{usuarioId}")
//    public ResponseEntity<UsuarioModel> atualizar (@PathVariable String usuarioId, @RequestBody UsuarioModel usuario){
//
//        if(!usuarioRepository.existsById(usuarioId)){
//            return ResponseEntity.notFound().build();
//        }
//
//        usuario.setId(Long.parseLong(usuarioId));
//        usuario.setPassword(encoder.encode(usuario.getPassword()));
//        UsuarioModel usuarioAtualizado = usuarioRepository.save(usuario);
//
//        return ResponseEntity.ok(usuarioAtualizado);
//    }

//    @GetMapping("/validarSenha/{usuarioId}")
//    public ResponseEntity<Boolean> validarSenha (@PathVariable Long usuarioId, @RequestParam String login, @RequestParam String password){
//        Optional <UsuarioModel> optUsuario = usuarioRepository.findByLogin(login);
//        if (optUsuario.isEmpty()){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
//        }
//        UsuarioModel usuario = optUsuario.get();
//        boolean valid = encoder.matches(password, usuario.getPassword());
//        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
//        return ResponseEntity.status(status).body(valid);
//    }

}
