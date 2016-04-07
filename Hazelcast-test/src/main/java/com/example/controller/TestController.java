package com.example.controller;

import com.example.entity.TestMessage;
import com.example.service.TestHazelcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    protected TestHazelcastService service;

    @RequestMapping(method = RequestMethod.GET)
    public TestMessage get() {
        return service.getMessage();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void post(@RequestBody TestMessage message, HttpServletRequest request) {
        service.saveMessage(message);
    }
}
