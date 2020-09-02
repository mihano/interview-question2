package com.example.demo.service;

import java.util.List;

/**
 * Interface defining methods for saving and numbers permutation.
 */
public interface NumbersService {

    /**
     * Retrieve numbers by id and randomly permute them.
     * @param id id of the numbers record
     * @return permuted numbers
     */
    List<Long> getPermutedNumbers(Long id);

    /**
     * Save numbers and returns generated id.
     * @param numbers numbers to be saved
     * @return generated id of the numbers record
     */
    Long saveNumbers(List<Long> numbers);
}
