package com.shmoozed.repository;

import java.util.Optional;

import com.shmoozed.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

  Optional<User> findByUsernameEquals(String username);
}
