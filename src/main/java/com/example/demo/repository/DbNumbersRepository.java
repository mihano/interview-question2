package com.example.demo.repository;

import com.example.demo.domain.entity.NumbersEntity;
import com.example.demo.domain.exception.NumbersNotFoundException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Database numbers repository which implements @see com.example.demo.repository.NumbersRepository interface. All
 * entries are stored in database with unique id.
 */
@Repository
@Transactional(readOnly = true)
@Profile("!in-memory")
@Slf4j
public class DbNumbersRepository implements NumbersRepository {

    @PersistenceContext
    private EntityManager em;

    @SneakyThrows
    @Override
    public List<Long> getNumbersById(Long id) {
        log.debug("Get numbers from database for id {}", id);
        NumbersEntity numbersEntity = em.find(NumbersEntity.class, id);
        if (numbersEntity == null) {
            log.error("Numbers for id {} not found", id);
            throw new NumbersNotFoundException("Numbers not found");
        }
        return numbersEntity.getNumbers();
    }

    @Override
    @Transactional
    public Long saveNumbers(List<Long> numbers) {
        log.debug("Store numbers {} into database", numbers);

        NumbersEntity entity = new NumbersEntity();
        entity.setNumbers(numbers);

        em.persist(entity);
        em.flush();

        log.debug("Id {}", entity.getId());

        return entity.getId();
    }
}
