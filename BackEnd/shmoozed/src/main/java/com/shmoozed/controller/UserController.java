package com.shmoozed.controller;

import java.util.List;
import java.util.Optional;

import com.shmoozed.model.User;
import com.shmoozed.model.UserAuthorizations;
import com.shmoozed.model.UserRoles;
import com.shmoozed.service.BuyerSellerItemsService;
import com.shmoozed.service.GoogleService;
import com.shmoozed.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Arrays.asList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin // Allow All CORS Requests. See https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
@RestController
@RequestMapping(path = "/user")
public class UserController {

  private Logger logger = LoggerFactory.getLogger(UserController.class);

  private BuyerSellerItemsService buyerSellerItemsService;
  private GoogleService googleService;
  private UserService userService;

  @Autowired
  public UserController(BuyerSellerItemsService buyerSellerItemsService, GoogleService googleService, UserService userService) {
    this.buyerSellerItemsService = buyerSellerItemsService;
    this.googleService = googleService;
    this.userService = userService;
  }

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public @ResponseBody ResponseEntity<List<User>> getUsers(@RequestHeader("Authorization") String token) {
    logger.debug("Request to get all users. token={}", token);

    List<User> allUsers = userService.getAll();

    return new ResponseEntity<>(allUsers, HttpStatus.OK);
  }

  @PostMapping(
    consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<User> insertNewUser(@RequestHeader("Authorization") String token,
                                                          @RequestBody User user) {
    logger.debug("Request to add new user. token={}, user={}", token, user);

    Optional<User> alreadyExistingUser = userService.getByUsername(user.getUsername());
    if (alreadyExistingUser.isPresent()) {
      logger.debug("Username already exists, new user not created");
      return new ResponseEntity<>(alreadyExistingUser.get(), HttpStatus.CONFLICT);
    }

    User newUser = userService.insertNewUser(user);
    buyerSellerItemsService.insertDefaultBuyerItemsForNewUser(user.getId());

    return new ResponseEntity<>(newUser, HttpStatus.OK);
  }

  @GetMapping(
    value = "/username/{username}",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody
  ResponseEntity<User> getUserByUsername(@RequestHeader("Authorization") String token,
                                                    @PathVariable("username") String username) {
    logger.debug("Request to get user. token={}, username={}", token, username);

    Optional<User> user = userService.getByUsername(username);

    if (user.isPresent()) {
      return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
    else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(
    value = "/{id}",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<User> getUserById(@RequestHeader("Authorization") String token,
                                                        @PathVariable("id") String id) {
    logger.debug("Request to get user. token={}, userId={}", token, id);

    Optional<User> user = userService.get(Integer.valueOf(id));

    if (user.isPresent()) {
      return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
    else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public @ResponseBody ResponseEntity<Void> deleteUserById(@RequestHeader("Authorization") String token,
                                                           @PathVariable("id") String id) {
    logger.debug("Request to delete user. token={}, userId={}", token, id);

    userService.deleteUser(Integer.valueOf(id));

    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Attempts to authenticate a user using the provided token (created externally from Google). It first
   * validates the token with Google. A {@link UserAuthorizations} object is returned to the caller. This
   * objects contains a new token, which the caller should use on all subsequent calls to any API request,
   * along with a list of {@link UserRoles} which the user has. The caller can use this, for example, to
   * enable/disable feature sets, etc.
   *
   * Expects the token to be passed in via an {@code Authorization} header.
   *
   * @param token The JWS token created by Google's Authentication Service
   * @return The {@link UserAuthorizations} for the now authenticated user
   */
  @GetMapping("/authorization")
  public @ResponseBody
  ResponseEntity<UserAuthorizations> getUserAuthorizations(@RequestHeader("Authorization") String token) {
    logger.debug("Request to get authorizations. token={}", token);

    String newToken = googleService.validateGoogleToken(token);

    UserAuthorizations userAuthorizations = new UserAuthorizations(newToken, asList(UserRoles.BUYER, // TODO: These are hardcoded to always return all the roles
                                                                                    UserRoles.SELLER,
                                                                                    UserRoles.ADMIN));

    return new ResponseEntity<>(userAuthorizations, HttpStatus.OK);
  }

}
