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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

}
