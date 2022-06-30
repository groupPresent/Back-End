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
    private String name;

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

    public User(String userName, String name, String profileImg, String birthDay, String password, String accountNum, String gender) {
        this.userName = userName;
        this.name = name;
        this.profileImg = profileImg;
        this.birthDay = birthDay;
        this.password = password;
        this.accountNum = accountNum;
        this.gender = gender;
    }


    public void setProfileImg(String imgPath) {
        this.profileImg = imgPath;
    }
}
