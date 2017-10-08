package com.nishant.spring.boot.ldap.entry;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.odm.annotations.Transient;

import com.nishant.spring.boot.ldap.constants.CONSTANTS;
@Entry(objectClasses = {CONSTANTS.TOP, CONSTANTS.ORGANIZATIONAL_UNIT})
public class Groups {
	@Id
	private Name dn;

	@Transient
	@DnAttribute(value = CONSTANTS.OU)
	private String groupname;


	@Attribute(name = "description")
	private String description;



	public Name getDn() {
		return dn;
	}


	public void setDn(Name dn) {
		this.dn = dn;
	}


	public String getGroupname() {
		return groupname;
	}


	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}




}
