package com.aadhar.app.utility;

import com.aadhar.app.model.Role;
import com.aadhar.app.model.User;

public class UserUtility {

	public static String getRoleFromUser(User user) {
		String role = null;
		for (Role roleItem : user.getRoles()) {
			role = roleItem.getName();
		}
		return role;
	}
}
