package com.erget.chatgpt.controller.user;

import com.erget.chatgpt.dto.ResultDto;
import com.erget.chatgpt.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController {


    @Autowired
    SysUserService sysUserService;

    @GetMapping("/signIn")
    public ResultDto<Object> signIn(@RequestParam String username,
                                    @RequestParam String password,@RequestParam String phone){
        return ok(sysUserService.signIn(username,password,phone));
    }


    @GetMapping("/check")
    public ResultDto<Object> check(@RequestParam String username){
        return ok(sysUserService.check(username));
    }


    @GetMapping("/login")
    public ResultDto<Object> login(@RequestParam String username,
                                    @RequestParam String password) throws Exception {
        return ok(sysUserService.login(username,password));
    }


}
