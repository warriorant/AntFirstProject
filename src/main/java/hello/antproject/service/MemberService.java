package hello.antproject.service;

import hello.antproject.domain.member.Member;
import hello.antproject.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member add(Member member){

        validateDuplicateLoginId(member.getLoginId());

        return memberRepository.save(member);
    }

    private void validateDuplicateLoginId(String loginId){
        memberRepository.findByLoginId(loginId)
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });
    }
    public Optional<Member> findById(Long memberId){
        return memberRepository.findById(memberId);
    }

    public Optional<Member> findByLoginId(String loginId){
        return memberRepository.findByLoginId(loginId);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public void clearMember(){
        memberRepository.clearStore();
    }



}
