package com.nishant.spring.boot.ldap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import com.nishant.spring.boot.ldap.repo.Repo;
@Configuration
public class Config {

	@Bean
	public LdapContextSource ldapContextSource() {
		LdapContextSource lcs=new LdapContextSource();
		lcs.setUrl("ldap://localhost:10389");
		lcs.setBase("dc=nishant,dc=com");
		return lcs;
	}

	@Bean
	public LdapTemplate ldaptemplate(){
		return new LdapTemplate(ldapContextSource());
	}

	@Bean
	public Repo repo() {
		return new Repo();
	}

}