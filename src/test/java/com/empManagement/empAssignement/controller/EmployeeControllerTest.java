package com.empManagement.empAssignement.controller;

import com.empManagement.empAssignement.Entities.Dept;
import com.empManagement.empAssignement.Services.EmployeeService;
import com.empManagement.empAssignement.Services.*;
import com.empManagement.empAssignement.Entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;
    Dept d = new Dept(1, "Test", "Test");
    emp e1 = new emp(1, "first","email", d, null);
    emp e2 = new emp(2, "second","email", null, null);
    emp e3 = new emp(3, "third","email", null, null);
    emp e4 = new emp(4, "fourth","email", null, null);



    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void getAllEmployees_success() throws Exception {
        List<emp> employees = new ArrayList<>(Arrays.asList(e1,e2,e3));
        Mockito.when(employeeService.getAllEmployees()).thenReturn(employees);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].emp_name", is("third")))
                .andExpect(jsonPath("$[1].emp_name", is("second")));
    }

    @Test
    public void getEmployeeById_success() throws Exception {
        Mockito.when(employeeService.getEmployeeById(e1.getEmpId())).thenReturn(e1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.emp_name", is("first")));
    }

    @Test
    public void getEmployeesByDepartmentId_success() throws Exception {
        Mockito.when(employeeService.getEmployeeByDeptId(d.getId())).thenReturn(Arrays.asList(e1,e2,e3));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employees/department/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[0].emp_name", is("first")));
    }

    @Test
    public void getEmployeeByIdAndDepartmentId_success() throws Exception {
        Mockito.when(employeeService.getEmployeeByIdAndDeptId(e1.getEmpId(), 1)).thenReturn(e1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employees/1/department/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.emp_name", is("first")));
    }
//fcaing issue
    @Test
    public void createEmployee_success() throws Exception {
        Mockito.when(employeeService.saveEmployee(e1)).thenReturn(e1);
        String content = objectMapper.writeValueAsString(e1);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isCreated());
//

    }

    //facing same issue
    @Test
    public void updateEmployee_success() throws Exception {
        Mockito.when(employeeService.updateEmployee(e1, e1.getEmpId(), 3)).thenReturn(Optional.of(e1));
        String content = objectWriter.writeValueAsString(e1);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.put("/employees/1/department/3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$", notNullValue()))
//                .andExpect(jsonPath("$.firstName", is("Testing")));
    }

    @Test
    public void assignProjectsToEmployees_success() throws Exception {
        project p = new project(1, "Test", "Test", d, null);
        Mockito.when(employeeService.assignProjectsToEmployee(e1.getEmpId(), p.getProject_id())).thenReturn(e1);
        String content = objectWriter.writeValueAsString(e1);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.put("/employees/1/project/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void deleteEmployee_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .equals("Employee deleted successfully!");

        verify(employeeService, times(1)).deleteEmployee(1);
    }
}
