package com.lti.online_exam.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="Address_Tb")
public class Address {	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)  
	private Long addressId;
	//1>We made sure to create an employeeId field 
	//as well as an employee object
	@Column(name="User_Street")
	private String userStreet;

	@Column(name="User_City")
	private String userCity;

	@Column(name="User_State")
	private String userState;

	@Column(name="User_Country")
	private String userCountry;
	
	@OneToOne(targetEntity=User.class)  
	private User user;
	
	public Address() {
		
	}

	public Address(String userStreet, String userCity, String userState, String userCountry, User user) {
		super();
		this.userStreet = userStreet;
		this.userCity = userCity;
		this.userState = userState;
		this.userCountry = userCountry;
		this.user = user;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getUserStreet() {
		return userStreet;
	}

	public void setUserStreet(String userStreet) {
		this.userStreet = userStreet;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getUserCountry() {
		return userCountry;
	}

	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", userStreet=" + userStreet + ", userCity=" + userCity
				+ ", userState=" + userState + ", userCountry=" + userCountry + ", user=" + user + "]";
	}
	
	
}