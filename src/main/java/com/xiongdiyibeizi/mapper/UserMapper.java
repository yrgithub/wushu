package com.xiongdiyibeizi.mapper;

import com.xiongdiyibeizi.bean.user.User;

import java.util.Map;

public interface UserMapper {

    /**
     * 修改create_by
     * @param map
     * @return
     */
    int updateCreateBy(Map map);
    /**
     * 修改密码
     * @param map
     * @return
     */
    int updatePassword(Map map);

    /**
     * 获取密码
     * @param username
     * @return
     */
    String getPassword(String username);

    /**
     * 获取用户是否已经存在
     * @param username
     * @return
     */
    int getCountUser(String username);

    /**
     * 将新注册的用户加入用户表中
     * @param user
     * @return
     */
    int registUser(User user);

    /**
     * 通过用户主键--用户名获取用户的信息
     * @param username
     * @return
     */
    User selectUserById(String username);

    /**
     * 删除指定用户
     * @param userId
     * @return
     */
    int deleteByUsername(String userId);
}