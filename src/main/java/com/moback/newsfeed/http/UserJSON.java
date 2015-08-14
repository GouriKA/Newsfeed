package com.moback.newsfeed.http;

public class UserJSON {

	private String id;	
	private String username;
  	private String firstName;		
	private String lastName;
	//private Image image;
 	private String dateOfBirth;	
	private String gender;
 	private String tagLine;
	private String password;
 	
	public String getTagLine() {
		return tagLine;
	}
	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(String DateOfBirth) {
		this.dateOfBirth = DateOfBirth;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
 	
 	public String getFirstName() {
 		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	/*public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}*/
}
