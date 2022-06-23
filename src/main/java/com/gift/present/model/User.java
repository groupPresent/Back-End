package com.gift.present.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String userName;

    @Column
    private String profileImg;

    @Column
    private String birthDay;

    @Column
    private String password;

    @Column
    private String accountNum;

    @Column
    private String gender;


}
