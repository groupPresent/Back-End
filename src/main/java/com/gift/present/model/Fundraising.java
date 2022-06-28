package com.gift.present.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Fundraising {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Funding funding;

    @Column
    private Long contributorId;

    @Column
    private int money;

    public Fundraising(Funding funding, Long contributorId, int money) {
        this.funding = funding;
        this.contributorId = contributorId;
        this.money = money;
    }
}
