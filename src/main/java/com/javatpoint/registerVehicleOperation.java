package com.javatpoint;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller 
@RequestMapping("/registering")
public class registerVehicleOperation 
{	
	@RequestMapping(value="/yourVehicle",method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Declaration setInsert(@RequestBody Declaration regi)
	{
		System.out.println(regi.getTypeOfVehicle());
		registerVehicleInsertion obj=new registerVehicleInsertion();
		return obj.registration(regi);
	}
	/*@RequestMapping(value="/imageUpload",method=RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("name") String name,@RequestParam("file") MultipartFile file) 
	{
		
		System.out.println(file+" "+name);
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				File dir=new File("E://dadProject/SpringFileUpload/src/main/webapp/uploadImages/");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();


				return "You successfully uploaded file=" + name;
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name
					+ " because the file was empty.";
		}
	}*/
}
class registerVehicleInsertion
{
	public Declaration registration(Declaration regi)
	{
		try
		{
			Connection con=DbHandler.connect();
			PreparedStatement ps=con.prepareStatement("insert into register_car(model,vehicle_model,registration_no,color,owner_name,no_of_owner,rate_of_sale,phone_number) values(?,?,?,?,?,?,?,?);");
			ps.setString(1,regi.getTypeOfVehicle());
			System.out.println(regi.getTypeOfVehicle());
			ps.setString(2,regi.getVehicleModel());
			ps.setString(3,regi.getRegistrationNo());
			ps.setString(4,regi.getColor());
			ps.setString(5,regi.getOwnerName());
			ps.setString(6,regi.getNoOfOwner());
			ps.setString(7, regi.getRateToSale());
			ps.setString(8,regi.getPhoneNumber());
			ps.executeUpdate();
			return regi;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return null;
		}
	}
}

//ownerName,noOfOwner,rateToSale,typeOfVehicle,color,registrationNo;