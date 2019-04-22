package com.shmoozed.service;

import java.util.List;
import java.util.Optional;

import com.shmoozed.model.User;
import com.shmoozed.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

  @InjectMocks
  private UserService fixture;

  @Mock
  private UserRepository mockUserRepository;

  @Before
  public void setUp() {
    initMocks(this);
  }

  @Test
  public void getAll() {
    User user1 = new User(1, "1", "1", "1@1.com", "1", "1");
    User user2 = new User(2, "2", "2", "2@2.com", "2", "2");

    when(mockUserRepository.findAll()).thenReturn(asList(user1, user2));

    List<User> results = fixture.getAll();

    assertThat(results.get(0), is(user1));
    assertThat(results.get(1), is(user2));
  }

  @Test
  public void get() {
    User user1 = new User(1, "1", "1", "1@1.com", "1", "1");

    when(mockUserRepository.findById(1)).thenReturn(Optional.of(user1));

    Optional<User> results = fixture.get(1);

    assertThat(results.isPresent(), is(true));
    assertThat(results.get(), is(user1));
  }

  @Test
  public void get_notFound() {
    when(mockUserRepository.findById(1)).thenReturn(Optional.empty());

    Optional<User> results = fixture.get(1);

    assertThat(results.isPresent(), is(false));
  }

  @Test
  public void insertNewUser() {
    User user1 = new User(1, "1", "1", "1@1.com", "1", "1");

    when(mockUserRepository.save(user1)).thenReturn(user1);

    User results = fixture.insertNewUser(user1);

    verify(mockUserRepository).save(user1);
    assertThat(results, samePropertyValuesAs(user1));
  }

  @Test
  public void deleteUser() {
    fixture.deleteUser(1);
    verify(mockUserRepository).deleteById(1);
  }
}