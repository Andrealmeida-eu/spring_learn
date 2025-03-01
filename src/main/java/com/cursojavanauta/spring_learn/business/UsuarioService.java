package com.cursojavanauta.spring_learn.business;


import com.cursojavanauta.spring_learn.infrastructure.entity.Usuario;
import com.cursojavanauta.spring_learn.infrastructure.exceptions.ConflitException;
import com.cursojavanauta.spring_learn.infrastructure.exceptions.ResourceNotfoundException;
import com.cursojavanauta.spring_learn.infrastructure.repository.UsuarioRepository;
import io.jsonwebtoken.security.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario salvaUsuario(Usuario usuario) {
        try {
            emailExiste(usuario.getEmail());
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            return usuarioRepository.save(usuario);
        } catch (ConflitException e) {
            throw new ConflitException("Email ja cadastrado", e.getCause());

        }

    }

    public void emailExiste(String email) {
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflitException("Email já cadastrado" + email);
            }
        } catch (ConflitException e) {
            throw new ConflitException("Email já cadastrado" + e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotfoundException("Email nao encontrado" + email));
    }

    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }
}





