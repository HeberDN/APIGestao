package com.h2healing.schedule.controller;

import com.h2healing.schedule.model.usuario.AuthenticationDTO;
import com.h2healing.schedule.model.usuario.LoginResponseDTO;
import com.h2healing.schedule.model.usuario.RegisterDTO;
import com.h2healing.schedule.model.usuario.UsuarioModel;
import com.h2healing.schedule.repository.UsuarioRepository;
import com.h2healing.schedule.security.JWTTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManage;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    JWTTokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(),data.password());
        var auth = this.authenticationManage.authenticate(usernamePassword);
        var token = tokenService.generateToken((UsuarioModel)auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.usuarioRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = passwordEncoder.encode(data.password());
        String passwordWithPrefix = "{bcrypt}" + encryptedPassword;
        UsuarioModel newUser = new UsuarioModel(data.login(), passwordWithPrefix, data.role());

        this.usuarioRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
