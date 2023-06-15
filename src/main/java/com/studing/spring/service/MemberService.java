package com.studing.spring.service;

import com.studing.spring.domain.Member;
import com.studing.spring.repository.MemberRepository;
import com.studing.spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * sgining up.
     */
    public long join (Member member) {
        // check same user.
        validateDuplicateMember(member);

        memberRepository.save(member);


        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent( m -> {
                throw new IllegalStateException("this user already exists.");
            });

    }

    /**
     * get all members
     * @return
     */
    public List<Member> findMembers () {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne (Long memberId) {
        return memberRepository.findById(memberId);
    }
}
