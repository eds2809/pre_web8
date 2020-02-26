package com.example.springBoodCRUDJAR.repositories;

import com.example.springBoodCRUDJAR.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByLogin(String login);
    User findUserById(Long id);
}
