package com.erget.chatgpt.user.controller;

import com.erget.chatgpt.dto.ResultDto;
import com.erget.chatgpt.user.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "用户")
@Slf4j
@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController {


    @Autowired
    SysUserService sysUserService;

    @ApiOperation(value = "用户名密码注册")
    @GetMapping("/signIn")
    public ResultDto<Object> signIn(@RequestParam String username,
                                    @RequestParam String password,@RequestParam String phone){
        return ok(sysUserService.signIn(username,password,phone));
    }


    @ApiOperation(value = "用户名验重")
    @GetMapping("/check")
    public ResultDto<Object> check(@RequestParam String username){
        return ok(sysUserService.check(username));
    }


    @ApiOperation(value = "用户名密码登录")
    @GetMapping("/login")
    public ResultDto<Object> login(@RequestParam String username,
                                    @RequestParam String password) throws Exception {
        return ok(sysUserService.login(username,password));
    }


}
