package hello.antproject.service;

import hello.antproject.domain.member.Member;
import hello.antproject.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Member login(String loginId, String password){
        Optional<Member> optionalMember = memberRepository.findByLoginId(loginId);
        if(optionalMember.isEmpty()){
            return null;
        }
        Member member = optionalMember.get();
        if(member.getPassword().equals(password)){
            return member;
        }
        return null;
    }


}
