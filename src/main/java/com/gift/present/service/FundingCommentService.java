package com.gift.present.service;

import com.gift.present.dto.qnAdto.CommentDto;
import com.gift.present.model.Funding;
import com.gift.present.model.FundingComment;
import com.gift.present.model.User;
import com.gift.present.repository.FundingCommentRepository;
import com.gift.present.repository.FundingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FundingCommentService {
    private final FundingCommentRepository fundingCommentRepository;
    private final FundingRepository fundingRepository;

    // 댓글 조회
    public List<CommentDto> getComment(Long fundingId) {
        List<CommentDto> commentDtoList = new ArrayList<>();
        List<FundingComment> commentList = fundingCommentRepository.findAllByFunding_Id(fundingId);
        for(FundingComment fundingComment : commentList) {
            commentDtoList.add(generateCommentDto(fundingComment));
        }
        return commentDtoList;
    }

    private CommentDto generateCommentDto(FundingComment comment) {
        return CommentDto.builder()
                .author(comment.getAuthor())
                .content(comment.getContent())
                .build();
    }

    //댓글 작성
    @Transactional
    public void WriteComment(Long fundingId, CommentDto commentDto, User user) {
        Funding funding = fundingRepository.findById(fundingId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 펀딩이 존재하지 않습니다.")
        );
        FundingComment fundingcomment =  new FundingComment(funding, user.getName(), commentDto.getContent());
        fundingCommentRepository.save(fundingcomment);
    }

    // 댓글 수정
    @Transactional
    public void editComment(CommentDto commentDto, Long commentId, User user) {
        String userName = user.getName();
        FundingComment fundingComment = fundingCommentRepository.findByIdAndAuthor(commentId, userName).orElseThrow(
                () -> new IllegalArgumentException("수정할 댓글이 존재하지 않거나 수정 권한이 없습니다.")
        );
        fundingComment.update(userName, commentDto);
    }

    //댓글 삭제
    @Transactional
    public void deleteComment(Long commentId, User user) {
        String userName = user.getName();
        fundingCommentRepository.findByIdAndAuthor(commentId, userName).orElseThrow(
                () -> new IllegalArgumentException("삭제할 댓글이 존재하지 않거나 삭제 권한이 없습니다.")
        );
        fundingCommentRepository.deleteByIdAndAuthor(commentId, userName);
    }
}
