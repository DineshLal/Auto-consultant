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
@RequestMapping("/rent")
public class rentVehicleOperation 
{
	@RequestMapping(value="/vehicle",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody Declaration rentvehicle(@RequestBody Declaration renting)
	{
		rentVehicleInsertion rv=new rentVehicleInsertion();
		return rv.insert(renting);
	}
}
class rentVehicleInsertion
{
	public Declaration insert(Declaration renting)
	{
		try
		{
			Connection con=DbHandler.connect();
			PreparedStatement pt=con.prepareStatement("select User_name from sign_up");
			ResultSet rs=pt.executeQuery();
			int counter=0,check=0;
			while(rs.next())
			{
				if(renting.getUserName().equalsIgnoreCase(rs.getString("User_name")))
				{
					counter=0;
					System.out.println(counter);
					break;
				}
				else
				{
					counter=12;
					System.out.println(counter);
				}
			}
			PreparedStatement st=con.prepareStatement("select user_name,licence_dl_no,licence_expiry_date,licence_type,vehicle_model,duration,payable_method from rent_car");
			ResultSet rss=st.executeQuery();
			while(rss.next())
			{
				if((renting.getUserName().equalsIgnoreCase(rss.getString("user_name")))&&(renting.getLicenceDLno().equalsIgnoreCase(rss.getString("licence_dl_no")))&&(renting.getLicenceExpiryDate().equalsIgnoreCase(rss.getString("licence_expiry_date")))&&(renting.getLicenceType().equalsIgnoreCase(rss.getString("licence_type")))&&(renting.getVehicleModel().equalsIgnoreCase(rss.getString("vehicle_model")))&&(renting.getDuration().equalsIgnoreCase(rss.getString("duration")))&&(renting.getPayableMethod().equalsIgnoreCase(rss.getString("payable_method"))))
				{
					check=12;
					System.out.println("repeated"+check);
					break;
				}
				else
				{
					counter=0;
					System.out.println("not repeated"+check);
				}
			}
			if((counter==0)&&(check==0))
			{
			PreparedStatement ps=con.prepareStatement("insert into rent_car(user_name,licence_dl_no,licence_expiry_date,licence_type,vehicle_model,duration,payable_method) values(?,?,?,?,?,?,?)");
			ps.setString(1,renting.getUserName());
			ps.setString(2,renting.getLicenceDLno());
			ps.setString(3,renting.getLicenceExpiryDate());
			ps.setString(4,renting.getLicenceType());
			ps.setString(5,renting.getVehicleModel());
			ps.setString(6,renting.getDuration());
			ps.setString(7,renting.getPayableMethod());
			ps.executeUpdate();
			}
			return renting;
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
		
	}
}