package com.xiongdiyibeizi.service.impl;

import com.xiongdiyibeizi.bean.user.User;
import com.xiongdiyibeizi.mapper.UserMapper;
import com.xiongdiyibeizi.service.api.UserService;
import com.xiongdiyibeizi.util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{


	@Resource
	private UserMapper userMapper;

	@Override
	public String getPassword(String username) {
		if (username == null || username.equals(""))
		{
			return "";
		}
		return userMapper.getPassword(username);
	}

	@Override
	public int changePassword(JSONObject map) {

		Map mapUser = new HashMap();
		String password = userMapper.getPassword((String) map.get("username"));
		String webPassword = (String) map.get("oldPassword");
		webPassword = StringUtil.INST.getPassword(1,webPassword,(String)map.get("username"));

		if (password.equals(webPassword)){
			mapUser.put("username",(String)map.get("username"));
			mapUser.put("password",StringUtil.INST.getPassword(1,(String)map.get("newPassword"),(String)map.get("username")));
			userMapper.updatePassword(mapUser);
			return 1;
		}
		return 0;
	}

	@Override
	public int getCountUser(String username) {
		return userMapper.getCountUser(username);
	}

	@Override
	public int registUser(User user) {
		return userMapper.registUser(user);
	}

	@Override
	public User selectUserById(String username) {
		return userMapper.selectUserById(username);
	}

	@Override
	public int deleteByUsername(String username) {
		return userMapper.deleteByUsername(username);
	}
}
