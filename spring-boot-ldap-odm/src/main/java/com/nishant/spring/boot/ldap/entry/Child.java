package com.nishant.spring.boot.ldap.entry;

import java.util.Set;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import com.nishant.spring.boot.ldap.constants.CONSTANTS;
@Entry(objectClasses = {CONSTANTS.TOP, CONSTANTS.GROUP_OF_UNIQUE_NAMES})
public class Child {
	
	@Id
    private Name dn;

	@Attribute(name=CONSTANTS.CN)
	@DnAttribute(value=CONSTANTS.CN, index = 2)
    private String name;
    @Attribute(name=CONSTANTS.UNIQUEMEMBER)
    private Set<Name> members;

	public Name getDn() {
		return dn;
	}

	public void setDn(Name dn) {
		this.dn = dn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Name> getMembers() {
		return members;
	}

	public void setMembers(Set<Name> members) {
		this.members = members;
	}
    
    
}
