package com.javatpoint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/delete")
public class deleteRecordOperation 
{
	@RequestMapping(value="/record",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody Declaration setDel(@RequestBody Declaration del)
	{
		System.out.println(del.getEmailId());
		deleteRecordInsertion obj=new deleteRecordInsertion();
		return obj.delete(del);
	}
}

class deleteRecordInsertion
{
	public Declaration delete(Declaration del)
	{
		try{
			Connection con=DbHandler.connect();
			System.out.println(del.getEmailId());
			PreparedStatement ps=con.prepareStatement("delete from sign_up where Email_id=?");
			ps.setString(1,del.getEmailId());
			ps.executeUpdate();
			}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
}
