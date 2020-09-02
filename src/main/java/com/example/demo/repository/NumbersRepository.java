package com.example.demo.repository;

import java.util.List;

/**
 * Numbers repository interface.
 */
public interface NumbersRepository {

    /**
     * Get array of numbers by given id from data storage.
     * @param id id of the array of numbers
     * @return array of numbers
     */
    List<Long> getNumbersById(Long id);

    /**
     * Save numbers in data storage and return generated id of the entry.
     * @param numbers numbers to be saved
     * @return generated id of the saved numbers
     */
    Long saveNumbers(List<Long> numbers);
}
