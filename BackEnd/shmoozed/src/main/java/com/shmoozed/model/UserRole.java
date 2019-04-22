package com.shmoozed.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Relationship between a User and its role. User can be a Seller, Buyer, or Admin
 */
@Entity
@Table(name = "User_Role")
public class UserRole {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "UserRole_Id")
  private int UserRoleId;

  @Column(name = "User_Id")
  private int userId;

  @Column(name = "Role_Id")
  private int RoleId;

  public UserRole() {
    // Empty default constructor. This is needed in order for JPA to work properly.
  }

  public UserRole(int UserRole_Id, int User_Id, int Role_Id) {
    this.UserRoleId = UserRole_Id;
    this.userId = User_Id;
    this.RoleId = Role_Id;
  }

  public int getUserRole_Id() {
    return UserRoleId;
  }

  public void setUserRole_Id(int UserRole_Id) {
    this.UserRoleId = UserRole_Id;
  }

  public int getUser_Id() {
    return userId;
  }

  public void setUser_Id(int User_Id) {
    this.userId = User_Id;
  }

  public int getRole_Id() {
    return RoleId;
  }

  public void setRole_Id(int Role_Id) {
    this.RoleId = Role_Id;
  }

  public String getRole() {
    switch (RoleId) {
      case 1:
        return "Buyer";
      case 2:
        return "Seller";
      case 3:
        return "Admin";
      default:
        return "error";
    }
  }


  @Override
  public String toString() {
    return "UserRole{" +
      "UserRole_Id=" + UserRoleId +
      ", User_Id='" + userId + '\'' +
      ", Role_Id='" + RoleId + '\'' +
      ", Role='" + getRole() + '\'' +
      '}';
  }
}
