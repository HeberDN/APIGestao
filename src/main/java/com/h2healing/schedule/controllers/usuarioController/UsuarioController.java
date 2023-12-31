package com.h2healing.schedule.controllers.usuarioController;

import com.h2healing.schedule.model.usuario.UsuarioModel;
import com.h2healing.schedule.repository.repositoryUsuario.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
