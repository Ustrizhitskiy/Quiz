package com.quiz.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String level;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    public Test(String level, Category category) {
        this.level = level;
        this.category = category;
    }

/*    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "testsOfUser")
    private List<User> usersOfTest;*/

    public Long getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

    public Category getCategory() {
        return category;
    }

}
