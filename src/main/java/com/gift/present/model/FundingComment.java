package com.gift.present.model;

import com.gift.present.dto.qnAdto.CommentDto;
import com.gift.present.time.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FundingComment extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Funding funding;

    @Column
    private String author;

    @Column
    private String content;

    //@Column
    //private Long fundingID;


    public FundingComment(Funding funding, String author, String content){ //, Long fundingID) {
        this.funding = funding;
        this.author = author;
        this.content = content;

    }

    public void update(String name, CommentDto commentDto) {
        this.author = name;
        this.content = commentDto.getContent();



    }
}
