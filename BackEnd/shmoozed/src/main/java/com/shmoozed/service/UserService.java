package com.shmoozed.service;

import java.util.List;
import java.util.Optional;

import com.shmoozed.model.User;
import com.shmoozed.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private Logger logger = LoggerFactory.getLogger(UserService.class);

  private UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAll() {
    logger.debug("Fetching all users");
    return (List<User>) userRepository.findAll();
  }

  public Optional<User> get(int userId) {
    logger.debug("Fetching userId={}", userId);
    return userRepository.findById(userId);
  }

  public Optional<User> getByUsername(String username) {
    logger.debug("Fetching username={}", username.toLowerCase());
    return userRepository.findByUsernameEquals(username.toLowerCase());
  }

  public User insertNewUser(User user) {
    user.setUsername(user.getUsername().toLowerCase());
    logger.debug("Attempting to insert user={}", user);
    User newUser = userRepository.save(user);
    logger.debug("New user inserted into database. newUser={}", newUser);
    return newUser;
  }

  public void deleteUser(int userId) {
    userRepository.deleteById(userId);
  }
}
