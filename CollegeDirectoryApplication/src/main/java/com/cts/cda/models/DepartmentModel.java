package com.cts.cda.models;

import lombok.Data;

@Data
public class DepartmentModel {
	private Long id;
	private String name;
	private String description;
	public DepartmentModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DepartmentModel(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
}
