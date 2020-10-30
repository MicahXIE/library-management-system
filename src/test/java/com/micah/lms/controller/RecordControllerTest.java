package com.micah.lms.controllers;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.log4j.Log4j2;
import org.junit.After;
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

import com.micah.lms.service.BookServiceImpl;
import com.micah.lms.service.RecordService;
import com.micah.lms.service.RecordServiceImpl;
import com.micah.lms.service.UserServiceImpl;


@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordControllerTest {

    @Test
    public void contextLoads() {}


    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
	private UserServiceImpl userServiceImpl;

    // due to transactional annotation, here must autowired interface
	@Autowired
	private RecordService recordServiceImpl;

	@Autowired
	private BookServiceImpl bookServiceImpl;
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
    public void testGetAllRecords() throws Exception {
        String url = "/api/records";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }

    @Test
    public void testAddRecordUserNotExist() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/borrow/100/100"))
                .andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(400))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("user doesn't exist."))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testUpdateRecordUserNotExist() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/return/100/100"))
                .andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(400))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("user doesn't exist."))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testAddRecordBookNotExist() throws Exception {
    	Map<String, Object> map = new HashMap<>();
        map.put("id", 100);
        map.put("userName", "user100");
        map.put("count", 5);

        MvcResult userResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(map))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();        

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/borrow/100/100"))
                .andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(400))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("book doesn't exist."))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());

        userServiceImpl.deleteUser(100);
    }

    @Test
    public void testUpdateRecordBookNotExist() throws Exception {
    	Map<String, Object> map = new HashMap<>();
        map.put("id", 100);
        map.put("userName", "user100");
        map.put("count", 5);

        MvcResult userResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(map))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();        

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/return/100/100"))
                .andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(400))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("book doesn't exist."))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());

        userServiceImpl.deleteUser(100);
    }

    @Test
    public void testUpdateRecordRecordNotExist() throws Exception {
    	Map<String, Object> userMap = new HashMap<>();
        userMap.put("id", 100);
        userMap.put("userName", "user100");
        userMap.put("count", 5);

        MvcResult userResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(userMap))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();        

        Map<String, Object> bookMap = new HashMap<>();
        bookMap.put("id", 100);
        bookMap.put("isbn", "isbn100");
        bookMap.put("bookName", "book100");
        bookMap.put("author", "author100");
        bookMap.put("publishDate", "2020-10-28");
        bookMap.put("summary", "summary");
        bookMap.put("available", true);


        MvcResult bookResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/addBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(bookMap))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/return/100/100"))
                .andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(400))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("record doesn't exist."))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        userServiceImpl.deleteUser(100);
        bookServiceImpl.deleteBook(100);
    }

    @Test
    public void testAddAndUpdateRecord() throws Exception {
    	Map<String, Object> userMap = new HashMap<>();
        userMap.put("id", 100);
        userMap.put("userName", "user100");
        userMap.put("count", 5);

        MvcResult userResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(userMap))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();        

        Map<String, Object> bookMap = new HashMap<>();
        bookMap.put("id", 100);
        bookMap.put("isbn", "isbn100");
        bookMap.put("bookName", "book100");
        bookMap.put("author", "author100");
        bookMap.put("publishDate", "2020-10-28");
        bookMap.put("summary", "summary");
        bookMap.put("available", true);


        MvcResult bookResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/addBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(bookMap))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


        MvcResult borrowResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/borrow/100/100"))
                .andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("success"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        MvcResult returnResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/return/100/100"))
                .andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("success"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        userServiceImpl.deleteUser(100);
        bookServiceImpl.deleteBook(100);
        recordServiceImpl.deleteAll();

    }

}