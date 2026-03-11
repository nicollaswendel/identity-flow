package com.identity.flow.nicollas.service;

import com.identity.flow.nicollas.domain.dto.UserDTO;
import com.identity.flow.nicollas.domain.model.User;
import com.identity.flow.nicollas.exception.RepositoryException;
import com.identity.flow.nicollas.repository.UserRepository;
import com.identity.flow.nicollas.security.Token;
import com.identity.flow.nicollas.security.TokenUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<User> findAll() {
        logger.info("Log: o usuário {} está listando usuários.", getLogged()); // Funciona como se fosse um print.
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        logger.info("Log: o usuário {} está listando um usuário específico.", getLogged());
        return userRepository.findById(id).orElseThrow(() ->
                new RepositoryException("O usuário informado não existe no banco.", HttpStatus.NOT_FOUND));
    }

    public void deleteById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new RepositoryException("O usuário informado não existe no banco.", HttpStatus.NOT_FOUND));

        logger.info("Log: o usuário {} está deletando um usuário.", getLogged());
        userRepository.deleteById(user.getUserId());
    }

    public User save(User user) {
        User savedUser = new User();

        String encoder = passwordEncoder.encode(user.getPass());

        savedUser.setCompleteName(user.getCompleteName());
        savedUser.setPass(encoder);
        savedUser.setEmail(user.getEmail());
        savedUser.setUsername(user.getUsername());
        savedUser.setTelephone(user.getTelephone());

        userRepository.save(savedUser);
        logger.info("Log: o usuário {} está criando um usuário.", getLogged());
        return savedUser;
    }

    public User update(User user, Integer id) {

        User updatedUser = userRepository.findById(id).orElseThrow(() ->
                new RepositoryException("O usuário informado não existe no banco.", HttpStatus.NOT_FOUND));

        updatedUser.setCompleteName(user.getCompleteName());
        updatedUser.setPass(user.getPass());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setUsername(user.getUsername());
        updatedUser.setTelephone(user.getTelephone());

        userRepository.save(updatedUser);
        logger.info("Log: o usuário {} está atualizando um usuário.", getLogged());
        return updatedUser;
    }

    public Token generateToken(@Valid UserDTO userDTO) {

        User user = userRepository.findByEmail(userDTO.getEmail());

        if (user != null) {
            boolean valid = passwordEncoder.matches(userDTO.getPass(), user.getPass());

            if (valid){
                return new Token(TokenUtil.createToken(user));
            }
        }
        return null;
    }

    private String getLogged(){
        Authentication loggedUser = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if(loggedUser != null && !(loggedUser instanceof AnonymousAuthenticationToken)){
            return loggedUser.getName();
        }

        return "null";
    }

}
