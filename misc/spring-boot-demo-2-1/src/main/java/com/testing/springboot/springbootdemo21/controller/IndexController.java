package com.testing.springboot.springbootdemo21.controller;

import com.testing.springboot.springbootdemo21.bean.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/index")
public class IndexController {

    @Value(value = "${application.secret}")
    private String secret;

    @Value(value = "${application.number}")
    private int number;

    @Value(value = "${application.description}")
    private String description;

    @RequestMapping
    public String index() {
        return "hello world";
    }

    @RequestMapping(value = "get")
    public Map<String, Object> get(@RequestParam String name) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("value", "hello world");
        map.put("secret", secret);
        map.put("number", number);
        map.put("description", description);
        return map;
    }

    @RequestMapping(value = "find/{id}/{name}")
    public User find(@PathVariable int id, @PathVariable String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setDate(new Date());
        return user;
    }
}