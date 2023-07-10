package com.company.davyc.api.controller;

import com.company.davyc.api.DTO.CredencialDTO;
import com.company.davyc.api.DTO.TokenDTO;
import com.company.davyc.domain.entity.Usuario;
import com.company.davyc.exception.WrongPasswordException;
import com.company.davyc.security.jwt.JWTService;
import com.company.davyc.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class CadastroController {

    private final UserServiceImpl userService;
    private final JWTService jwtService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario save(@RequestBody Usuario usuario) {
        return userService.saveUser(usuario);
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredencialDTO credencialDTO) {
        try {
            Usuario usuario = Usuario.builder()
                    .username(credencialDTO.getLogin())
                    .password(credencialDTO.getPassword())
                    .build();
            UserDetails authUser = userService.authenticate(usuario);

            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getUsername(), token);

        } catch (UsernameNotFoundException | WrongPasswordException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
