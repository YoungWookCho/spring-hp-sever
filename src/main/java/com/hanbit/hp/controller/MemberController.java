package com.hanbit.hp.controller;

//클라이언트(웹 브라우저)에서 데이터를 입력하면 해당 URL에서 데이터를 서버로 넘겨 보자! 
//(여기에서 SQL에 저장하는 코드만 입력하면 서버에 저장시킬 수 있다!)

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanbit.hp.annotation.SignInRequired;
import com.hanbit.hp.service.MemberService;

@Controller //스프링에서 컨트롤러라고 인식하게 하는 것
public class MemberController {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
   
   //mysql에 DB를 저장하기 위해서
   @Autowired
   private MemberService memberService;

   @RequestMapping(value="/api2/member/signup", method=RequestMethod.POST) //URL과 맵핑시키는 것, 이 URL에서 데이터를 받아오는 것
   // method=RequestMethod.POST 를 굳이 써주는 이유는? -> POST 방식 이외에는 받지 않겠다고 벨리데이션해주는 것
   // 왜 이렇게 해야 하냐면, 사용자가 URL에 GET 방식으로(http://localhost/api2/member/signup?userId=123&userPw=1234) 직접 데이터를 입력하면 스크립트와 서버의 벨리데이션 통과해서 회원가입이 가능해지기 때문에... 
   @ResponseBody
   public Map signup(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw) {
      //@RequestParam을 해주는 이유는? 스프링에서 이게 파라미터로 들어올 거야!라고 인식시켜주는 것
      
      
      //서버 벨리데이션까지 추가해준다. 
      // 이렇게 서버에서 한 번 더 체크해주는 이유는? -> 사용자가 개발자창으로 강제로 전송할 수 있기 때문에 클라이언트(웹)에서 체크한 뒤 서버에서 한 번 더 확실하게 체크해줘야 함
      if(StringUtils.isBlank(userId)) {
         throw new RuntimeException("아이디가 잘못 입력되었습니다.");
      }
      else if(StringUtils.isBlank(userPw)) {
         throw new RuntimeException("비밀번호가 잘못 입력되었습니다.");
      }
      
      String uid = memberService.addMember(userId, userPw); //mysql에 DB를 저장하기 위해서
            
      Map result = new HashMap();
      result.put("result", "ok");
      result.put("uid", uid);
      
      return result;
   }
   
   
   //로그인//
   @RequestMapping(value="/api2/member/signin", method=RequestMethod.POST)
   @ResponseBody
   public Map signin(@RequestParam("userId") String userId,
         @RequestParam("userPw") String userPw,
         HttpServletRequest request) {
      
      try {
         if(!memberService.isValidMember(userId, userPw)) {
           LOGGER.warn("패스워드 틀림:" + userId + "/" + userPw);
            
            throw new RuntimeException("패스워드가 다릅니다.");
         }
      }
      catch (NullPointerException e) {
         throw new RuntimeException("가입되지 않은 사용자입니다.");
      }
      
      //로그인 세션 저장//
      HttpSession session = request.getSession();
      
      String uid = memberService.getUid(userId);
      
      session.setAttribute("signedIn", true);
      session.setAttribute("uid", uid);
      session.setAttribute("userId", userId);
      
      Map result = new HashMap();
      result.put("result", "ok");
      
      return result;
   }
   
   @RequestMapping("/api2/member/signedin")
   @ResponseBody

   public Map signedin(HttpSession session) {
      Map result = new HashMap();
      String signedIn = "no";      
      
      if (session.getAttribute("signedIn") != null &&
            (Boolean) session.getAttribute("signedIn")) {
         signedIn = "yes";
         
         result.put("userId", session.getAttribute("userId"));
      }
      
      result.put("result", signedIn);
      
      return result;
   }
   

   //회원정보 수정
   @RequestMapping(value="/api2/member/update", method=RequestMethod.POST)
   @ResponseBody
   @SignInRequired

   public Map update(@RequestParam("userPw") String userPw,
         HttpSession session) {
      
      String uid = (String) session.getAttribute("uid");
      
      if (StringUtils.isBlank(uid)) {
         throw new RuntimeException("로그인이 필요합니다.");
      }
      
      memberService.modifyMember(uid, userPw);
      
      Map result = new HashMap();
      result.put("result", "ok");
      
      return result;
   }
   
   
   //로그아웃//
   @RequestMapping("/api2/member/signout")
   @ResponseBody
   public Map signout(HttpSession session) {
      
      session.invalidate();
      
      Map result = new HashMap();
      result.put("result", "ok");
      
      return result;
   }
}