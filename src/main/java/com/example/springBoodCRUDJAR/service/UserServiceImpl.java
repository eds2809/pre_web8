package com.example.springBoodCRUDJAR.service;


import com.example.springBoodCRUDJAR.models.Role;
import com.example.springBoodCRUDJAR.models.User;
import com.example.springBoodCRUDJAR.repositories.RoleRepository;
import com.example.springBoodCRUDJAR.repositories.UserRepository;
import com.example.springBoodCRUDJAR.service.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean save(String login, String pass, String email, String role) {
        if (login.isEmpty() || pass.isEmpty() || role.isEmpty()){
            return false;
        }

         userRepository.save(
                new User(
                        login,
                        passwordEncoder.encode(pass),
                        email,
                        Collections.singleton(
                                roleRepository.findByRole(role)
                        )
                )
        );
        return true;
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getByID(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public void deleteByID(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean updateUser(Long id, String login, String pass, String email, String role) {

        User user = getByID(id);

        if (user == null) {
            return false;
        }

        if (!login.isEmpty()){
            user.setLogin(login);
        }

        if (!pass.isEmpty()){
            user.setPass(passwordEncoder.encode(pass));
        }

        if (!role.isEmpty()){
            user.getRoles().clear();
            user.addRole(
                    roleRepository.findByRole(role)
            );
        }

        userRepository.save(user);
        return true;
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }


}
