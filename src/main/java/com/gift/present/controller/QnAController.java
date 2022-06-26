package com.gift.present.controller;

import com.gift.present.dto.qnAdto.QnADetailDto;
import com.gift.present.dto.qnAdto.QnADto;
import com.gift.present.dto.qnAdto.QnARequestDto;
import com.gift.present.service.QnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QnAController {
    private final QnAService qnAService;

    // 자주묻는 질문 목록 조회
    @GetMapping("/question")
    public ResponseEntity<List<QnADto>> getAllQnA() {
        return ResponseEntity.ok().body(qnAService.getQnAs());
    }

    // 자주묻는 질문 목록 세부조회
    @GetMapping("/question/{questionId}")
    public ResponseEntity<QnADetailDto> getQnADetail(@PathVariable Long questionId) {
        return ResponseEntity.ok().body(qnAService.getQnADetail(questionId));
    }


    // 질문 작성 API (운영진용)
    @PostMapping("/makequestion")
    public void createQnA(@RequestBody QnARequestDto qnARequestDto) {
        qnAService.createQnA(qnARequestDto);
    }
}
