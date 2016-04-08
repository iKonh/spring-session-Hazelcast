package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.TestMessage;

/**
 * Created by Huangyipeng on 2016/04/07.
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    public TestMessage get(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (TestMessage)session.getAttribute("test");
    }

    @RequestMapping(method = RequestMethod.POST)
    public void post(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("test") == null) {
            TestMessage testMessage = new TestMessage();
            session.setAttribute("test", testMessage);
        } else {
            TestMessage testMessage = (TestMessage)session.getAttribute("test");
            testMessage.count += 1;
        }
    }
}
