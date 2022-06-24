package com.gift.present.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class QnA {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String answer;

    @Column
    private String question;

    public QnA(String question, String answer) {
        this.answer = answer;
        this.question = question;
    }
}
