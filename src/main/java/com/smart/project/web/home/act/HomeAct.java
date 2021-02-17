package com.smart.project.web.home.act;

import com.smart.project.web.home.biz.HomeService;
import com.smart.project.web.home.vo.DeptVO;
import com.smart.project.web.home.vo.MembersVO;
import com.smart.project.web.home.vo.SchVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class HomeAct {

	@Autowired
	HomeService homeService;

	@RequestMapping("/test")
	public String home(@ModelAttribute SchVO sch){
		//homeService.Test();
		Map<String, Object> ddd = homeService.Test(sch);
		log.warn("ddd==>{}//{}", ddd, sch);
		return "/main/home";
	}
	@GetMapping("/member")
	public String openBoardWrite(Model model) {
		Map<String, Object> members = homeService.member();
		model.addAttribute("members", members);
		System.out.println(" ");
		System.out.println(members);
		return "/main/members";
	}
}
