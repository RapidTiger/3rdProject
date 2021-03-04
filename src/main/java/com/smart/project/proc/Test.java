package com.smart.project.proc;

import com.smart.project.annotation.Master;
import com.smart.project.common.vo.MenuVO;
import com.smart.project.web.home.vo.*;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Master
@Component
public interface Test {
	/**********************************************************************************************
	 * @Method 설명 : Test_Mapper.xml에 있는 쿼리를 사용 할 경우
	 * @작성일 : 2021-02-15
	 * @작성자 : 김남현
	 * @변경이력 :
	 **********************************************************************************************/
	//List<MenuVO> sqlMenu2(int memNo);

	/**********************************************************************************************
	 * @Method 설명 : proc 하위 SqlTest의 쿼리를 사용 할 경우
	 * @작성일 : 2021-02-15
	 * @작성자 : 김남현
	 * @변경이력 :
	 **********************************************************************************************/
	//@SelectProvider(type = SqlTest.class, method = "sqlMenu")
	//List<MenuVO> sqlMenu(int memNo);

	MemberVO idSelect(String id);

	String joinProgram(MemberVO info);

	String joinProfile(String id);

	String loginProgram(MemberVO log);

	String memberProfile_1(MemberProfileVO profile);

	String memberProfile_2(MemberProfileVO profile);

	String memberProfile_3(MemberProfileVO profile);

	MemberProfileVO profileSelect(String id);

	String profileInsert(ProfileListVO profile);

	String profileDelete(ProfileListVO profile);

	List<ProfileListVO> profileListSelect();

	List<ProfileListVO> profileListSelectCtg(ProfileListVO profile);

	List<ProfileListVO> profileListSelectMore(CtgVo ctg);

	List<ProfileListVO> profileListSelectCtgMore(CtgVo ctg);

	ProfileListVO profileUserSelect(String user);

	List<MessageHeadVO> messageHead(String log);

	List<MessageHeadVO> messageContents(String id, String friend);
	String messageRead(String id, String friend);

	String messageInsert(String send, String received, String content);

	String messageCheck(String id);

	String markCheck(String id, String mark);

	String markInsert(String id, String mark, String idName, String markName);

	String markDelete(String id, String mark);

	List<MarkVO> markList(String id);

	List<MarkVO> markMeList(String id);
}
