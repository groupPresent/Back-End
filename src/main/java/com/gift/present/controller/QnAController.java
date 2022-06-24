package com.gift.present.controller;

import com.gift.present.dto.qnAdto.QnADetailDto;
import com.gift.present.dto.qnAdto.QnADto;
import com.gift.present.service.QnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    // 질문 작성 API (운영진용)
    @PostMapping("/makequestion")
    public void createQnA(@RequestBody QnADetailDto qnADetailDto) {
        System.out.println(qnADetailDto.getTitle());
        qnAService.createQnA(qnADetailDto);
    }
}
