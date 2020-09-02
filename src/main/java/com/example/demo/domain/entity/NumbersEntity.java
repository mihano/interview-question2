package com.example.demo.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Entity to store numbers.
 */
@Entity(name = "numbers")
@Table(name = "numbers")
@Data
@NoArgsConstructor
public class NumbersEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    private List<Long> numbers;
}
