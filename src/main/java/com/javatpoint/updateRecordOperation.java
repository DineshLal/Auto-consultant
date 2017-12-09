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
@RequestMapping("/update")
public class updateRecordOperation 
{	
	@RequestMapping(value="/record",method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Declaration setInsert(@RequestBody Declaration updating)
	{
		System.out.println(updating.getEmailId());
		updateRecordInsertion obj=new updateRecordInsertion();
		return obj.update(updating);
	}
}

class updateRecordInsertion 
{
		public Declaration update(Declaration updating)
		{
			try 
			{
				String email=updating.getEmailId();
				System.out.println(email);
				Connection con=DbHandler.connect();
				String sql="select * from sign_up where Email_id=?";
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1,email);
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					PreparedStatement pt=con.prepareStatement("update sign_up set First_name=?,Middle_name=?,Last_name=?,User_name=?,phone_number=?,Pass_word=?,Gender=?,Permanent_address=?,Zip_code=? where Email_id=?");
					if(updating.getFirstName()!=null)
					{
						pt.setString(1,updating.getFirstName());
					}
					else
					{
						pt.setString(1,rs.getString("First_name"));
					}
					if(updating.getMiddleName()!=null)
					{
						pt.setString(2,updating.getMiddleName());
					}
					else
					{
						pt.setString(2,rs.getString("Middle_name"));
					}
					if(updating.getLastName()!=null)
					{
						pt.setString(3,updating.getLastName());
					}
					else
					{
						pt.setString(3,rs.getString("Last_name"));
					}
					if(updating.getUserName()!=null)
					{
						pt.setString(4,updating.getUserName());
					}
					else
					{
						pt.setString(4,rs.getString("User_name"));
					}
					if(updating.getPhoneNumber()!=null)
					{
						pt.setString(5,updating.getPhoneNumber());
					}
					else
					{
						pt.setString(5,rs.getString("phone_number"));
					}
					if(updating.getPassWord()!=null)
					{
						pt.setString(6,updating.getPassWord());
					}
					else
					{
						pt.setString(6,rs.getString("Pass_word"));
					}
					if(updating.getGender()!=null)
					{
						pt.setString(7,updating.getGender());
					}
					else
					{
						pt.setString(7,rs.getString("Gender"));
					}
					if(updating.getPermanentAddress()!=null)
					{
						pt.setString(8,updating.getPermanentAddress());
					}
					else
					{
						pt.setString(8,rs.getString("Permanent_address"));
					}
					if(updating.getZipCode()!=null)
					{
						pt.setString(9,updating.getZipCode());
					}
					else
					{
						pt.setString(9,rs.getString("Zip_code"));
					}
					pt.setString(10,email);
					pt.executeUpdate();
				}
				return updating;
			}
			catch(Exception e)
			{
				System.out.println(e);
				return null;
			}
			
	}
}

