package com.nishant.spring.boot.ldap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nishant.spring.boot.ldap.constants.CONSTANTS;
import com.nishant.spring.boot.ldap.entry.Child;
import com.nishant.spring.boot.ldap.entry.Groups;
import com.nishant.spring.boot.ldap.entry.People;
import com.nishant.spring.boot.ldap.repo.Repo;
import com.nishant.spring.boot.ldap.vo.GroupVO;
import com.nishant.spring.boot.ldap.vo.PeopleVO;

@RestController
public class Controller {

	@Autowired
	public Repo repo;
	

    ////People Methods
	@GetMapping("/persons")
	public List<People> getAllPersons() throws JsonProcessingException {
		return repo.findAll();
	}
	@GetMapping("/getuid/{uid}")
	public People getuid(@PathVariable String uid) {
		return repo.findByUid(uid);
	}
	@GetMapping("/getlastname/{lastname}")
	public List<People> getlastname(@PathVariable String lastname) {
		return repo.findByLastName(lastname);
	}
	@PostMapping("/add")
	public String add(@RequestBody PeopleVO peoplevo) {
		repo.save(peoplevo);
		return CONSTANTS.SUCCESS;
	}
	@PutMapping("/update")
	public String update(@RequestBody PeopleVO peoplevo) {
		repo.update(peoplevo);
		return CONSTANTS.SUCCESS;
	}
	@PostMapping("/del/{uid}")
	public String delete(@PathVariable String uid) {
		repo.delete(uid);
		return CONSTANTS.SUCCESS;
	}
	
	
	//// Group Methods
	@GetMapping("/groups")
	public List<Groups> getAllGroups() {
		return repo.findAllGroups();
	}
	@GetMapping("/childs")
	public List<Child> getAllChilds() {
		return repo.findAllChilds();
	}
	@PostMapping("/addgroup")
	public String addGroup(@RequestBody GroupVO groupvo) {
		repo.createGroup(groupvo);
		return CONSTANTS.SUCCESS;
	}
	@PostMapping("/addgroupchild/{groupname}/{child}")
	public String addGroupChild(@PathVariable String groupname,@PathVariable String child) {
		repo.createGroupChild(child, groupname);
		return CONSTANTS.SUCCESS;
	}
	@PostMapping("/delgroup/{groupname}")
	public String deleteGroupByName(@PathVariable String groupname) {
		repo.deleteGroupByName(groupname);
		return CONSTANTS.SUCCESS;
	}
	@PostMapping("/delgroupchild/{childname}")
	public String deleteGrouphildByName(@PathVariable String childname) {
		repo.deleteChild(childname);
		return CONSTANTS.SUCCESS;
	}
	@PostMapping("/addpeopletogroup/{groupname}/{child}/{membername}")
	public String addPeopleToGroup(@PathVariable String groupname,@PathVariable String child,@PathVariable String membername) {
		repo.addPeopleToGroup(child, groupname, membername);
		return CONSTANTS.SUCCESS;
	}
	@PostMapping("/removepeopletogroup/{groupname}/{child}/{membername}")
	public String removePeopleToGroup(@PathVariable String groupname,@PathVariable String child,@PathVariable String membername) {
		repo.removePeopleToGroup(child, groupname, membername);
		return CONSTANTS.SUCCESS;
	}


}