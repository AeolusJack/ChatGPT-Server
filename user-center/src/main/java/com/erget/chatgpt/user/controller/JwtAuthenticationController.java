package com.erget.chatgpt.user.controller;

import com.erget.chatgpt.dto.ResultDto;
import com.erget.chatgpt.user.constant.UserConstant;
import com.erget.chatgpt.user.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;



@Api(tags = "用户JWT认证服务")
@Slf4j
@RestController
@RequestMapping("/ua")
public class JwtAuthenticationController extends BaseController {

    @Resource
    private JwtUtil jwtUtil;

    /**
     * 无拖拽验证码 登录接口
     * @return
     */
    @ApiOperation(value = "用户名密码登录")
    @GetMapping("/login")
    public ResultDto<Object> login(@RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam(defaultValue = UserConstant.DEFAULT_APP_KEY_ERGET) String appKey,
                                   HttpServletResponse response) throws UnsupportedEncodingException {
       //  SysUser sysUser = sysUserService.selectByUserName(username);
        //验证用户
        //sysUserService.checkUser(sysUser, password, appKey);
       // Map<String, Object> claims = sysUserService.getClaimsFromUser(sysUser);
       //  claims.put(JwtConstant.CLAIMS_NAME_SYSTEM_CODE, appKey);
        //Integer expirationTime = jwtUtil.getExpirationTime();
//
//        String token = jwtUtil.generateToken(sysUser.getUsername(), claims, expirationTime);
//        return ok(token);
        return  null;
    }




}