package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
class Jpashop11ApplicationTests {
	
//	@Autowired
//	MemberRepository memberRepository;
//	
//	@Test
//	@Transactional
//	@Rollback(false)
//	public void testMember() {
//		Member member = new Member();
//		member.setUserName("kim");
//		
//		Long saveId = memberRepository.save(member).getId();
//		Member findMember = memberRepository.findOneById(saveId);
//		
//		System.out.println("=============");
//		
//		Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//		Assertions.assertThat(findMember.getUserName()).isEqualTo(member.getUserName());
//		Assertions.assertThat(findMember).isEqualTo(member);
//	}
	
}
