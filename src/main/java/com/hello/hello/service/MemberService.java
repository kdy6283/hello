package com.hello.hello.service;

import com.hello.hello.domain.Member;
import com.hello.hello.repository.MemberRepository;
import com.hello.hello.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
    * 회원 가입
    * */
    public Long join(Member member){
        //같은 이름 중복회원X
        validateDuplicateMeber(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMeber(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->  {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     *
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public  Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
