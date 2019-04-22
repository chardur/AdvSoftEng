package com.shmoozed.service;

import java.util.List;
import java.util.Optional;

import com.shmoozed.model.UserRole;
import com.shmoozed.repository.UserRoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserRoleServiceTest {

  @InjectMocks
  private UserRoleService fixture;

  @Mock
  private UserRoleRepository mockUserRoleRepository;

  @Before
  public void setUp() {
    initMocks(this);
  }

  @Test
  public void userIsBuyer() {
    UserRole userRole1 = new UserRole(1, 1, 1);
    UserRole userRole2 = new UserRole(2, 2, 2);

    Optional<UserRole> optional1 = Optional.of(userRole1);
    Optional<UserRole> optional2 = Optional.empty();
    when(mockUserRoleRepository.findDUserRoleByUserIdEqualsAndRoleIdEquals(userRole1.getUser_Id(), 1))
      .thenReturn(optional1);
    when(mockUserRoleRepository.findDUserRoleByUserIdEqualsAndRoleIdEquals(userRole2.getUser_Id(), 1))
      .thenReturn(optional2);

    Boolean results1 = fixture.userIsBuyer(userRole1.getUser_Id());
    Boolean results2 = fixture.userIsBuyer(userRole2.getUser_Id());

    assertEquals(results1, true);
    assertEquals(results2, false);
  }

  @Test
  public void userIsSeller() {
    UserRole userRole1 = new UserRole(1, 1, 1);
    UserRole userRole2 = new UserRole(2, 2, 2);

    Optional<UserRole> optional1 = Optional.empty();
    Optional<UserRole> optional2 = Optional.of(userRole2);
    when(mockUserRoleRepository.findDUserRoleByUserIdEqualsAndRoleIdEquals(userRole1.getUser_Id(), 2))
      .thenReturn(optional1);
    when(mockUserRoleRepository.findDUserRoleByUserIdEqualsAndRoleIdEquals(userRole2.getUser_Id(), 2))
      .thenReturn(optional2);

    Boolean results1 = fixture.userIsSeller(userRole1.getUser_Id());
    Boolean results2 = fixture.userIsSeller(userRole2.getUser_Id());

    assertEquals(results1, false);
    assertEquals(results2, true);
  }

  @Test
  public void getAllRolesGivenUserId() {
    UserRole userRole1 = new UserRole(1, 1, 1);
    UserRole userRole2 = new UserRole(2, 1, 2);

    List<UserRole> listIn = asList(userRole1, userRole2);
    when(mockUserRoleRepository.findUserRoleByUserIdEquals(userRole1.getUser_Id())).thenReturn(listIn);

    List<UserRole> listOut = fixture.getAllRolesGivenUserId(userRole1.getUser_Id());
    assertEquals(listIn, listOut);
  }


}