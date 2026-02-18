package hello.antproject.web;

import hello.antproject.domain.member.Member;
import hello.antproject.web.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String main(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);// false를 하는 이유는 세션 생성을 하려는게 아니기때문 오히려 생성시 의도가 달라짐

        // 로그인 안한 상태
        if (session == null) {
            return "index";  // 로그인/회원가입 화면
        }

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if (loginMember == null) {
            return "index";  // 로그인/회원가입 화면
        }
       return "home";
    }
}
