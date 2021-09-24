package jpabook.sbjpashop.service;

import jpabook.sbjpashop.domain.Member;
import jpabook.sbjpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

//    @Autowired
//    private MemberRepository memberRepository;
    private final MemberRepository memberRepository;    //컴파일 시점에 체크가 가능하기 때문에

    // AllArgsConstructor
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //회원 가입
    @Transactional
    public Long join(Member member) {
        //같은 이름인 회원은 x
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        //count 를 세서 가지고 오는 게 더 최적화!
        //이렇게 해도 멀티 스레드로 인한 문제가 있음으로 데이터 베이스에서 name을 unique 제약 조건으로 걸어주어야한다.
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 조회
    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
