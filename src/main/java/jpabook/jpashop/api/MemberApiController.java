package jpabook.jpashop.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jpabook.jpashop.entity.Member;
import jpabook.jpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

	private final MemberService memberService;
	
	public CreateMemberResponse saveMemberV1(Member member) {
		
	}
	
	@Data
	static class CreateMemberResponse {
		private String name;
	}
}
