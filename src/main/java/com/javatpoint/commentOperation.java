package com.javatpoint;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/add")
public class commentOperation 
{
	@RequestMapping(value="/comment",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody commentsDeclaration Loggedin(@RequestBody commentsDeclaration comt)
	{
		System.out.println(comt.getFeedBack());
		System.out.println(comt.getMailId());
		System.out.println(comt.getRating());
		commentInsertion obj=new commentInsertion();
		return obj.commenting(comt);
	}
	@RequestMapping(value="/newcomment",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody commentsDeclaration newoggedin(@RequestBody commentsDeclaration comt)
	{
		System.out.println(comt.getFeedBack());
		System.out.println(comt.getMailId());
		System.out.println(comt.getRating());
		newcommentInsertion obj=new newcommentInsertion();
		return obj.commentingNew(comt);
	}
}
class commentInsertion 
{
	public commentsDeclaration commenting(commentsDeclaration comt)
	{
		try 
		{
			Connection con=DbHandler.connect();
			int counter=0;
			PreparedStatement pt=con.prepareStatement("select Email_id from comments");
			ResultSet rs=pt.executeQuery();
			while(rs.next())
			{
				if(comt.getMailId().equals(rs.getString("Email_id"))||comt.getFeedBack().equalsIgnoreCase("FeedBack already submitted"))
				{
					counter=counter+1;
				}
				else
				{
					System.out.println("proceeded");
				}
			}
			if(counter==0)
			{
				PreparedStatement ps=con.prepareStatement("insert into comments values(?,?,?)");
				ps.setString(1,comt.getMailId());
				ps.setString(2,comt.getFeedBack());
				ps.setInt(3,comt.getRating());
				ps.executeUpdate();
				comt.setFeedBack("success");
			}
			else
			{
				System.out.println("cannot inserted");
				comt.setFeedBack("FeedBack already submitted");
			}
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		
		System.out.println(comt.getFeedBack());
		System.out.println(comt.getMailId());
		System.out.println(comt.getRating());
		return comt;
		
	}
}
class newcommentInsertion
{
	public commentsDeclaration commentingNew(commentsDeclaration comt)
	{
		try
		{
			Connection con=DbHandler.connect();
			PreparedStatement ps=con.prepareStatement("delete from comments where Email_id=?");
			ps.setString(1,comt.getMailId());
			ps.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
}