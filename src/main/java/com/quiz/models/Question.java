package com.quiz.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String body;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id")
    private Test test;

    public Question(String body, Test test) {
        this.body = body;
        this.test = test;
    }

}
