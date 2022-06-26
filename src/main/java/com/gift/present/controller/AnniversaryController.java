package com.gift.present.controller;


import com.gift.present.dto.anniversarydto.AnniversaryRequestDto;
import com.gift.present.service.AnniversaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AnniversaryController {
    private final AnniversaryService anniversaryService;

    // 기념일 등록
    @PostMapping("/user/anniversary")
    public void createAnniversary(@RequestBody AnniversaryRequestDto anniversaryRequestDto) {
        // User
        anniversaryService.createAnniversary(anniversaryRequestDto);
    }

    // 기념일 수정
    @PutMapping("/user/anniversary/{anniversaryId}")
    public void editAnniversary(@RequestBody AnniversaryRequestDto anniversaryRequestDto, @PathVariable Long anniversaryId) {
        // User
        anniversaryService.editAnniversary(anniversaryRequestDto, anniversaryId);
    }

    // 기념일 삭제
    @DeleteMapping("/user/anniversary/{anniversaryId}")
    public void deleteAnniversary(@PathVariable Long anniversaryId) {
        // User
        anniversaryService.deleteAnniversary(anniversaryId);
    }
}
