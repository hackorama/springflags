package com.hackorama.flags.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

@KeySpace("countries")
public class Country {

	@Id
	private String name;
	private String flag;

	public Country() {
	}

	public Country(String name, String flag) {
		super();
		this.name = name;
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}

	public String getName() {
		return name;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setName(String name) {
		this.name = name;
	}

}
