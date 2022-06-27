package com.gift.present.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.gift.present.time.Timestamped;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Friendship extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @Column
    private Long friendId;

    @Column
    private Boolean favorites;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Anniversary anniversary;
    
    public Friendship(User user, Long friendId, Boolean favorites, Anniversary anniversary) {
		this.user = user;
		this.friendId = friendId;
		this.favorites = favorites;
		this.anniversary = anniversary;
	}

    public void update(Long friendId,Anniversary anniversary) {
        this.friendId = friendId;
        if(this.favorites == true) {
        	this.favorites = false;
        }
        else {
        	this.favorites = true;
        }
        this.anniversary = anniversary;
    }



	

}
