package com.shmoozed.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Represents a User. This User can be a Seller, Buyer, or Both depending on context.
 */
@Entity
@Table(name = "User")
public class User {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "User_Id")
  private int id;

  @Column(name = "User_First_Name")
  private String firstName;

  @Column(name = "User_Last_Name")
  private String lastName;

  @Column(name = "User_Email")
  private String email;

  @Column(name = "User_Password")
  private String password; // TODO: Plaintext password!

  @Column(name = "User_Username")
  private String username;

  public User() {
    // Empty default constructor. This is needed in order for JPA to work properly.
  }

  public User(int id, String firstName, String lastName, String email, String password, String username) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.username = username;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", email='" + email + '\'' +
      ", password='" + password + '\'' +
      ", username='" + username + '\'' +
      '}';
  }
}
