package com.example.demo.controller;

import com.example.demo.service.NumbersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * REST API endpoints implementation.
 */
@Validated
@RestController
@RequiredArgsConstructor
public class NumbersController {

    private final NumbersService numbersService;

    /**
     * Get permuted numbers endpoint which should return a random permutation of the numbers array.
     * @param id id of the numbers array
     * @return random permutation of the numbers array
     */
    @GetMapping(path = "/permutation", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Long> getPermutedNumbers(@RequestParam(name = "id") @NotNull @Min(0) Long id) {
        return numbersService.getPermutedNumbers(id);
    }

    /**
     * Post numbers endpoint which should store numbers and return an ID of the entry.
     * @param numbers numbers which should be stored
     * @return should return an ID of the numbers entry
     */
    @PostMapping(path = "/store", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long saveNumbers(@RequestParam(name = "numbers") @NotEmpty List<Long> numbers) {
        return numbersService.saveNumbers(numbers);
    }
}
