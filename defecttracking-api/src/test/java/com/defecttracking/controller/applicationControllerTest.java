package com.defecttracking.controller;

import com.defecttracking.model.ApplicationVO;
import com.defecttracking.service.ApplicationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.hamcrest.Matchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class applicationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ApplicationService applicationService;

    @Test
    public void testGetApplications() throws Exception{
        List<ApplicationVO> applicationVOS = new ArrayList<>();
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(1L);
        applicationVO.setApplicationName("SpringBoot");
        applicationVO.setOwner("Emexo");
        applicationVO.setDescription("Spring Boot Application");
        applicationVOS.add(applicationVO);

        when(applicationService.findAll()).thenReturn(applicationVOS);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }
    @Test
    public void testGetApplication1() throws Exception{
        List<ApplicationVO> applicationVOS = new ArrayList<>();
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(1L);
        applicationVO.setApplicationName("SpringBoot");
        applicationVO.setOwner("Emexo");
        applicationVO.setDescription("Spring Boot Application");
        applicationVOS.add(applicationVO);

        when(applicationService.findAll()).thenReturn(applicationVOS);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect((ResultMatcher) jsonPath("$",Matchers.hasSize(1))).andDo(print());

    }

    @Test
    public void testGetApplication2() throws Exception{
        List<ApplicationVO> applicationVOS = new ArrayList<>();
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(1L);
        applicationVO.setApplicationName("SpringBoot");
        applicationVO.setOwner("Emexo");
        applicationVO.setDescription("Spring Boot Application");
        applicationVOS.add(applicationVO);

        when(applicationService.findAll()).thenReturn(applicationVOS);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    public void testGetApplication3() throws Exception{
        List<ApplicationVO> applicationVOS = new ArrayList<>();
        when(applicationService.findAll()).thenReturn(applicationVOS);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(),response.getStatus());

    }

    @Test
    public void testGetApplication4() throws Exception{
        List<ApplicationVO> applicationVOS = new ArrayList<>();
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(1L);
        applicationVO.setApplicationName("SpringBoot");
        applicationVO.setOwner("Emexo");
        applicationVO.setDescription("Spring Boot Application");
        applicationVOS.add(applicationVO);
        when(applicationService.findAll()).thenThrow(new NullPointerException());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isInternalServerError());
    }

    @Test
    public void TestGetById() throws Exception {
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(1L);
        applicationVO.setApplicationName("SpringBoot");
        applicationVO.setOwner("Emexo");
        applicationVO.setDescription("Spring Boot Application");

        when(applicationService.findById(anyLong())).thenReturn(applicationVO);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications/1l")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    public void TestGetByIdNull() throws Exception {
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(1L);
        applicationVO.setApplicationName("SpringBoot");
        applicationVO.setOwner("Emexo");
        applicationVO.setDescription("Spring Boot Application");

        when(applicationService.findById(anyLong())).thenThrow(new NullPointerException());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications/1l")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isInternalServerError());
    }
    @Test
    public void TestGetByNotFound() throws Exception {
        //ApplicationVO applicationVO = new ApplicationVO();
        when(applicationService.findById(anyLong())).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications/1l")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
    }


    @Test
    public void TestGetByID1() throws Exception{
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(1L);
        applicationVO.setApplicationName("SpringBoot");
        applicationVO.setOwner("Emexo");
        applicationVO.setDescription("Spring Boot Application");
        when(applicationService.findById(anyLong())).thenReturn(applicationVO);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications/1l")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }

    @Test
    public void TestGetByID2() throws Exception{
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(1L);
        applicationVO.setApplicationName("SpringBoot");
        applicationVO.setOwner("Emexo");
        applicationVO.setDescription("Spring Boot Application");
        when(applicationService.findById(anyLong())).thenReturn(applicationVO);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications/1l")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect((ResultMatcher) jsonPath("$",Matchers.hasSize(1))).andDo(print());
    }


    @Test
    public void TestGetByName() throws Exception {
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(1L);
        applicationVO.setApplicationName("SpringBoot");
        applicationVO.setOwner("Emexo");
        applicationVO.setDescription("Spring Boot Application");

        when(applicationService.findByName(anyString())).thenReturn(applicationVO);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications/name?name=SpringBoot")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }
    @Test
    public void TestGetByNameNull() throws Exception {
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(1L);
        applicationVO.setApplicationName("SpringBoot");
        applicationVO.setOwner("Emexo");
        applicationVO.setDescription("Spring Boot Application");

        when(applicationService.findByName(anyString())).thenThrow(new NullPointerException());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications/name?name=SpringBoot")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isInternalServerError());
    }
    @Test
    public void TestGetByNameNotFound() throws Exception {
       // ApplicationVO applicationVO = null;

        when(applicationService.findByName(anyString())).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications/name?name=SpringBoot")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
    }

    @Test
    public void TestGetByName1() throws Exception{
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(1L);
        applicationVO.setApplicationName("SpringBoot");
        applicationVO.setOwner("Emexo");
        applicationVO.setDescription("Spring Boot Application");
        when(applicationService.findByName(anyString())).thenReturn(applicationVO);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications/name?name=SpringBoot")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }

    @Test
    public void TestGetByName2() throws Exception{
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(1L);
        applicationVO.setApplicationName("SpringBoot");
        applicationVO.setOwner("Emexo");
        applicationVO.setDescription("Spring Boot Application");
        when(applicationService.findByName(anyString())).thenReturn(applicationVO);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications/name?name=SpringBoot")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect((ResultMatcher) jsonPath("$",Matchers.hasSize(1))).andDo(print());
    }

    public String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TestGetPost() throws Exception{
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(1L);
        applicationVO.setApplicationName("SpringBoot");
        applicationVO.setOwner("Emexo");
        applicationVO.setDescription("Spring Boot Application");
        when(applicationService.save(any())).thenReturn(applicationVO);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/applications")
                .content(asJsonString(applicationVO)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    public void TestGetPostNullPointer() throws Exception{
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(1L);
        applicationVO.setApplicationName("SpringBoot");
        applicationVO.setOwner("Emexo");
        applicationVO.setDescription("Spring Boot Application");
        when(applicationService.save(any())).thenThrow(new NullPointerException());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/applications")
                .content(asJsonString(applicationVO)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isInternalServerError());
    }
    @Test
    public void TestGetPostNotFound() throws Exception{
        when(applicationService.save(any())).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/applications")
                .content(asJsonString(null)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
    }

    @Test
    public void TestGetPut() throws Exception{
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(1L);
        applicationVO.setApplicationName("SpringBoot");
        applicationVO.setOwner("Emexo");
        applicationVO.setDescription("Spring Boot Application");
        when(applicationService.save(any())).thenReturn(applicationVO);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/applications")
                .content(asJsonString(applicationVO)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    public void TestGetPutNullPointer() throws Exception{
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(1L);
        applicationVO.setApplicationName("SpringBoot");
        applicationVO.setOwner("Emexo");
        applicationVO.setDescription("Spring Boot Application");
        when(applicationService.save(any())).thenThrow(new NullPointerException());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/applications")
                .content(asJsonString(applicationVO)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isInternalServerError());
    }
    @Test
    public void TestGetPutNotFound() throws Exception{
        when(applicationService.save(any())).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/applications")
                .content(asJsonString(null)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
    }

    @Test
    public void TestDelete() throws Exception{
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(1L);
        applicationVO.setApplicationName("SpringBoot");
        applicationVO.setOwner("Emexo");
        applicationVO.setDescription("Spring Boot Application");
        when(applicationService.delete(anyLong())).thenReturn("Deleted");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/applications/id?id=1l")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }
    @Test
    public void TestDeleteException() throws Exception{
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(1L);
        applicationVO.setApplicationName("SpringBoot");
        applicationVO.setOwner("Emexo");
        applicationVO.setDescription("Spring Boot Application");
        when(applicationService.delete(anyLong())).thenThrow(new NullPointerException());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/applications/id?id=1l")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isInternalServerError());
    }

}
