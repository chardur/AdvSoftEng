package com.shmoozed.service;

import java.util.List;
import java.util.Optional;

import com.shmoozed.model.UserRole;
import com.shmoozed.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

  private Logger logger = LoggerFactory.getLogger(UserService.class);

  private UserRoleRepository userRoleRepository;

  @Autowired
  public UserRoleService(UserRoleRepository userRoleRepository) {
    this.userRoleRepository = userRoleRepository;
  }

  public boolean userIsBuyer(int UserId) {
    logger.debug("Fetching roles matching 'buyer' for given user");

    return userRoleRepository.findDUserRoleByUserIdEqualsAndRoleIdEquals(UserId, 1).isPresent();
  }

  public boolean userIsSeller(int UserId) {
    logger.debug("Fetching roles matching 'seller' for given user");
    return userRoleRepository.findDUserRoleByUserIdEqualsAndRoleIdEquals(UserId, 2).isPresent();
  }

  public boolean userIsAdmin(int UserId) {
    logger.debug("Fetching roles matching 'seller' for given user");
    return userRoleRepository.findDUserRoleByUserIdEqualsAndRoleIdEquals(UserId, 3).isPresent();
  }

  public UserRole insertNewUserRoleIfNotAlreadyPresent(UserRole userRole) {
    logger.debug("Attempting to insert userRole={}", userRole);

    //Allowable values
    if ((userRole.getRole_Id() != 1) && (userRole.getRole_Id() != 2) && (userRole.getRole_Id() != 3)) {
      logger.debug("Value for role not valid.");
      return null;
    }

    // If there is already a userRole with the item_id and role_id then don't insert it again.
    Optional<UserRole> existingUserRole =
      userRoleRepository.findDUserRoleByUserIdEqualsAndRoleIdEquals(userRole.getUser_Id(), userRole.getRole_Id());

    if (existingUserRole.isPresent()) {
      logger.debug("UserRole already exists. Not attempting to insert again.");
      return existingUserRole.get();
    }
    else {
      UserRole newUserRole = userRoleRepository.save(userRole);
      logger.debug("New buyer item inserted. buyerItem={}", newUserRole);
      return newUserRole;
    }
  }

  public Optional<UserRole> get(int userRole_Id) {
    logger.debug("Fetching userRoleId={}", userRole_Id);
    return userRoleRepository.findById(userRole_Id);
  }

  public List<UserRole> getAllRolesGivenUserId(int UserId) {
    logger.debug("Fetching all user roles given a user_id");
    return (List<UserRole>) userRoleRepository.findUserRoleByUserIdEquals(UserId);
  }

  public void deleteUserRole(int userRoleId) {
    logger.debug("Deleting userRole userRoleId={}", userRoleId);
    userRoleRepository.deleteById(userRoleId);
  }


}
