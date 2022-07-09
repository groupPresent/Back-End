package com.gift.present.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Goods {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String goodsName;

    @Column
    private String goodsPhoto;

    public Goods(String goodsName, String goodsPhoto) {
        this.goodsName = goodsName;
        this.goodsPhoto = goodsPhoto;
    }
}
