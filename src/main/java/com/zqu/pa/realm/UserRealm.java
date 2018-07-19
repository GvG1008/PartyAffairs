package com.zqu.pa.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.zqu.pa.entity.User;
import com.zqu.pa.service.UserService;


public class UserRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;

    /**
     * 权限授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取user.roleId
        List<Object> list = principalCollection.asList();
        User user = (User) list.get(1);
        int roleId = user.getRoleId();
        
        //查询数据库，获取身份所对应权限列表
        List<String> permissions = userService.getPermissionMenu(roleId);
        
        //存储权限列表
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }

    /**
     * 身份验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取token里所存储的用户ID和密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        User formUser = new User();
        formUser.setUserId(token.getPrincipal().toString());
        formUser.setPassword(upToken.getPassword().toString());
        
        //数据库查询是否存在该用户
        User user = userService.userlogin(formUser.getUserId());
        
        //如果用户不存在返回null
        if( user==null )
            return null;
        
        List<Object> principals=new ArrayList<Object>();
        principals.add(user.getUserId());
        principals.add(user);
        //返回授权信息
        SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(principals,user.getPassword(),
                ByteSource.Util.bytes(user.getUserId()),this.getName());
                //MD5加密+盐
        return a;
    }

    /**
     * 在权限修改后
     * 在service调用此方法UserRealm.clearCached()
     * 可以及时清空用户缓存
     * 达到权限修改立即生效的目的
     */
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
