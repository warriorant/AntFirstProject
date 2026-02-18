package hello.antproject.web.member;

import hello.antproject.domain.member.Member;
import hello.antproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/add")
    public String MemberForm(@ModelAttribute("member") MemberSaveForm Form){
        return "members/addForm";
    }

    @PostMapping("/add")
    public String addMember(@Validated @ModelAttribute("member") MemberSaveForm Form , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "members/addForm";
        }
        Member member=new Member();
        member.setName(Form.getName());
        member.setLoginId(Form.getLoginId());
        member.setPassword(Form.getPassword());
        memberService.add(member);
        log.info("현재상태={}",Form.getLoginId());
        return "redirect:/home";
    }

}
