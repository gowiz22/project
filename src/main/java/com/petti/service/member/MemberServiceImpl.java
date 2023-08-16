package com.petti.service.member;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petti.domain.member.AuthVO;
import com.petti.domain.member.MemberVO;
import com.petti.repository.member.AuthRepository;
import com.petti.repository.member.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private AuthRepository authRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public void join(MemberVO vo) {
		vo.setMemberPwd(passwordEncoder.encode(vo.getMemberPwd()));
		AuthVO authVO = new AuthVO(vo.getMemberId(), "ROLE_MEMBER");
		memberRepository.insert(vo);
		authRepository.insert(authVO);
	}

	@Override
	public void modify(MemberVO vo) {
		memberRepository.update(vo);
	}

	@Override
	public MemberVO read(String memberId) {
		return memberRepository.selectById(memberId);
	}

	@Override
	public void chagePassword(Map<String, String> memberMap) {
		String memberId = memberMap.get("memberId");
		String newPwd = memberMap.get("newPwd");
		String currentPwd = memberMap.get("currentPwd");
		MemberVO vo = memberRepository.selectById(memberId);
		if(!passwordEncoder.matches(currentPwd, vo.getMemberPwd())) {
			throw new PasswordMisMatchException();
		}
		memberRepository.updatePassword(memberId, passwordEncoder.encode(newPwd));
	}

}
