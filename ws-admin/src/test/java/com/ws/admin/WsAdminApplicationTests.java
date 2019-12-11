package com.ws.admin;



import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
                classes = WsAdminApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class WsAdminApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAllFormateurs() throws Exception {


        // Perform the behavior being tested
        String uri = "/formateur/allFormateurs";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        // Extract the response status and body
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        System.out.println(content);


        // Perform standard JUnit assertions on the response
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

    }
    @Test
    public void getFormateurs() throws Exception {


        // Perform the behavior being tested
        String uri = "/formateur/formateurById/70";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        // Extract the response status and body
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        System.out.println(content);


        // Perform standard JUnit assertions on the response
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

    }

}
