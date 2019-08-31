package com.example.demo.model.user.controller;


import com.example.demo.common.base.BaseController;
import com.example.demo.common.dto.ParamDto;
import com.example.demo.common.dto.ResultDto;
import com.example.demo.common.exception.exception.BusinessException;
import com.example.demo.common.util.BeanUtil;
import com.example.demo.model.user.dto.UserEditDto;
import com.example.demo.model.user.dto.UserPo;
import com.example.demo.model.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "page", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto page() {
        ParamDto paramDto = getParam();
        return ResultDto.success(userService.page(paramDto, UserPo.class));
    }

    @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto create(@RequestBody UserEditDto userEditDto) {
        UserPo userPo = new UserPo();
        BeanUtil.copyProperties(userEditDto, userPo);
        userPo.setCreateTime(new Date());
        userService.create(userPo);
        return ResultDto.success();
    }

    @PostMapping(value = "update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto update(@RequestBody UserEditDto userEditDto) {
        UserPo userPo = userService.findById(userEditDto.getId(), UserPo.class);
        if (null == userPo) {
            throw new BusinessException("用户不存在");
        }
        userPo.setUsername(userEditDto.getUsername());
        userService.update("id", userPo.getId(), userPo);
        return ResultDto.success();
    }

    @PostMapping(value = "delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto delete(@RequestBody UserEditDto userEditDto) {
        userService.delete("id", userEditDto.getId());
        return ResultDto.success();
    }

}
