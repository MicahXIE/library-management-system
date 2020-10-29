package com.micah.lms.controllers;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import net.minidev.json.JSONObject;

import com.micah.lms.service.UserServiceImpl;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Test
    public void contextLoads() {}


    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
	private UserServiceImpl userServiceImpl;

//    @Autowired  
//    private MockHttpSession session;
//      
//    @Autowired  
//    private MockHttpServletRequest request; 

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        String url = "/api/users";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }

    @Test
    public void testAddUser() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("userName", "admin");
        map.put("count", 5);


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(map))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());

        userServiceImpl.deleteUser(1);
    }
}