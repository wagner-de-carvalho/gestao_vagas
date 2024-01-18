package br.com.acme.gestao_vagas.modules.company.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.acme.gestao_vagas.modules.company.dto.CreateJobDTO;

public class CreateJobControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @DisplayName("Should be able to create a new job")
    public void should_be_abte_to_create_a_new_job() throws Exception {
        var createJobDTO = CreateJobDTO
                .builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();

        mvc.perform(MockMvcRequestBuilders.post("/company/job")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJSON(createJobDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private static String objectToJSON(Object object) {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
