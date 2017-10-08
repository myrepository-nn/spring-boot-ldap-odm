package com.nishant.spring.boot.ldap.entry;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import com.nishant.spring.boot.ldap.constants.CONSTANTS;


@Entry(objectClasses = {CONSTANTS.TOP, CONSTANTS.PERSON, CONSTANTS.ORGANIZATIONAL_PERSON,CONSTANTS.INET_ORG_PERSON})
public class People {

	@Id
	private Name dn;

	@Attribute(name=CONSTANTS.CN)
	@DnAttribute(value=CONSTANTS.CN)
	private String fullname;

	@Attribute(name=CONSTANTS.SN)
	private String lastname;

	@Attribute(name = "description")
	private String description;

	@Attribute(name = "givenname")
	private String givenname;

	@Attribute(name = "mail")
	private String mail;

	@Attribute(name = "manager")
	private String manager;

	@Attribute(name = CONSTANTS.UID)
	private String uid;

	@Attribute(name = "userpassword")
	private String userpassword;

	public Name getDn() {
		return dn;
	}

	public void setDn(Name dn) {
		this.dn = dn;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGivenname() {
		return givenname;
	}

	public void setGivenname(String givenname) {
		this.givenname = givenname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}


}
