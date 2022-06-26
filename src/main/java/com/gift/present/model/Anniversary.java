package com.gift.present.model;

import com.gift.present.dto.anniversarydto.AnniversaryRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Anniversary {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @Column
    private String anniversaryName;

    @Column
    private String anniversaryDate;

    @Column
    private String anniversaryContent;

    public Anniversary(User user, String anniversaryName, String anniversaryDate, String anniversaryContent) {
        this.user = user;
        this.anniversaryName = anniversaryName;
        this.anniversaryDate = anniversaryDate;
        this.anniversaryContent = anniversaryContent;
    }

    public void update(AnniversaryRequestDto anniversaryRequestDto) {
        this.anniversaryName = anniversaryRequestDto.getAnniversaryName();
        this.anniversaryDate = anniversaryRequestDto.getAnniversaryDate();
        this.anniversaryContent = anniversaryRequestDto.getAnniversaryContent();
    }

}
