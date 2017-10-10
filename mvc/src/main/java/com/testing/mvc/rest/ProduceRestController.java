package com.testing.mvc.rest;

import com.testing.mvc.controller.vo.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.*;
import java.util.ArrayList;

/**
 * Using @RestController for better readability than @ResponseBody on each method
 */
@RestController
public class ProduceRestController {

    /**
     * ResponseBody to surpresse the view and handle the response body directly
     * All the @ResponseBody can be removed by using @RestController annotation on the closs
     */
    @RequestMapping(value = "/users2", method= RequestMethod.GET,
            // request header: accept=application/json
            produces = MediaType.APPLICATION_JSON_VALUE)
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

    @RequestMapping(value = "/users2/{name}", method= RequestMethod.GET,
            // request header: content-type
            consumes = MediaType.APPLICATION_ATOM_XML_VALUE)
    public User putUser3(@PathVariable(value="name") String name, @RequestBody User user) {

        User user1 = new User();
        user1.setName(name);
        user1.setHobby("Hehe");

        return user;
    }

    @RequestMapping(value = "/users23/{name}", method= RequestMethod.GET)
    public ResponseEntity<Void> putUser4(@PathVariable(value="name") String name, @RequestBody User user) {

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    /*@RequestMapping(value = "/users/{name}", method= RequestMethod.GET)
    public ResponseEntity<Void> putUser(@PathVariable(value="name") String name, @RequestBody User user) {

        return new ResponseEntity<Void>(true, HttpStatus.NOT_FOUND);
    }*/

    @RequestMapping(value = "/users22/{name}", method= RequestMethod.GET)
    public ResponseEntity<Boolean> putUser2(@PathVariable(value="name") String name, @RequestBody User user) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("key1", "value1");
        httpHeaders.add("key2", "value2");

        return new ResponseEntity<Boolean>(true, httpHeaders, HttpStatus.NOT_FOUND);
    }

    /**
     *
     * @param name
     * @param user
     * @return
     */
    @RequestMapping(value = "/users2/{name}", method= RequestMethod.POST)
    public ResponseEntity<Boolean> createUser(@PathVariable(value="name") String name, @RequestBody User user) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("location", ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{name}").buildAndExpand(user.getName()).toUri().toString());

        // use CREATED instead of OK
        return new ResponseEntity<Boolean>(true, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users2/{name}", method= RequestMethod.PUT)
    public ResponseEntity<Boolean> changeUser(@PathVariable(value="name") String name, @RequestBody User user) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("key1", "value1");
        httpHeaders.add("key2", "value2");

        return new ResponseEntity<Boolean>(true, httpHeaders, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/users2/{name}", method= RequestMethod.GET)
    public ResponseEntity<Boolean> listUsers(@PathVariable(value="name") String name, @RequestBody User user) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("key1", "value1");
        httpHeaders.add("key2", "value2");

        return new ResponseEntity<Boolean>(true, httpHeaders, HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = "/users2/{name}", method= RequestMethod.DELETE)
    public ResponseEntity<Boolean> removeUser(@PathVariable(value="name") String name, @RequestBody User user) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("key1", "value1");
        httpHeaders.add("key2", "value2");

        return new ResponseEntity<Boolean>(true, httpHeaders, HttpStatus.NOT_FOUND);
    }
}