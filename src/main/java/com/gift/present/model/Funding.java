package com.gift.present.model;

import com.gift.present.time.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Funding extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private Long userId;

    @Column
    private String giftName;

    @Column
    private String giftPhoto;

    @Column
    private int giftPrice;

    @Column
    private int fundingPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Anniversary anniversary;

    public Funding(Long userId, String giftName, String giftPhoto, int giftPrice, int fundingPrice) {
        this.userId = userId;
        this.giftName = giftName;
        this.giftPhoto = giftPhoto;
        this.giftPrice = giftPrice;
        this.fundingPrice = fundingPrice;
    }
}
