package com.erget.chatgpt.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erget.chatgpt.dto.ResultDto;
import com.erget.chatgpt.user.dao.SysUserDao;
import com.erget.chatgpt.user.entity.SysUser;
import com.erget.chatgpt.user.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 对话数据存储
 */
@Slf4j
@Service
public class SysUserService extends ServiceImpl<SysUserDao, SysUser> {

    @Resource
    private JwtUtil jwtUtil;

    /**
     * 返回token
     * @param username
     * @param password
     * @param phone
     * @return
     */
    public String signIn(String username, String password, String phone) {

        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        QueryWrapper<SysUser> eq = wrapper.eq("userName", username);
        SysUser sysUser = getOne(eq);
        if (sysUser == null){
            sysUser = new SysUser();
        }
        sysUser.setUserName(username);
        sysUser.setPassword(password);
        sysUser.setPhone(phone);
        boolean b = saveOrUpdate(sysUser);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("chat","erget");
        return jwtUtil.generateToken(username, hashMap, jwtUtil.getExpirationTime());
    }

    public boolean check(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        QueryWrapper<SysUser> eq = wrapper.eq("userName", username);
        SysUser sysUser = getOne(eq);
        if (sysUser == null){
            return false;
        }
        return true;
    }

    public Map login(String username, String password) throws Exception {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        QueryWrapper<SysUser> eq = wrapper.eq("userName", username);
        SysUser sysUser = getOne(eq);
        HashMap<String, Object> map = new HashMap<>();
        if (sysUser == null){
           throw new Exception("用户不存在");
        }
        if (!sysUser.getPassword().equals(password)){
            throw new Exception("密码错误");
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("chat","erget");
        String token = jwtUtil.generateToken(username, hashMap, jwtUtil.getExpirationTime());

        map.put("token",token);
        map.put("user",username);
        return map;
    }
}
