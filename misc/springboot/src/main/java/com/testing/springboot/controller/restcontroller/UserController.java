package com.testing.springboot.controller.restcontroller;

import com.testing.springboot.repository.Customer;
import com.testing.springboot.repository.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/users")
public class UserController {

    @RequestMapping(value="/{user}", method= RequestMethod.GET)
    public User getUser(@PathVariable Long user) {
        // ...
        return new User();
    }

    @RequestMapping(value="/{user}/customers", method=RequestMethod.GET)
    List<Customer> getUserCustomers(@PathVariable Long user) {
        // ...
        return new ArrayList<Customer>();
    }

    @RequestMapping(value="/{user}", method=RequestMethod.DELETE)
    public User deleteUser(@PathVariable Long user) {
        // ...
        return new User();
    }
}
