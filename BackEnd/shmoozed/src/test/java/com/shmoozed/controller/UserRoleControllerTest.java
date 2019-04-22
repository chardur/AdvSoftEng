package com.shmoozed.controller;

import com.shmoozed.model.UserRole;
import com.shmoozed.service.UserRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserRoleControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserRoleService mockUserRoleService;

  @Test
  public void getAllRolesForUser() throws Exception {
    UserRole userRole1 = new UserRole(1, 1, 1);
    UserRole userRole2 = new UserRole(2, 1, 2);

    when(mockUserRoleService.getAllRolesGivenUserId(1)).thenReturn(asList(userRole1, userRole2));

    mockMvc.perform(get("/userrole/getallrolesforuserid/1"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().json("[" +
                                  "{\"role_Id\":1,\"user_Id\":1,\"role\":\"Buyer\",\"userRole_Id\":1}," +
                                  "{\"role_Id\":2,\"user_Id\":1,\"role\":\"Seller\",\"userRole_Id\":2}]"));
  }

}