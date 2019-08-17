package com.example.demo.model.user;

import com.example.demo.common.BaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    BaseRepo baseRepo;

    @RequestMapping(value = "test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String test() {

        return baseRepo.test().toString();
    }
}
