package com.gift.present.service;

import com.gift.present.dto.anniversarydto.AnniversaryRequestDto;
import com.gift.present.model.Anniversary;
import com.gift.present.model.User;
import com.gift.present.repository.AnniversaryRepository;
import com.gift.present.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnniversaryService {
    private final AnniversaryRepository anniversaryRepository;
    private final UserRepository userRepository;

    // 기념일 등록 메소드
    @Transactional
    public void createAnniversary(AnniversaryRequestDto anniversaryRequestDto) {
        User user = userRepository.findById(1L).orElseThrow(
                () -> new IllegalArgumentException("찾는 유저가 없어요")
        );
        Anniversary anniversary = new Anniversary(
                user,
                anniversaryRequestDto.getAnniversaryName(),
                anniversaryRequestDto.getAnniversaryDate(),
                anniversaryRequestDto.getAnniversaryContent()
        );
        anniversaryRepository.save(anniversary);
    }
    // 기념일 수정 메소드
    @Transactional
    public void editAnniversary(AnniversaryRequestDto anniversaryRequestDto, Long anniversaryId) {
        Anniversary anniversary = anniversaryRepository.findById(anniversaryId).orElseThrow(
                () -> new IllegalArgumentException("해당 기념일이 존재하지 않습니다.")
        );
        anniversary.update(anniversaryRequestDto);
    }

    // 기념일 삭제 메소드
    @Transactional
    public void deleteAnniversary(Long anniversaryId) {
        anniversaryRepository.deleteById(anniversaryId);
    }
}
