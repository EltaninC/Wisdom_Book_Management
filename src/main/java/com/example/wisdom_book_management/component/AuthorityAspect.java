package com.example.wisdom_book_management.component;

import com.example.wisdom_book_management.utils.ResultUtils;
import com.example.wisdom_book_management.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static com.example.wisdom_book_management.component.ResultEnum.Limited_Authority;


@Aspect
@Component
public class AuthorityAspect {

    @Pointcut("execution (* com.example.wisdom_book_management.controller.BookController.UpdateBook(..)) || " +
            "execution (* com.example.wisdom_book_management.controller.BookController.DeleteBook(..)) || " +
            "execution (* com.example.wisdom_book_management.controller.BookController.AddBook(..)) ||" +
            "execution (* com.example.wisdom_book_management.controller.TypeController.UpdateType(..)) ||" +
            "execution (* com.example.wisdom_book_management.controller.TypeController.InsertType(..)) ||" +
            "execution (* com.example.wisdom_book_management.controller.TypeController.DeleteType(..)) ||" +
            "execution (* com.example.wisdom_book_management.controller.BorrowController.GetBorrow(..)) ||" +
            "execution (* com.example.wisdom_book_management.controller.UserController.*User(..)) ||" +
            "execution (* com.example.wisdom_book_management.controller.PropertyController.*A(..))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object AroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        TokenUtil.isAdmin(request);
        if(TokenUtil.isAdmin(request)){
            return joinPoint.proceed();
        }
        return ResultUtils.Err(Limited_Authority.getCode(), Limited_Authority.getMsg());
    }
}
