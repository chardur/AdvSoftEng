package com.shmoozed.controller;

import java.util.List;
import java.util.Optional;

import com.shmoozed.model.UserRole;
import com.shmoozed.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin // Allow All CORS Requests. See https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
@RestController
@RequestMapping(path = "/userrole")
public class UserRoleController {

  private Logger logger = LoggerFactory.getLogger(UserController.class);

  private UserRoleService userRoleService;

  @Autowired
  public UserRoleController(UserRoleService userRoleService) {
    this.userRoleService = userRoleService;
  }

  @PostMapping(
    consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<UserRole> insertNewUserRole(@RequestBody UserRole userRole) {
    logger.debug("Request to add new user role. userRole={}", userRole);

    UserRole newUserRole = userRoleService.insertNewUserRoleIfNotAlreadyPresent(userRole);

    return new ResponseEntity<>(newUserRole, HttpStatus.OK);
  }

  @GetMapping(
    value = "/{userRole_id}",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<UserRole> getUserRoleByUserRoleId(@PathVariable("userRole_id") int userRole_id) {
    logger.debug("Request to get userRole. userRoleId={}", userRole_id);

    Optional<UserRole> userRole = userRoleService.get(userRole_id);

    if (userRole.isPresent()) {
      return new ResponseEntity<>(userRole.get(), HttpStatus.OK);
    }
    else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(
    value = "/getallrolesforuserid/{user_id}",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<List<UserRole>> getAllRolesForUser(@PathVariable("user_id") int user_id) {
    logger.debug("Request to get all roles for a user. user_Id={}", user_id);

    List<UserRole> allRoles = userRoleService.getAllRolesGivenUserId(user_id);

    return new ResponseEntity<>(allRoles, HttpStatus.OK);
  }

  @GetMapping(
    value = "/userisbuyer/{user_id}",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<Boolean> isUserBuyer(@PathVariable("user_id") int user_id) {
    logger.debug("Request to determine if user is a buyer. user_Id={}", user_id);

    Boolean answer = userRoleService.userIsBuyer(user_id);

    return new ResponseEntity<>(answer, HttpStatus.OK);
  }

  @GetMapping(
    value = "/userisseller/{user_id}",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<Boolean> isUserSeller(@PathVariable("user_id") int user_id) {
    logger.debug("Request to determine if user is a seller. user_Id={}", user_id);

    Boolean answer = userRoleService.userIsSeller(user_id);

    return new ResponseEntity<>(answer, HttpStatus.OK);
  }

  @GetMapping(
    value = "/userisadmin/{user_id}",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<Boolean> isUserAdmin(@PathVariable("user_id") int user_id) {
    logger.debug("Request to determine if user is an admin. user_Id={}", user_id);

    Boolean answer = userRoleService.userIsAdmin(user_id);

    return new ResponseEntity<>(answer, HttpStatus.OK);
  }

  @DeleteMapping("/{userRole_id}")
  public @ResponseBody ResponseEntity<Void> deleteUserRoleById(@PathVariable("userRole_id") int userRole_id) {
    logger.debug("Request to delete userRole. userRole_id={}", userRole_id);

    userRoleService.deleteUserRole(userRole_id);

    return new ResponseEntity<>(HttpStatus.OK);
  }


}
