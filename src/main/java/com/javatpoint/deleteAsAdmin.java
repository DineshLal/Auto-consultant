package com.javatpoint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/deleting")
public class deleteAsAdmin 
{
	@RequestMapping(value="/recordAsAdmin",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody ArrayList<String> setDel(@RequestBody ArrayList<String> del) throws SQLException
	{
		deleteRecordAdmin obj=new deleteRecordAdmin();
		
		return obj.deleteAsAdmin(del);
	}
}
class deleteRecordAdmin
{
	public ArrayList<String> deleteAsAdmin(ArrayList<String> del) throws SQLException
	{
		//System.out.println(del);
		Iterator itr=del.iterator();
		while(itr.hasNext())
		{
			//System.out.println(itr.next());
			String emailDelete=(String) itr.next();
			//System.out.println(emailDelete);
			Connection con=DbHandler.connect();
			PreparedStatement ps=con.prepareStatement("delete from sign_up where Email_id=?");
			ps.setString(1,emailDelete);
			//System.out.println(emailDelete);
			ps.executeUpdate();
			
		}
		return null;
	}
}