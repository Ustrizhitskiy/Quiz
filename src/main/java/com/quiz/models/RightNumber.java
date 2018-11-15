package com.quiz.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "right_numbers")
public class RightNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String generated;

    private int questions;

    private int number;

    public RightNumber(String generated, int questions, int number) {
        this.generated = generated;
        this.questions = questions;
        this.number = number;
    }

}
