package com.empManagement.empAssignement.controller;
import com.empManagement.empAssignement.Services.*;
import com.empManagement.empAssignement.Entities.*;
import com.empManagement.empAssignement.controller.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProjectControllerTest {

    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private ProjectServices projectService;

    @InjectMocks
    private ProjectController projectController;
    Dept d = new Dept(1, "Test", "Test");
    project p1 = new project(1, "Test1", "Test1", d, new HashSet<>());
    project p2 = new project(2, "Test2", "Test2", d, null);
    project p3 = new project(3, "Test3", "Test3", d, null);



    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
    }

    @Test
    public void getAllProjects_success() throws Exception {
        List<project> projects = new ArrayList<>(Arrays.asList(p1,p2,p3));
        Mockito.when(projectService.getAllProject()).thenReturn(projects);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/Project")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].project_name", is("Test3")))
                .andExpect(jsonPath("$[1].project_name", is("Test2")));
    }

    @Test
    public void getProjectById_success() throws Exception {
        Mockito.when(projectService.getProjectById(p1.getProject_id())).thenReturn(p1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/Project/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.project_name", is("Test1")));
    }



    @Test
    public void getProjectsByDepartmentId_success() throws Exception {
        Mockito.when(projectService.getProjectByDeptId(d.getId())).thenReturn(Arrays.asList(p1,p2,p3));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/Project/department/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].project_name", is("Test3")))
                .andExpect(jsonPath("$[1].project_name", is("Test2")));
    }

    @Test
    public void saveProject_success() throws Exception {
        when(projectService.addProject(p1)).thenReturn(p1);
        String content = objectMapper.writeValueAsString(p1);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/Project")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk());
    }


    @Test
    public void saveProjectDeptId_success() throws Exception {
        when(projectService.addProjectWithDeptId(p1, 1)).thenReturn(p1);
        String content = objectMapper.writeValueAsString(p1);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/Project/Dept/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk());
    }
    @Test
    public void saveProjectEmpId_success() throws Exception {
        emp e1=new emp(1,"emp","email",null,null);
        p1.getEmployee().add(e1);
        when(projectService.addProjectWithEmp(p1, 1)).thenReturn(p1);
        String content = objectMapper.writeValueAsString(p1);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/Project/emp/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk());
    }
    @Test
    public void updateProject_success() throws Exception {
        when(projectService.updateDepartmentId (p1.getProject_id(), d.getId())).thenReturn(true);
        String content = objectWriter.writeValueAsString(p1);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.put("/Project/1/updateDepartment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void deleteProject_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/Project/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", notNullValue()))
                .equals("Project Deleted Successfully");

        verify(projectService, times(1)).deleteById(1);
    }
    @Test
    public void deleteAllProjects_success() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/Project")
                        .contentType(MediaType.APPLICATION_JSON))
                .equals("Project Deleted Successfully");

        verify(projectService, times(1)).deleteAllProjects();


    }
}
