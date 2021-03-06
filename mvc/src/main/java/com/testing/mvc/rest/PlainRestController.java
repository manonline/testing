package com.testing.mvc.rest;

import com.testing.mvc.controller.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PlainRestController {

    /**
     * ResponseBody to surpresse the view and handle the response body directly
     * All the @ResponseBody can be removed by using @RestController annotation on the closs
     */
    @ResponseBody
    @RequestMapping(value = "/users", method= RequestMethod.GET)
    public ArrayList<User> getUserList() {
        User user1 = new User();
        user1.setName("David");

        User user2 = new User();
        user2.setName("Mike");

        ArrayList<User> userList = new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);

        return userList;
    }

    @ResponseBody
    @RequestMapping(value = "/users/{name}", method= RequestMethod.GET)
    public User getUser(@PathVariable(value="name") String name) {

        User user = new User();
        user.setName(name);
        user.setHobby("Hehe");

        return user;
    }
}