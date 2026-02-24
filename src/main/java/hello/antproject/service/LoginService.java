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
        Member member = optionalMember.get();//이게 optional쓰면 해줘야되는건데 껍데기를 한번 벗겨주는 작업임 즉 optionalMember의 껍데기 한번 벗겨서 Member객체로 꺼내고 저장하라는 뜻
        if(member.getPassword().equals(password)){
            return member;
        }
        return null;
    }
}
