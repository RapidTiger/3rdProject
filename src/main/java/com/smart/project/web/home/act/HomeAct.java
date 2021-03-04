package com.smart.project.web.home.act;

import com.smart.project.web.home.biz.HomeService;
import com.smart.project.web.home.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@SessionAttributes({"id","name"})
public class HomeAct {

	@Autowired
	HomeService homeService;

	@GetMapping("/index")
	public String index(Model model) {
		String id = (String) model.getAttribute("id");
		if (id != null) {
			String alert = homeService.messageCheck(id);
			model.addAttribute("alert", alert);
		} else {
			model.addAttribute("alert", "");
		}
		System.out.println(model.getAttribute("alert"));
		return "ARY/index";
	}

	@GetMapping("/join")
	public String join(){
		return "/ARY/join";
	}

	@PostMapping("/joinProgram")
	public RedirectView joinProgram(@ModelAttribute MemberVO member, HttpSession session) {
		String url = "";
		String result = homeService.joinProgram(member);

		if (result.equals("fail")) {
			url = "join";
		} else {
			session.setAttribute("id",member.getId());
			url = "profile-01";
		}
		return new RedirectView(url);
	}

	@GetMapping("/login")
	public String login() {
		return "/ARY/login";
	}

	@PostMapping("/loginProgram")
	public RedirectView  login(@ModelAttribute MemberVO log, Model model,HttpSession session , SessionStatus status) {
		System.out.println(log);
		String member = homeService.loginProgram(log);
		String url = "";
		if (member != null) {
			session.setAttribute("id",log.getId());
			session.setAttribute("name",member);
			url = "index";
		} else {
			status.setComplete();
			url = "login";
		}
		return new RedirectView(url);
	}

	@PostMapping("idTest")
	@ResponseBody
	public String idTest(String id) {
		MemberVO result = homeService.idSelect(id);
		System.out.println(result);
		String returnId = null;
		try {
			returnId = result.getId();
		} catch (Exception e) {
			returnId = "false";
		}
		return returnId;
	}

	@GetMapping("/logout")
	public RedirectView logout(SessionStatus status) {
		status.setComplete();
		return new RedirectView("index");
	}

	@GetMapping("/profile-01")
	public String profile_01() {
		return "/ARY/profile-01";
	}

	@GetMapping("/profile-02")
	public String profile_02() {
		return "/ARY/profile-02";
	}

	@GetMapping("/profile-03")
	public String profile_03(Model model) {
		MemberProfileVO result = homeService.profileSelect((String) model.getAttribute("id"));
		MemberVO result2 = homeService.idSelect((String) model.getAttribute("id"));
		System.out.println(result2);
		model.addAttribute("profile", result);
		model.addAttribute("profile2", result2);
		return "ARY/profile-03";
	}

	@PostMapping("updateProfile1")
	public RedirectView updateProfile1(@ModelAttribute MemberProfileVO profile, Model model) {
		profile.setId((String) model.getAttribute("id"));
		String update = homeService.memberProfile_1(profile);
		return new RedirectView("profile-02");
	}

	@PostMapping("updateProfile2")
	public RedirectView updateProfile2(@ModelAttribute MemberProfileVO profile, Model model){
		profile.setId((String) model.getAttribute("id"));
		System.out.println(profile);
		String update = homeService.memberProfile_2(profile);
		return new RedirectView("profile-03");
	}

