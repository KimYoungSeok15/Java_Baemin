package com.example.shoppingmall.member;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    MemberJpaRepository memberRepository;

    public String join(Member member) {
        memberRepository.save(member);

        return memberRepository
                .findByUserId(member.getUserId())
                .getUserId();
    }

    public boolean checkDuplicateId(String userId) {
        Member existMember
            = memberRepository.findByUserId(userId);

        return existMember != null;
    }

//    public void makeConnection() {
//        memberRepository.makeConnection();
//    }

    public Member findByUserId(LoginDTO loginDTO) {
        return memberRepository.findByUserId(loginDTO.getStringId());
    }

}
