package com.company.davyc.service.impl;

import com.company.davyc.domain.entity.Usuario;
import com.company.davyc.domain.repository.LoginSD;
import com.company.davyc.exception.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    final LoginSD loginRepo;
    final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(LoginSD loginRepo,@Lazy PasswordEncoder passwordEncoder) {
        this.loginRepo = loginRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = loginRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

        String[] role = usuario.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(role)
                .build();
    }

    public Usuario saveUser(Usuario usuario){
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return loginRepo.save(usuario);
    }

    public UserDetails authenticate(Usuario usuario){
        UserDetails userDetails = loadUserByUsername(usuario.getUsername());
        boolean isAuthenticated = passwordEncoder.matches(usuario.getPassword(), userDetails.getPassword());
        if (isAuthenticated){
            return userDetails;
        }
        throw new WrongPasswordException("Senha incorreta");
    }


}
