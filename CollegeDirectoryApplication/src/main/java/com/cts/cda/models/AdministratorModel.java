package com.cts.cda.models;

import lombok.Data;

@Data
public class AdministratorModel {
		private Long id;
	 	private Long userId;
	    private String photo;
	    private String name;
	    private String email;
	    private String phone;
	    private String departmentName;
		public AdministratorModel(Long id,Long userId, String photo, String name, String email, String phone,
				String departmentName) {
			super();
			this.id=id;
			this.userId = userId;
			this.photo = photo;
			this.name = name;
			this.email = email;
			this.phone = phone;
			this.departmentName = departmentName;
		}
		
		
}
