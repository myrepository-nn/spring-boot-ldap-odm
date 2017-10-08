package com.nishant.spring.boot.ldap.repo;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;

import com.nishant.spring.boot.ldap.constants.CONSTANTS;
import com.nishant.spring.boot.ldap.entry.Child;
import com.nishant.spring.boot.ldap.entry.Groups;
import com.nishant.spring.boot.ldap.entry.People;
import com.nishant.spring.boot.ldap.vo.GroupVO;
import com.nishant.spring.boot.ldap.vo.PeopleVO;

public class Repo {

	@Autowired
	private LdapTemplate ldapTemplate;

	public String save(PeopleVO peoplevo) {
		People pep=new People();
		pep.setDn(LdapNameBuilder.newInstance(peoplevo.getDn()).build());
		pep.setDescription(peoplevo.getDescription());
		pep.setFullname(peoplevo.getFullname());
		pep.setGivenname(peoplevo.getGivenname());
		pep.setLastname(peoplevo.getLastname());
		pep.setMail(peoplevo.getMail());
		pep.setManager(peoplevo.getManager());
		pep.setUid(peoplevo.getUid());
		pep.setUserpassword(peoplevo.getUserpassword());
		ldapTemplate.create(pep);
		return CONSTANTS.SUCCESS;
	}
	public void update(PeopleVO peoplevo) {
		People pep=new People();
		pep.setDn(LdapNameBuilder.newInstance(peoplevo.getDn()).build());
		pep.setDescription(peoplevo.getDescription());
		pep.setFullname(peoplevo.getFullname());
		pep.setGivenname(peoplevo.getGivenname());
		pep.setLastname(peoplevo.getLastname());
		pep.setMail(peoplevo.getMail());
		pep.setManager(peoplevo.getManager());
		pep.setUid(peoplevo.getUid());
		pep.setUserpassword(peoplevo.getUserpassword());
		ldapTemplate.update(pep);
	}
	public void delete(String uid) {
		People pep=ldapTemplate.findOne(query().where(CONSTANTS.UID).is(uid), People.class);
		ldapTemplate.delete(pep);
	}
	public People findByUid(String uid) {
		return ldapTemplate.findOne(query().where(CONSTANTS.UID).is(uid), People.class);
	}
	public List<People> findAll() {
		return ldapTemplate.findAll(People.class);
	}
	public List<People> findByLastName(String lastName) {
		return ldapTemplate.find(query().where(CONSTANTS.SN).is(lastName), People.class);
	}


	// Group Methods
	public Groups createGroup(GroupVO groups) {
		Groups gps=new Groups();
		gps.setDn(LdapNameBuilder.newInstance(groups.getDn()).build());
		gps.setGroupname(groups.getGroupname());
		gps.setDescription(groups.getDescription());
		ldapTemplate.create(gps);
		return gps;
	}
	public String deleteGroupByName(String name) {
		Groups gps=ldapTemplate.findOne(query().where(CONSTANTS.OU).is(name), Groups.class);
		ldapTemplate.delete(gps);
		return CONSTANTS.SUCCESS;
	}
	public String createGroupChild(String child,String groupname) {
		Child cr=new Child();
		cr.setDn(LdapNameBuilder.newInstance().add(CONSTANTS.OU, CONSTANTS.BASE_GROUPS).add(CONSTANTS.OU, groupname).add(CONSTANTS.CN, child).build());
		cr.setName(child);
		Set<Name> st=new HashSet<>();
		st.add(LdapNameBuilder.newInstance().add(CONSTANTS.OU, CONSTANTS.BASE_PEOPLE_GROUP).add(CONSTANTS.CN, "ADMIN").build());
		cr.setMembers(st);
		ldapTemplate.create(cr);
		return CONSTANTS.SUCCESS;
	}
	public String deleteChild(String childname) {
		Child chd=ldapTemplate.findOne(query().where(CONSTANTS.OBJECTCLASS).is(CONSTANTS.GROUP_OF_UNIQUE_NAMES)
				.and(CONSTANTS.CN).whitespaceWildcardsLike(childname), Child.class);
		ldapTemplate.delete(chd);
		return CONSTANTS.SUCCESS;
	}
	public List<Child> findAllChilds() {
		return ldapTemplate.findAll(Child.class);
	}
	public List<Groups> findAllGroups() {
		return ldapTemplate.findAll(Groups.class);
	}
	public String addPeopleToGroup(String child,String groupname,String membername) {
		DirContextOperations ctx = ldapTemplate.lookupContext(LdapNameBuilder.newInstance().add(CONSTANTS.OU, CONSTANTS.BASE_GROUPS).add(CONSTANTS.OU, groupname).add(CONSTANTS.CN, child).build());
		ctx.addAttributeValue(CONSTANTS.UNIQUEMEMBER, LdapNameBuilder.newInstance().add(CONSTANTS.OU, CONSTANTS.BASE_PEOPLE_GROUP).add(CONSTANTS.CN, membername).build());
		ldapTemplate.modifyAttributes(ctx);
		return CONSTANTS.SUCCESS;
	}
	public String removePeopleToGroup(String child,String groupname,String membername) {
		DirContextOperations ctx = ldapTemplate.lookupContext(LdapNameBuilder.newInstance().add(CONSTANTS.OU, CONSTANTS.BASE_GROUPS).add(CONSTANTS.OU, groupname).add(CONSTANTS.CN, child).build());
		ctx.removeAttributeValue(CONSTANTS.UNIQUEMEMBER, LdapNameBuilder.newInstance().add(CONSTANTS.OU, CONSTANTS.BASE_PEOPLE_GROUP).add(CONSTANTS.CN, membername).build());
		ldapTemplate.modifyAttributes(ctx);
		return CONSTANTS.SUCCESS;
	}
}
