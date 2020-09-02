package com.example.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Objects;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NumbersControllerTest {

    @Autowired
    private MockMvc mvc;

    /**
     * When store endpoint is called with numbers, numbers should be successfully stored and created response should be
     * returned.
     */
    @SneakyThrows
    @Test
    public void saveNumbers() {
        mvc.perform(post("/store?numbers=1,2,3,4,5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isNumber());
    }

    /**
     * When store endpoint is called with numbers of invalid type, bad request response should be returned.
     */
    @SneakyThrows
    @Test
    public void saveNumbersInvalidType() {
        mvc.perform(post("/store?numbers=a,b,c,d,e")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(r -> assertEquals("Invalid argument types in the request",
                        Objects.requireNonNull(r.getResponse().getErrorMessage())));
    }

    /**
     * When store endpoint is called without numbers parameter, bad request response should be returned.
     */
    @SneakyThrows
    @Test
    public void saveNumbersMissingNumbersParameter() {
        mvc.perform(post("/store")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(r -> assertEquals("Required List parameter 'numbers' is not present",
                        Objects.requireNonNull(r.getResponse().getErrorMessage())));
    }

    /**
     * When store endpoint is called with empty numbers parameter, bad request response should be returned.
     */
    @SneakyThrows
    @Test
    public void saveNumbersEmptyNumbersParameter() {
        mvc.perform(post("/store?numbers=")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(r -> assertEquals("saveNumbers.numbers: must not be empty",
                        Objects.requireNonNull(r.getResponse().getErrorMessage())));
    }

    /**
     * When permutation endpoint is called with id of the stored numbers, random permutation with OK response should be
     * returned.
     */
    @SneakyThrows
    @Test
    public void getPermutedNumbers() {
        MvcResult result = mvc.perform(post("/store?numbers=1,2,3,4,5")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        Long id = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Long.class);

        mvc.perform(get("/permutation?id={id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$", containsInAnyOrder(1, 2, 3, 4, 5)));
    }

    /**
     * When permutation endpoint is called with id which not exists, not found response should be
     * returned.
     */
    @SneakyThrows
    @Test
    public void getPermutedNumbersNumbersNotFound() {
        MvcResult result = mvc.perform(post("/store?numbers=1,2,3,4,5")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        Long id = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Long.class);

        mvc.perform(get("/permutation?id={id}", id + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(r -> assertEquals("Numbers not found",
                        Objects.requireNonNull(r.getResponse().getErrorMessage())));
    }

    /**
     * When permutation endpoint is called without id of the stored numbers, bad request response should be
     * returned.
     */
    @SneakyThrows
    @Test
    public void getPermutedNumbersMissingNumbersParameter() {
        mvc.perform(get("/permutation")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(r -> assertEquals("Required Long parameter 'id' is not present",
                        Objects.requireNonNull(r.getResponse().getErrorMessage())));
    }

    /**
     * When permutation endpoint is called with invalid id value, bad request response should be
     * returned.
     */
    @SneakyThrows
    @Test
    public void getPermutedNumbersInvalidIdValue() {
        mvc.perform(get("/permutation?id=-1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(r -> assertEquals("getPermutedNumbers.id: must be greater than or equal to 0",
                        Objects.requireNonNull(r.getResponse().getErrorMessage())));
    }

    /**
     * When permutation endpoint is called with invalid id value, bad request response should be
     * returned.
     */
    @SneakyThrows
    @Test
    public void getPermutedNumbersInvalidIdType() {
        mvc.perform(get("/permutation?id=abc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(r -> assertEquals("Invalid argument types in the request",
                        Objects.requireNonNull(r.getResponse().getErrorMessage())));
    }
}
