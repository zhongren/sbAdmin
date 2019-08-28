package com.example.demo.model.user;


import com.example.demo.common.BaseController;
import com.example.demo.common.dto.ParamDto;
import com.example.demo.common.dto.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "page", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto page() {
        ParamDto paramDto=getParam();
        return ResultDto.success(userService.page(paramDto, UserPo.class));
    }
}
