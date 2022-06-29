package com.gift.present.controller;

import com.gift.present.dto.qnAdto.CommentDto;
import com.gift.present.model.User;
import com.gift.present.security.UserDetailsImpl;
import com.gift.present.service.FundingCommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor

public class CommentController {
    public final FundingCommentService fundingcommentService;
    // 댓글 조회
    @GetMapping("/user/funding/{fundingId}/fundingComment")
    public ResponseEntity<List<CommentDto>> getComment(@PathVariable Long fundingId){
        return ResponseEntity.ok().body(fundingcommentService.getComment(fundingId));
    }

    // 댓글 입력
    @PostMapping("/user/funding/{fundingId}/comment")
    public void WriteComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                             @PathVariable Long fundingId,
                             @RequestBody CommentDto commentDto)
    {
        User user = userDetails.getUser();
        fundingcommentService.WriteComment(fundingId, commentDto, user);

    }

    // 댓글 수정
    @PutMapping("/user/funding/comment/{commentId}")
    public void editComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                            @RequestBody CommentDto commentDto,
                            @PathVariable Long commentId)
    {
        User user = userDetails.getUser();
        fundingcommentService.editComment(commentDto, commentId, user);
    }

    //댓글 삭제
    @DeleteMapping("/user/funding/comment/{commentId}")
    public void deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId) {
        User user = userDetails.getUser();
        fundingcommentService.deleteComment(commentId, user);
    }

}
