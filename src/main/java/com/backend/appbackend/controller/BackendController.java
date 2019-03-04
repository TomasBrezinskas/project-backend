package com.backend.appbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class BackendController {

    @RequestMapping("/hello")
    @ResponseBody
    public HashMap<String, String> returnHello() {
        HashMap<String, String> map = new HashMap<>();
        map.put("hello", "people");
        return map;
    }
}
