package jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.entity.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //final 을 달고있는 변수만 생성자를 만듬
public class MemberService {
	private final MemberRepository memberRepository;
	
	//회원 가입
	@Transactional
	public Long join(Member member) {
		validateDuplicateMember(member);
		
		memberRepository.save(member);
		return member.getId();
	}
	
	@Transactional
	public void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다");
		}
	}
	
	//회원 전체 조회
	@Transactional(readOnly = true)
	public List<Member>  findMembers(){
		return memberRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
}
