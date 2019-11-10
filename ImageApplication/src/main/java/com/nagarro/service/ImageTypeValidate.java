package com.nagarro.service;

import javax.servlet.http.Part;


/**
 * @author mayankgangwar
 * class to validate the properties of uploaded image
 */
public class ImageTypeValidate {
	
	/**
	 * static method which validate several fields of the image
	 * @param part contains the detail of the image
	 * @return true if validation successful or false in case of some issue
	 */
	public static boolean validateType(Part part)
	{
		if(part == null)
		{
			System.out.println("No image selected.");
			return false;
		}
		if(part.getSize() <= 0 && part.getSize() > 1048576)
		{
			System.out.println("Invalid Image Size");
			return false;
		}
				
		if(part.getContentType().contains("image"))
			return true;
		
		System.out.println("Selected item is not an image. Please try again.\n");
	
		return false;
	}
}
