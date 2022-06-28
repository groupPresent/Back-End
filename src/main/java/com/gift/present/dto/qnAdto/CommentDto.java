package com.gift.present.dto.qnAdto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CommentDto {
    private String author;
    private String content;
}
