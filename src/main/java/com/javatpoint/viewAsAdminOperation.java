package com.javatpoint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/viewas")
public class viewAsAdminOperation 
{
	@RequestMapping(value="/admin",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody List<Declaration> selectAll(@RequestBody Declaration viewasadmin)
	{
		viewAsAdminSelection obj=new viewAsAdminSelection();
		return obj.viewasAdmin(viewasadmin);
	}

}
class viewAsAdminSelection 
{
	public List<Declaration> viewasAdmin(Declaration viewasadmin)
	{
		try
		{
			Connection con=DbHandler.connect();
			List<Declaration> contains=new ArrayList<Declaration>();
			Declaration declarationGetting=new Declaration();
			Declaration declaration=null;
			String emailid=viewasadmin.getEmailId();
			//System.out.println(emailid);
			String password=viewasadmin.getPassWord();
			//System.out.println(password);
			if(emailid.equalsIgnoreCase("tamildi@admin.com")&&password.equals("admin321"))
			{
				String sql="select * from sign_up";
				PreparedStatement st=con.prepareStatement(sql);
				ResultSet rs=st.executeQuery();
				while(rs.next())
				{
					declaration=new Declaration();
					declaration.setFirstName(rs.getString(2));
					declaration.setMiddleName(rs.getString(3));
					declaration.setLastName(rs.getString(4));
					declaration.setEmailId(rs.getString(5));
					declaration.setUserName(rs.getString(6));
					declaration.setPhoneNumber(rs.getString(7));
					declaration.setPassWord(rs.getString(8));
					declaration.setGender(rs.getString(9));
					declaration.setPermanentAddress(rs.getString(10));
					declaration.setZipCode(rs.getString(11));
					declaration.setIsActive(rs.getString(12));
					contains.add(declaration);
				}	
					
			}
			else
			{
				System.out.println("nothing happens");
			}
				return contains;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return null;
		}	
	
	}
}
