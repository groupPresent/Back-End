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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    public Reviews(String reviewTitle, String reviewContent, int reviewStar, String reviewPhoto) {
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.reviewStar = reviewStar;
        this.reviewPhoto = reviewPhoto;

    }


}
