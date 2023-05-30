package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.user.service.mailService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//随机端口启动
class DemoApplicationTests {
	/*@Autowired
	private loginUserMapper loginMapper;

	@Test
	void userTest() {
		System.out.println(("----- selectByName method test ------"));
		List<userAccountInfo> users=loginMapper.getByUsername("root");
		userAccountInfo user;
		if(users==null || users.size()==0)
		{
			Date date=new Date();
			SimpleDateFormat fmt=new SimpleDateFormat("yyyy-mm-dd");
			String salt=MD5SaltPwd.getSalt();
			String saltedPwd=MD5SaltPwd.getSaltedPwd("123456", salt);
			userAccountInfo root=new userAccountInfo("root",fmt.format(date),salt,saltedPwd,"natsutonbi");
			loginMapper.insert(root);
			user=root;
		}
		else user=users.get(0);
		boolean result=MD5SaltPwd.verify("123456", user.salt, user.password);
		System.out.println(result);
	}

	private gradeMapper gMapper;
	@Test
	void gradeTest(){
		List<gradeItem> grades=gMapper.getAll();
		System.out.println(grades.size());
	}*/
	@Autowired
	mailService mailServ;

	@Test//必须在app运行情况下test
	public void simpleMailTest()
	{
		mailServ.sendSimpleMail("1306512118@qq.com", "testmail", "hello,natsutonbi. (from spring boot)");
	}
}
