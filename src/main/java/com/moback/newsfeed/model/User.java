package com.moback.newsfeed.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document(collection = "members")
public class User {
 
	@Id
	private String id;
 	private String username;
 	private String firstName;	
	private String lastName;
	//private Image image;
 	private String dateOfBirth;	
	private String gender;
 	private String tagLine;
	private byte[] encryptedPassword;
 	private byte[] salt;
 	
 	public String getTagLine() {
		return tagLine;
	}

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}
 	
 	public String getFirstName() {
 		//String firstNameCamelCase = firstName.substring(0,1).toUpperCase().concat(firstName.substring(1));
 		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		//String lastNameCamelCase = lastName.substring(0,1).toUpperCase().concat(lastName.substring(1));
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public byte[] getEncryptedPassword() {
		return encryptedPassword;
	}
 
	public void setUsername(String username) {
		this.username = username;
	}

	public void setEncryptedPassword(byte[] encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public void setId(String id) {
		this.id = id;
	}

	/*public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}*/
	
}