	@PostMapping("updateProfile3")
	public RedirectView updateProfile3(@ModelAttribute MemberProfileVO profile, Model model, @RequestParam("img1") MultipartFile file1, @RequestParam("img2") MultipartFile file2, String introduce){
		String id = (String) model.getAttribute("id");
		profile.setId(id);
		String result = homeService.memberProfile_3(profile);
		System.out.println(result);
		try {
			String baseDir = "C:\\Users\\smhrd\\Desktop\\smart_project\\build\\resources\\main\\static\\img\\user\\"+id;
			String filePath1 = baseDir + "\\1.png";
			String filePath2 = baseDir + "\\2.png";
			File makeFolder = new File(baseDir);
			if (!makeFolder.exists()) {
				makeFolder.mkdirs();
			}
			if (file1.getContentType().substring(0,5).equals("image")) {
				file1.transferTo(new File(filePath1));
			}
			if (file2.getContentType().substring(0,5).equals("image")) {
				file2.transferTo(new File(filePath2));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new RedirectView("myProfile");
	}

	@GetMapping("myProfile")
	public String myProfile(Model model) {
		String id = (String)model.getAttribute("id");
		MemberVO member = homeService.idSelect(id);
		MemberProfileVO profile = homeService.profileSelect(id);
		model.addAttribute("member", member);
		model.addAttribute("profile", profile);
		return "ARY/myProfile";
	}

	@PostMapping("profileUpdate")
	public RedirectView profileUpdate(Model model, ProfileListVO profileList) {
		profileList.setId((String) model.getAttribute("id"));
		System.out.println(profileList);
		String result1 = homeService.profileDelete(profileList);
		String result2 = homeService.profileInsert(profileList);
		System.out.println(result1);
		System.out.println(result2);
		return new RedirectView("member-list");
	}

	@GetMapping("/member-list")
	public String member_list(Model model, ProfileListVO profile) {
		List<ProfileListVO> lists = homeService.profileListSelect();
		model.addAttribute("ctg", profile);
		model.addAttribute("lists", lists);
		System.out.println(lists);
		return "ARY/member-list";
	}

	@PostMapping("/member-listCtg")
	public String member_listCtg(Model model, ProfileListVO profile) {
		List<ProfileListVO> lists = homeService.profileListSelectCtg(profile);
		model.addAttribute("ctg", profile);
		model.addAttribute("lists", lists);
		return "ARY/member-list";
	}

	@GetMapping("/profileListSelectMore")
	@ResponseBody
	public List<ProfileListVO> profileListSelectMore(CtgVo ctg) {
		List<ProfileListVO> list = homeService.profileListSelectMore(ctg);
		return list;
	}

	@GetMapping("/profileListSelectCtgMore")
	@ResponseBody
	public List<ProfileListVO> profileListSelectCtgMore(CtgVo ctg) {
		List<ProfileListVO> list = homeService.profileListSelectCtgMore(ctg);
		return list;
	}

	@GetMapping("/userProfile")
	public String userProfile(Model model, String user) {
		String mark = homeService.markCheck((String)model.getAttribute("id"),user);
		ProfileListVO profile = homeService.profileUserSelect(user);
		model.addAttribute("mark", mark);
		model.addAttribute("profile", profile);
		return "ARY/userProfile";
	}

	@GetMapping("messageHead")
	public String messageHead(Model model){
		String id = (String)model.getAttribute("id");
		List<MessageHeadVO> head = homeService.messageHead(id);
		model.addAttribute("heads", head);
		return "ARY/messageHead";
	}

	@GetMapping("/messageHeadSet")
	public String messageHeadSet(Model model) {
		String id = (String)model.getAttribute("id");
		List<MessageHeadVO> head = homeService.messageHead(id);
		model.addAttribute("heads", head);
		return "ARY/messageHeadSet";
	}

	@GetMapping("/messageContent")
	public String messageContent(String friend, Model model) {
		String id = (String)model.getAttribute("id");
		List<MessageHeadVO> messages = homeService.messageContents(id, friend);
		System.out.println(messages);
		model.addAttribute("messages", messages);
		model.addAttribute("friend", friend);
		return "ARY/messageContent";
	}

	@GetMapping("/messageContentSet")
	public String messageContentsSet(String friend, Model model) {
		String id = (String)model.getAttribute("id");
		List<MessageHeadVO> messages = homeService.messageContents(id, friend);
		model.addAttribute("messages", messages);
		model.addAttribute("friend", friend);
		return "ARY/messageContentSet";
	}

	@PostMapping("/messageInsert")
	@ResponseBody
	public String messageHead(String received, String content, Model model) {
		String send = (String)model.getAttribute("id");
		String message = homeService.messageInsert(send, received, content);
		return message;
	}

	@PostMapping("/markInsert")
	@ResponseBody
	public String markInsert(Model model, String mark, String markName) {
		String id = (String)model.getAttribute("id");
		MemberProfileVO idName = homeService.profileSelect(id);
		String marker = homeService.markInset(id, mark, idName.getNickname(), markName);
		return marker;
	}

	@PostMapping("/markDelete")
	@ResponseBody
	public String markInsert(Model model, String mark) {
		String id = (String)model.getAttribute("id");
		System.out.println(id + "-" + mark);
		String marker = homeService.markDelete(id, mark);
		return marker;
	}

	@GetMapping("/markList")
	public String markList(Model model) {
		String id = (String)model.getAttribute("id");
		List<MarkVO> marks = homeService.markList(id);
		model.addAttribute("marks", marks);
		return "ARY/markList";
	}

	@GetMapping("/markMeList")
	public String markMeList(Model model) {
		String id = (String)model.getAttribute("id");
		List<MarkVO> marks = homeService.markMeList(id);
		model.addAttribute("marks", marks);
		return "ARY/markMeList";
	}

	@GetMapping("/AI-manager-1")
	public String AI_manager_1(Model model) {
		return "/Ary/AI-manager-1";
	}

	@GetMapping("/AI-manager-2")
	public String AI_manager_2(Model model) {
		return "/Ary/AI-manager-2";
	}

	@GetMapping("/gwansang")
	public String gwansang(Model model, String user) {
		model.addAttribute("user", user);
		return "/Ary/gwansang";
	}
}
