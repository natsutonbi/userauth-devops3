package com.example.demo;

import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
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
}
