package com.gift.present.model;


import com.gift.present.time.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Reviews extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String reviewTitle;

    @Column
    private int reviewStar;

    @Column
    private String reviewContent;

    @Column
    private String reviewPhoto;

    @Column
    private int reviewPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @Column
    private Long fundingId;

    public Reviews(String reviewTitle, String reviewContent, int reviewStar, String reviewPhoto, int reviewPrice, User user, Long fundingId) {
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.reviewStar = reviewStar;
        this.reviewPhoto = reviewPhoto;
        this.reviewPrice = reviewPrice;
        this.user = user;
        this.fundingId = fundingId;
    }


}
