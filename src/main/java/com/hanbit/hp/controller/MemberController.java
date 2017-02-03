package com.hanbit.hp.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanbit.hp.service.MemberService;
// 커트롤러 설정
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	//주소를 매핑해줌
	@RequestMapping(value="/api2/member/signup", method=RequestMethod.POST)
	//주소를 받는 부분
	@ResponseBody
	//@RequestParam 으로 받아야 하는데 유저아이디 ""해주는 이유는 자바 7에서 해줘야함
	public Map signup (@RequestParam("userId") String userId, 
			@RequestParam("userPw") String userPw) {
			
		System.out.println(userId+" / "+userPw);
		
		if (StringUtils.isBlank(userId)) {
			throw new RuntimeException("아이디가 잘못되었습니다");
		}
		else if (StringUtils.isBlank(userPw)) {
			throw new RuntimeException("비밀번호가 잘못되었습니다");
		}
		
		String uid =memberService.addMember(userId, userPw);

		Map result = new HashMap();
		result.put("result", "ok");
		result.put("uid", uid);
		return result;
	}
}
