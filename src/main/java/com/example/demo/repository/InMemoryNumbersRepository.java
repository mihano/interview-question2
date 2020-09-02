package com.example.demo.repository;

import com.example.demo.domain.exception.NumbersNotFoundException;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory numbers repository which implements @see com.example.demo.repository.NumbersRepository interface. All
 * entries are stored in concurrent map with unique id.
 */
@Repository
@Slf4j
public class InMemoryNumbersRepository implements NumbersRepository {

    private static final ConcurrentMap<Long, List<Long>> STORAGE = new ConcurrentHashMap<>();
    private static final AtomicLong STORAGE_ID = new AtomicLong(0);

    @SneakyThrows
    @Override
    public List<Long> getNumbersById(@NonNull Long id) {
        log.debug("Get numbers from in-memory storage for id {}", STORAGE_ID);
        List<Long> numbers = STORAGE.get(id);
        if (numbers == null) {
            log.error("Numbers for id {} not found", STORAGE_ID);
            throw new NumbersNotFoundException("Numbers not found");
        }
        return numbers;
    }

    @Override
    public Long saveNumbers(List<Long> numbers) {
        final Long id = STORAGE_ID.getAndIncrement();
        log.debug("Store numbers {} into in-memory storage under id {}", numbers, id);
        STORAGE.put(id, numbers);
        return id;
    }
}
