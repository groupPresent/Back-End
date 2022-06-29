package com.gift.present.service;

import com.gift.present.dto.anniversarydto.AnniversaryRequestDto;
import com.gift.present.model.Anniversary;
import com.gift.present.model.User;
import com.gift.present.repository.AnniversaryRepository;
import com.gift.present.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnniversaryService {
    private final AnniversaryRepository anniversaryRepository;
    private final UserRepository userRepository;

    // 기념일 등록 메소드
    @Transactional
    public void createAnniversary(AnniversaryRequestDto anniversaryRequestDto, User user) {
        Long userId = user.getId();
        User findUser = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("찾는 유저가 없어요")
        );

        Optional<Anniversary> findAnniversary = anniversaryRepository.findByAnniversaryNameAndUser_Id(anniversaryRequestDto.getAnniversaryName(), userId);
        if(findAnniversary.isPresent() && findAnniversary.get().getAnniversaryName().equals(anniversaryRequestDto.getAnniversaryName())) {
            throw new IllegalStateException("해당 기념일은 이미 존재하는 기념일입니다.");
        }

        Anniversary anniversary = new Anniversary(
                findUser,
                anniversaryRequestDto.getAnniversaryName(),
                anniversaryRequestDto.getAnniversaryDate(),
                anniversaryRequestDto.getAnniversaryContent()
        );

        anniversaryRepository.save(anniversary);
    }
    // 기념일 수정 메소드
    @Transactional
    public void editAnniversary(AnniversaryRequestDto anniversaryRequestDto, Long anniversaryId, User user) {
        Anniversary anniversary = anniversaryRepository.findByIdAndUser_Id(anniversaryId, user.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당 기념일이 존재하지 않거나 유저에게 수정권한이 없습니다.")
        );
        anniversary.update(anniversaryRequestDto);
    }

    // 기념일 삭제 메소드
    @Transactional
    public void deleteAnniversary(Long anniversaryId, User user) {
        Anniversary anniversary = anniversaryRepository.findByIdAndUser_Id(anniversaryId, user.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당 기념일이 존재하지 않거나 유저에게 수정권한이 없습니다.")
        );
        anniversaryRepository.deleteById(anniversary.getId());
    }
}
