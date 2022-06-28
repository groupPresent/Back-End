package com.gift.present.service;

import com.gift.present.dto.anniversarydto.AnniversaryInfoDto;
import com.gift.present.dto.userdto.UserDto;
import com.gift.present.dto.userdto.UserInfoResponseDto;
import com.gift.present.model.Anniversary;
import com.gift.present.model.User;
import com.gift.present.repository.AnniversaryRepository;
import com.gift.present.repository.FundingRepository;
import com.gift.present.repository.FundraisingRepository;
import com.gift.present.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AnniversaryRepository anniversaryRepository;
    private final FundingRepository fundingRepository;
    private final FundraisingRepository fundraisingRepository;

    // 임시유저생성
    public void createUser() {
        User user = new User("beomin", "NOIMG", "6/8", "1234", "123-123-123", "남");
        User user2 = new User("mirim", "NOIMG", "5/4", "12345", "123-123", "여");
        userRepository.save(user);
        userRepository.save(user2);
    }

    // 내정보 조회
    public UserInfoResponseDto getMyInfo() {
        List<AnniversaryInfoDto> anniversaryInfoDtoList = new ArrayList<>();
        User user = userRepository.findById(1L).orElseThrow(
                () -> new IllegalArgumentException("해당하는 유저가 존재하지 않습니다.")
        );
        List<Anniversary> anniversaryList = anniversaryRepository.findAllByUser_Id(user.getId());
        for(Anniversary anniversary : anniversaryList) {
            anniversaryInfoDtoList.add(generateAnniversaryInfoDto(anniversary));
        }
        return generateUserInfoResponseDto(user, anniversaryInfoDtoList);
    }


//    public UserDto getUser(Long id) {
//        //주석 처리된 코드가 원래코드 현재는 친구유저가 생성이 안되어 있기 때문에 주석처리하고 내정보호출
//        User user = userRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("해당하는 유저가 존재하지 않습니다.")
//        );
//        //1번유저정보호출(최초에 가상유저생성한것)
////        User user = userRepository.findById(1L).orElseThrow(
////                () -> new IllegalArgumentException("해당하는 유저가 존재하지 않습니다.")
////        );
//        return generateUserDto(user);
//    }

//    public List<UserDto> searchUserByName(String userName){
//        return userRepository.findAllByUserName(userName);
//    }



    // 유저 탈퇴하기
    @Transactional
    public void deleteUser() {
        userRepository.deleteById(1L);
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
