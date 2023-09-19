package com.petti.service.member;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petti.domain.member.AuthVO;
import com.petti.domain.member.MemberVO;
import com.petti.exception.PasswordMisMatchException;
import com.petti.repository.member.AuthRepository;
import com.petti.repository.member.MemberRepository;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private AuthRepository authRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public boolean join(MemberVO vo) {
		if(!new PasswordValidator().isVaild(vo.getMemberPwd())) return false;
		
		else {
			vo.setMemberPwd(passwordEncoder.encode(vo.getMemberPwd()));
			AuthVO authVO = new AuthVO(vo.getMemberId(), "ROLE_MEMBER");
			memberRepository.insert(vo);
			authRepository.insert(authVO);
			return true;
		}
	}

	public static class PasswordValidator{
		private static final String PASSWORD_PATTERN = 
				"^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$";
		
		public boolean isVaild(String password) {
			Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
			Matcher matcher = pattern.matcher(password);
			return matcher.matches();
		}
	}
	
	@Override
	public void modify(MemberVO vo) {
		memberRepository.update(vo);
	}

	@Override
	public MemberVO read(String memberId) {
		return memberRepository.selectById(memberId);
	}

	@Transactional
	@Override
	public void changePassword(Map<String, String> memberMap) {
		String memberId = memberMap.get("memberId");
		String newPwd = memberMap.get("newPwd");
		String currentPwd = memberMap.get("currentPwd");
		log.info(currentPwd);
		log.info(newPwd);
		MemberVO vo = memberRepository.selectById(memberId);
		
		log.info(currentPwd);
		if(!passwordEncoder.matches(currentPwd, vo.getMemberPwd())) {
			throw new PasswordMisMatchException();
		}
		memberRepository.updatePassword(memberId, passwordEncoder.encode(newPwd));
	}

}
