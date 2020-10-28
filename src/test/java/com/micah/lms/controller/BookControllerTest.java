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

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerTest {

    @Test
    public void contextLoads() {}


    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

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
    public void testGetAllBooks() throws Exception {
        String url = "/api/books";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }

    @Test
    public void testAddBook() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("isbn", "qwerty");
        map.put("bookName", "unit");
        map.put("author", "unit");
        map.put("publishDate", "2020-10-28");
        map.put("summary", "summary");
        map.put("available", true);


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/addBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(map))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testUpdateBook() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("isbn", "qwerty1");
        map.put("bookName", "unit1");
        map.put("author", "unit1");
        map.put("publishDate", "2020-10-28");
        map.put("summary", "summary");
        map.put("available", true);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/updateBook/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(map))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void testSearchBookByName() throws Exception {

        String url = "/api/book/searchByName/unit1";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void testSearchBookByISBN() throws Exception {

        String url = "/api/book/searchByISBN/qwerty1";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }


    @Test
    public void testDeleteBook() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/deleteBook/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}