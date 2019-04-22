package com.shmoozed.repository;

import com.shmoozed.model.UserRole;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
  //  Optional<UserRole> findUserRoleByUserIdEqualsAndRoleIdEquals(int UserId, int RoleId);

  @Query(value = "SELECT * from User_Role WHERE User_Id = :userId AND Role_Id = :roleId", nativeQuery = true)
  Optional<UserRole> findDUserRoleByUserIdEqualsAndRoleIdEquals(@Param("userId") int userId,
                                                                @Param("roleId") int roleId);

  List<UserRole> findUserRoleByUserIdEquals(int UserId);
}
