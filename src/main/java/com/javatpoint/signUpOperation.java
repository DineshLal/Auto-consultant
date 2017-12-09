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
@RequestMapping("/signup")
public class signUpOperation 
{	
	@RequestMapping(value="/getdata",method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Declaration setInsert(@RequestBody Declaration signup)
	{
		signUpInsertion obj=new signUpInsertion();
		return obj.insert(signup);
	}
}

class signUpInsertion 
{
		public Declaration insert(Declaration signup)
		{
			try 
			{
				Connection con=DbHandler.connect();
				PreparedStatement ps=con.prepareStatement("select Email_id from sign_up");
				ResultSet rs=ps.executeQuery();
				int counter=0;
				while(rs.next())
				{
					//System.out.println(rs.getString("Email_id"));
					if(signup.getEmailId().equalsIgnoreCase(rs.getString("Email_id")))
					{
						counter=counter+1;
					}
					else
					{
						//System.out.println(counter);
					}
					
				}
				if(counter==0)
				{
					PreparedStatement pt=con.prepareStatement("insert into sign_up(First_name,Middle_name,Last_name,Email_id,User_name,phone_number,Pass_word,Gender,Permanent_address,Zip_code) values(?,?,?,?,?,?,?,?,?,?)");
					pt.setString(1,signup.getFirstName());
					pt.setString(2,signup.getMiddleName());
					pt.setString(3,signup.getLastName());
					pt.setString(4,signup.getEmailId());
					pt.setString(5,signup.getUserName());
					pt.setString(6,signup.getPhoneNumber());
					pt.setString(7,signup.getPassWord());
					pt.setString(8,signup.getGender());
					pt.setString(9,signup.getPermanentAddress());
					pt.setString(10,signup.getZipCode());
					pt.executeUpdate();
				}
				else
				{
					signup.setEmailId("exists");
					System.out.println("email already exists");
				}
				return signup;
			}
			catch(Exception e)
			{
				System.out.println(e);
				return null;
			}
			
	}
}