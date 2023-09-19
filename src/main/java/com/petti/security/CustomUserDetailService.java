package com.petti.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.petti.domain.member.MemberVO;
import com.petti.repository.member.MemberRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO vo = memberRepository.read(username);
			if(vo == null) {
				throw new UsernameNotFoundException("회원 정보를 찾을 수 없습니다. - " +username);
			}
		return new CustomUser(vo);
	}

}
