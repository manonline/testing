package com.testing.mvc.rest;

import com.testing.mvc.controller.vo.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Using @RestController for better readability than @ResponseBody on each method
 */
@RestController
public class ControllerRest {

    /**
     * ResponseBody to surpresse the view and handle the response body directly
     * All the @ResponseBody can be removed by using @RestController annotation on the closs
     */
    @RequestMapping(value = "/users1", method= RequestMethod.GET)
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

    @RequestMapping(value = "/users1/{name}", method= RequestMethod.GET)
    public User getUser(@PathVariable(value="name") String name) {

        User user = new User();
        user.setName(name);
        user.setHobby("Hehe");

        return user;
    }
}