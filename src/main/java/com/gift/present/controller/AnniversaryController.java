package com.gift.present.controller;


import com.gift.present.dto.anniversarydto.AnniversaryRequestDto;
import com.gift.present.model.User;
import com.gift.present.security.UserDetailsImpl;
import com.gift.present.service.AnniversaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AnniversaryController {
    private final AnniversaryService anniversaryService;

    // 기념일 등록
    @PostMapping("/user/anniversary")
    public void createAnniversary(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody AnniversaryRequestDto anniversaryRequestDto) {
        User user = userDetails.getUser();
        anniversaryService.createAnniversary(anniversaryRequestDto, user);
    }

    // 기념일 수정
    @PutMapping("/user/anniversary/{anniversaryId}")
    public void editAnniversary(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                @RequestBody AnniversaryRequestDto anniversaryRequestDto,
                                @PathVariable Long anniversaryId)
    {
        User user = userDetails.getUser();
        anniversaryService.editAnniversary(anniversaryRequestDto, anniversaryId, user);
    }

    // 기념일 삭제
    @DeleteMapping("/user/anniversary/{anniversaryId}")
    public void deleteAnniversary(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long anniversaryId) {
        User user = userDetails.getUser();
        anniversaryService.deleteAnniversary(anniversaryId, user);
    }
}
