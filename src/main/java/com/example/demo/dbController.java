package com.example.demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
class item
{
    public int SID,CID;
    public float Score;
    public String Note;
	item(int SID,int CID,float Score){
		this.SID=SID;
		this.CID=CID;
		this.Score=Score;
	}
}
@Mapper
interface socreItemMapper{
	
	@Select("SELECT * FROM GRADE WHERE SID = #{SID}")
    item findByName(@Param("SID") String SID);

    @Insert("INSERT INTO USER(SID, CID, Score, Note) VALUES(#{SID}, #{CID}, #{Socre}, #{Note})")
    int insert(@Param("name") String name, @Param("age") Integer age);
}
@Controller
public class dbController {
    public static final String url = "jdbc:mysql://114.116.254.100/GradeDB"; 
	private final static String username = "outer";//MySQL数据库登录用户名
	private final static String password = "123456";//MySQL数据库登录密码
    
    @GetMapping("/grade")
    public String grade(ModelMap map){
        ArrayList<item> item_list=new ArrayList<item>();
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");//加载MySQL数据库驱动
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("未能成功加载数据库驱动程序!");
		}
		try {
			Connection connect = DriverManager.getConnection(url, username, password);
			
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery("select * from COURSE");
            
			rs = stmt.executeQuery("select * from GRADE");
			while (rs.next()) {
                item a=new item();
				a.SID=rs.getInt("SID");
				a.CID=rs.getInt("CID");
				a.Score=rs.getFloat("Score");
				a.Note=rs.getString("Note");
                item_list.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        map.addAttribute("itemList", item_list);
        return "grade";
    }
    @GetMapping("/grade/{cid}")
    public String grade_course(ModelMap map,@PathVariable("cid") String cid)
    {
        ArrayList<item> item_list=new ArrayList<item>();
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");//加载MySQL数据库驱动
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("未能成功加载数据库驱动程序!");
		}
		try {
			Connection connect = DriverManager.getConnection(url, username, password);
			
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery("select * from GRADE where CID="+cid);
			while (rs.next()) {
                item a=new item();
				a.SID=rs.getInt("SID");
				a.CID=rs.getInt("CID");
				a.Score=rs.getFloat("Score");
				a.Note=rs.getString("Note");
                item_list.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        map.addAttribute("itemList", item_list);
        return "grade";
    }
}