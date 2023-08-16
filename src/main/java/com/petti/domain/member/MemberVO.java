package com.petti.domain.member;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String email;
	private boolean enabled;  
	
	private LocalDateTime regDate; 
	private LocalDateTime updateDate;

	private List<AuthVO> authList;
}