package com.xiongdiyibeizi.service.api;

import com.xiongdiyibeizi.bean.user.User;
import net.sf.json.JSONObject;

import java.util.Map;


public interface UserService {

	String getPassword(String username);

	int changePassword(JSONObject map);

	int getCountUser(String username);

	int registUser(User user);

	User selectUserById(String username);

	int deleteByUsername(String userId);
}
