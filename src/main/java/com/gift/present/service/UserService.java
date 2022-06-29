package com.gift.present.service;

import com.gift.present.dto.anniversarydto.AnniversaryInfoDto;
import com.gift.present.dto.userdto.UserInfoResponseDto;
import com.gift.present.dto.userdto.UserRequestDto;
import com.gift.present.model.Anniversary;
import com.gift.present.model.User;
import com.gift.present.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final AnniversaryRepository anniversaryRepository;
    private final FundingRepository fundingRepository;
    private final FundraisingRepository fundraisingRepository;
    private final FundingCommentRepository fundingCommentRepository;


    // 임시 회원가입
    @Transactional
    public void createUser(UserRequestDto userRequestDto, MultipartFile profileImg) {
        String enPassword = bCryptPasswordEncoder.encode(userRequestDto.getPassword());
        User user = new User(userRequestDto.getUserName(),
                userRequestDto.getName(),
                "NOIMG",
                userRequestDto.getBirthDay(),
                enPassword,
                userRequestDto.getAccountNum(),
                userRequestDto.getGender()
        );
        userRepository.save(user);
    }

    // 내정보 조회
    public UserInfoResponseDto getMyInfo(User user) {
        Long userId = user.getId();
        List<AnniversaryInfoDto> anniversaryInfoDtoList = new ArrayList<>();
        userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 유저가 존재하지 않습니다.")
        );
        List<Anniversary> anniversaryList = anniversaryRepository.findAllByUser_Id(userId);
        for(Anniversary anniversary : anniversaryList) {
            anniversaryInfoDtoList.add(generateAnniversaryInfoDto(anniversary));
        }
        return generateUserInfoResponseDto(user, anniversaryInfoDtoList);
    }

    // 유저 탈퇴하기
    @Transactional
    public void deleteUser(User user) {
        Long userId = user.getId();
        fundingCommentRepository.deleteAllByAuthor(user.getName());
        fundraisingRepository.deleteAllByContributorId(userId);
        fundingRepository.deleteAllByUserId(userId);
        anniversaryRepository.deleteAllByUserId(userId);
        userRepository.deleteById(userId);
    }

    // UserInfoResponseDto 생성 메소드
    public UserInfoResponseDto generateUserInfoResponseDto(User user, List<AnniversaryInfoDto> anniversaryInfoDtoList) {
        return UserInfoResponseDto.builder()
                .userPhoto(user.getProfileImg())
                .userName(user.getUserName())
                .birthDay(user.getBirthDay())
                .accountNum(user.getAccountNum())
                .anniversaryInfoList(anniversaryInfoDtoList)
                .build();
    }

    // AnniversaryInfoDto 생성 메소드
    public AnniversaryInfoDto generateAnniversaryInfoDto(Anniversary anniversary) {
        return AnniversaryInfoDto.builder()
                .name(anniversary.getAnniversaryName())
                .date(anniversary.getAnniversaryDate())
                .anniversaryRemains("D-5")
                .build();
    }

}
