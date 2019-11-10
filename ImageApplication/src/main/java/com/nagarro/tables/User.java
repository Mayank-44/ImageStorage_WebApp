package com.nagarro.tables;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


 /**
 * @author mayankgangwar
 * User class which represents all the fields presented in user table in DB
 */
@Entity
public class User {

	@Id
	private String username;	// username working as an primary key
	private String password;
	private int totalFileSize;

	// One to Many relationship with table Images
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Images> image = new ArrayList<Images>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Images> getImg() {
		return image;
	}

	public void setImg(List<Images> img) {
		this.image = img;
	}

	public int getTotalFileSize() {
		return totalFileSize;
	}

	public void setTotalFileSize(int totalFileSize) {
		this.totalFileSize = totalFileSize;
	}

	public List<Images> getImage() {
		return image;
	}

	public void setImage(List<Images> image) {
		this.image = image;
	}
}
