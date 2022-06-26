package com.gift.present.model;

import com.gift.present.dto.fundingdto.FundingRequestDto;
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Anniversary anniversary;

    public Funding(Long userId, String giftName, String giftPhoto, int giftPrice, Anniversary anniversary) {
        this.userId = userId;
        this.giftName = giftName;
        this.giftPhoto = giftPhoto;
        this.giftPrice = giftPrice;
        this.anniversary = anniversary;
    }

    public void update(FundingRequestDto fundingRequestDto, Anniversary anniversary) {
        this.giftName = fundingRequestDto.getGiftName();
        this.giftPhoto = "NoImg";
        this.giftPrice = fundingRequestDto.getGiftPrice();
        this.anniversary = anniversary;
    }
}
