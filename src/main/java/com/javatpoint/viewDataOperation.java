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
@RequestMapping("/view")
public class viewDataOperation 
{
	@RequestMapping(value="/details",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody Declaration searchValue(@RequestBody Declaration viewdata)
	{
		viewDataSelection vd=new viewDataSelection();
		return vd.getByEmailid(viewdata);		
	}
}
class viewDataSelection 
{
	public Declaration getByEmailid(Declaration viewdata)
	{
		try
		{
			Connection con=DbHandler.connect();
			Declaration d=new Declaration();
			String query="select * from sign_up where Email_id=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,viewdata.getEmailId());
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				viewdata.setFirstName(rs.getString("First_name"));
				viewdata.setMiddleName(rs.getString("Middle_name"));
				viewdata.setLastName(rs.getString("Last_name"));
				viewdata.setEmailId(rs.getString("Email_id"));
				viewdata.setUserName(rs.getString("User_name"));
				viewdata.setUserName(rs.getString("phone_number"));
				viewdata.setPassWord(rs.getString("Pass_word"));
				viewdata.setGender(rs.getString("Gender"));
				viewdata.setPermanentAddress(rs.getString("Permanent_address"));
				viewdata.setZipCode(rs.getString("Zip_code"));
				viewdata.setIsActive(rs.getString("is_active"));
			}
			return viewdata;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
		
	}
}
