package org.example.Service;

import org.example.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repositorio;
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return repositorio.findByEmail(userEmail);
    }
}
