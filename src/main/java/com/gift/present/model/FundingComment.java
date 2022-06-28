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
    @JoinColumn(nullable = true)
    private Funding funding;

    @Column
    private String author;

    @Column
    private String content;

    @Column
    private Long fundingID;

    @Column
    private Long commentID;

    public FundingComment(String author, String content, Long fundingID) {
        this.author = author;
        this.fundingID = fundingID;
        this.content = content;
    }

    public void update(CommentDto commentDto) {
        this.author = commentDto.getAuthor();
        this.content = commentDto.getContent();



    }
}
