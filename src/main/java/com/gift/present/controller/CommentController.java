package com.gift.present.controller;

import com.gift.present.dto.qnAdto.CommentDto;
import com.gift.present.service.FundingCommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor

public class CommentController {
    public final FundingCommentService fundingcommentService;
    // 댓글 조회
    @GetMapping("/user/funding/{fundingId}/fundingComment")
    public ResponseEntity<CommentDto> getComment(@PathVariable Long fundingId){
        return ResponseEntity.ok().body(fundingcommentService.getComment(fundingId));
    }

    // 댓글 입력
    @PostMapping("/user/funding/{fundingId}/comment")
    public void WriteComment(@PathVariable Long fundingId, @RequestBody CommentDto commentDto){
        fundingcommentService.WriteComment(fundingId, commentDto);

    }

    // 댓글 수정
    @PutMapping("/user/funding/{fundingId}/comment/{commentId}")
    public void editComment(@RequestBody CommentDto commentDto, @PathVariable Long commentId){
        fundingcommentService.editComment(commentDto, commentId);
    }

    //댓글 삭제
    @DeleteMapping("/user/funding/{fundingId}/comment/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        fundingcommentService.deleteComment(commentId);
    }

}
