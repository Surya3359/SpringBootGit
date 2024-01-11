package com.legio.adeptus.Entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "GreyKnight", uniqueConstraints = {
		@UniqueConstraint(columnNames = "vemail", name = "email"),
		@UniqueConstraint(columnNames = "vphone", name = "phone")
})
public class OrdoMalleus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "vname")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Username cannot have special characters")
	@Size(min = 3, max = 25, message = "Username must atleast contain 3 characters")
	@NotBlank(message = "Username is required")
	private String name;
	@Column(name = "vage")
	@Min(18)
	@Max(60)
	@NotNull(message = "Age is required")
	private int age;
	@Column(name = "vaddress")
	@Size(min = 10, max = 60, message = "Address should be atleast 10 char with pincode")
	@NotBlank(message = "Address is required")
	private String address;
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
			flags = Pattern.Flag.CASE_INSENSITIVE, message = "EmailId must be written in proper format")
	@Column(name = "vemail")
	@NotBlank(message = "EmailID is required")
	private String email;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
	@Column(name = "vdoj", unique = true)
	@NotNull(message = "Date of joining is required")
	private Date doj;
	@Column(name = "vphone", unique = true)
	@Size(min = 10, max = 10, message = "Phone no. must be 10 digits")
	@NotBlank(message = "Phone no. is required")
	private String phone;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
