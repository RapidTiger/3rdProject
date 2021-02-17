package com.smart.project.proc;

import com.smart.project.annotation.Master;
import com.smart.project.common.vo.MenuVO;
import com.smart.project.web.home.vo.DeptVO;
import com.smart.project.web.home.vo.MembersVO;
import com.smart.project.web.home.vo.SchVO;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Master
@Component
public interface Test {
	/**********************************************************************************************
	 * @Method 설명 : proc 하위 SqlTest의 쿼리를 사용 할 경우
	 * @작성일 : 2021-02-15
	 * @작성자 : 김남현
	 * @변경이력 :
	 **********************************************************************************************/
	@SelectProvider(type = SqlTest.class, method = "sqlMenu")
	List<MenuVO> sqlMenu(int memNo);


	/**********************************************************************************************
	 * @Method 설명 : Test_Mapper.xml에 있는 쿼리를 사용 할 경우
	 * @작성일 : 2021-02-15
	 * @작성자 : 김남현
	 * @변경이력 :
	 **********************************************************************************************/
	List<MenuVO> sqlMenu2(int memNo);

	List<DeptVO> sqlMenu3(int deptNo);

	List<DeptVO> sqlMenu4(SchVO param);

	List<DeptVO> membersAll();
}
