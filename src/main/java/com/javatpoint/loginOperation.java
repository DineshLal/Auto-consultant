package com.javatpoint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class loginOperation 
{
	@RequestMapping(value="/success",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody Declaration Loggedin(@RequestBody Declaration login)
	{
		loginValidation lv=new loginValidation();
		return lv.validate(login);
	}
}
class loginValidation 
{
	public Declaration validate(Declaration login)
	{
		try
		{
			Connection con=DbHandler.connect();
			//Declaration d=new Declaration();
			String query="select Email_id,Pass_word,User_name from sign_up where Email_id=? and Pass_word=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,login.getEmailId());
			ps.setString(2,login.getPassWord());
			ResultSet rs=ps.executeQuery();
			boolean lal=rs.next();
			if(lal==true)
			{
				login.setEmailId(rs.getString("Email_id"));
				login.setUserName(rs.getString("User_name"));
			}
			else
			{
				login.setEmailId("no");
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		return login;
	}
}
