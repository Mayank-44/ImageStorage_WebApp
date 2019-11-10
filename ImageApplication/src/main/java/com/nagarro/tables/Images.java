package com.nagarro.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 * @author mayankgangwar
 * Images class which represents all the fields present in Images table in DB
 */
@Entity
public class Images {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;	// auto incrementing column also the primary key for the table

	private String imageName;
	private int size;
	@Lob
	@Column(name = "preview", nullable = false, columnDefinition = "mediumblob")
	private byte[] preview;	// variable to store image in DB

	@ManyToOne
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public byte[] getPreview() {
		return preview;
	}

	public void setPreview(byte[] preview) {
		this.preview = preview;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
