package hello.antproject.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 재고 없음 등 "비즈니스 예외" 처리
    @ExceptionHandler(IllegalStateException.class)
    public String handleIllegalState(IllegalStateException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error/business"; // templates/error/business.html
    }

    // (선택) 그 외 예상 못한 에러를 커스텀 페이지로 보낼 수도 있음
    // 근데 이건 너무 넓게 잡으면 디버깅이 힘들어서 일단 안 넣는 걸 추천
}