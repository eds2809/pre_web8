package com.example.springBoodCRUDJAR.service.interfaces;



import com.example.springBoodCRUDJAR.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean save(String login, String pass, String email, String role);

    List<User> getAll();

    User getByID(Long id);

    void deleteByID(Long id);

    boolean updateUser(Long id, String login, String pass, String email, String role);

    User getUserByLogin(String login);
}
