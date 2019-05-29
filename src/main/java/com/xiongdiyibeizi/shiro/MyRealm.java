package com.xiongdiyibeizi.shiro;

import com.xiongdiyibeizi.bean.user.User;
import com.xiongdiyibeizi.service.api.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权1");

        //从principalCollection中获取登录用户的信息
        Object principal = principalCollection.getPrimaryPrincipal();

        //利用登录的用户的信息来验证用户的角色或权限(可能需要查询数据库)
        User user = userService.selectUserById((String)principal);

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("user",user);
        /*Set<String> roles = new HashSet<>();
        roles.add("user");
        if (principal.equals("admin")){
            roles.add("admin");
        }*/
        //创建SimpleAuthenticationInfo，并设置其roles属性
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //返回SimpleAuthenticationInfo对象
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证1");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;


        String username = token.getUsername();
        String password = null;
        User user = userService.selectUserById(username);
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("user",user);
        password = user.getPassword();
        ByteSource salt = ByteSource.Util.bytes(username);//此处需要用唯一性的字符串，可以是随机数或者userId
        String  realm = getName();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password,salt,realm);
        return info;
    }

}
