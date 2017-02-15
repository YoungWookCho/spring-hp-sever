package com.hanbit.hp.aop;


import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Order(3)
public class SessionAspect {

   @Before("@annotation(com.hanbit.hp.annotation.SignInRequired)")
   public void checkSignedIn() {
      ServletRequestAttributes requestAttributes = 
            (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
      HttpSession session = requestAttributes.getRequest().getSession();
      
      if (session.getAttribute("signedIn") == null ||
            !(Boolean) session.getAttribute("signedIn")) {
         throw new RuntimeException("로그인이 필요합니다.");
      }
   }
   
}
