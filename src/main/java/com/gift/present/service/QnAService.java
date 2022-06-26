package com.gift.present.service;

import com.gift.present.dto.qnAdto.QnADetailDto;
import com.gift.present.dto.qnAdto.QnADto;
import com.gift.present.dto.qnAdto.QnARequestDto;
import com.gift.present.model.QnA;
import com.gift.present.repository.QnARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QnAService {
    private final QnARepository qnARepository;

    // 자주묻는 질문 목록 조회
    public List<QnADto> getQnAs() {
        List<QnADto> qnADtoList = new ArrayList<>();

        List<QnA> qnAList = qnARepository.findAll();
        for(QnA qnA : qnAList) {
            qnADtoList.add(generateQnADto(qnA));
        }
        return qnADtoList;
    }

    // 자주묻는 질문 목록 세부 조회
    public QnADetailDto getQnADetail(Long questionId) {
        QnA qnA = qnARepository.findById(questionId).orElseThrow(
                () -> new IllegalStateException("해당하는 QnA가 존재하지 않습니다.")
        );
        return generateQnADetailDto(qnA);
    }

    // 질문 작성
    public void createQnA(QnARequestDto qnARequestDto) {
        QnA qnA = new QnA(qnARequestDto.getQuestion(), qnARequestDto.getAnswer());
        qnARepository.save(qnA);
    }


    // QnA의 Dto 변환 과정
    public QnADto generateQnADto(QnA qnA) {
        return QnADto.builder()
                .question(qnA.getQuestion())
                .viewCnt(5) // 일단 적어놓은거 예시를 위해
                .build();
    }

    // QnADetailDto 생성 메소드
    public QnADetailDto generateQnADetailDto(QnA qnA) {
        return QnADetailDto.builder()
                .question(qnA.getQuestion())
                .answer(qnA.getAnswer())
                .viewCnt(5)
                .build();
    }
}
