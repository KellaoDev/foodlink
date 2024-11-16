package com.foodlink.service;

import com.foodlink.dto.UserRegistrationDTO;
import com.foodlink.entity.UserEntity;
import com.foodlink.permissions.UserTypeEnum;
import com.foodlink.repository.RoleRepository;
import com.foodlink.repository.UserRepository;
import com.foodlink.roles.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void loginUser(UserRegistrationDTO userDto) {
        if (!userExists(userDto.getCnpj())) {
            throw new IllegalArgumentException("Username or password is incorrect");
        }
    }

    public void registerUser(UserRegistrationDTO userDto) {

        if (userDto.getCnpj() == null || userDto.getCnpj().isEmpty()) {
            throw new IllegalArgumentException("CNPJ não pode ser nulo ou vazio");
        }

        if (userDto.getCnpj().length() != 15) {
            throw new IllegalArgumentException("CNPJ deve conter 14 dígitos.");
        }

        if (userDto.getUsername() == null || userDto.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Nome de usuário não pode ser nulo ou vazio");
        }

        if (userDto.getUsername().length() < 10) {
            throw new IllegalArgumentException("Nome de usuário precisa ter pelo menos 10 caracteres!");
        }

        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia");
        }

        if (userDto.getPassword().length() < 8) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres.");
        }

        if (userDto.getConfirmPassword() == null || userDto.getConfirmPassword().isEmpty()) {
            throw new IllegalArgumentException("Confirmação da senha não pode ser nula ou vazia");
        }

        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            throw new IllegalArgumentException("Senhas não são iguais");
        }

        if (userDto.getUserTypeEnum() == null) {
            throw new IllegalArgumentException("Tipo de usuário não pode ser nula");
        }

        if (this.userRepository.findByCnpj(userDto.getCnpj()) != null) {
            throw new DataIntegrityViolationException("Usuário existente");
        }

        UserEntity user = new UserEntity();
        user.setCnpj(userDto.getCnpj());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUserTypeEnum(userDto.getUserTypeEnum());

        if (user.getUserTypeEnum() == UserTypeEnum.RESTAURANTE) {
            Role roleRestaurante = roleRepository.findByName("ROLE_RESTAURANTE");
            user.setRoles(Set.of(roleRestaurante));
        } else if (user.getUserTypeEnum() == UserTypeEnum.ONG) {
            Role roleOng = roleRepository.findByName("ROLE_ONG");
            user.setRoles(Set.of(roleOng));
        }
        userRepository.save(user);
    }

    public UserEntity findByCnpj(String cnpj) {
        return userRepository.findByCnpj(cnpj);
    }

    public boolean userExists(String cnpj) {
        return userRepository.findByCnpj(cnpj) != null;
    }
}
