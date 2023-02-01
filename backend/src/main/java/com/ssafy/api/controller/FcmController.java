package com.ssafy.api.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.ssafy.api.service.FCMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class FcmController {

    @Autowired
    FCMService fcmService;

    @PostMapping("/fcm")
    public String sendPush(@RequestParam("title") String title,
                           @RequestParam("targetToken") String token,
                           @RequestParam("body") String body
                           ) throws IOException, FirebaseMessagingException {

        //fcmService.sendMessageTo(token, title, body);
        fcmService.sendMessageData(title,body,token);
        return "check";
    }


}
