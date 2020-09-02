package com.example.demo.service;

import com.example.demo.repository.NumbersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Service in which business logic for saving and numbers permutation is implemented. Implements
 * @see com.example.demo.service.NumbersService interface.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NumbersServiceImpl implements NumbersService {

    private final NumbersRepository numbersRepository;

    @Override
    public List<Long> getPermutedNumbers(Long id) {
        List<Long> numbers = numbersRepository.getNumbersById(id);
        Collections.shuffle(numbers);
        return numbers;
    }

    @Override
    public Long saveNumbers(List<Long> numbers) {
        return numbersRepository.saveNumbers(numbers);
    }
}
