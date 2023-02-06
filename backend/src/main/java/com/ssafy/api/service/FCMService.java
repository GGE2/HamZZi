package com.ssafy.api.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.ssafy.db.entity.FCM.FCMMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FCMService {
    private String API_URL = "https://fcm.googleapis.com/v1/projects/teamrestructuring-8b91c/messages:send";
    private final ObjectMapper objectMapper;

    private String getAccessToken() throws IOException{
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("firebase.json");
        System.out.println("token"+is.toString());
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(is)
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));
        googleCredentials.refreshIfExpired();
        System.out.println("googleCredential="+googleCredentials.getAccessToken());
        System.out.println(googleCredentials.getAccessToken().getTokenValue());
        return googleCredentials.getAccessToken().getTokenValue();
    }

    public void sendMessageNotification(String targetToken,String title,String body) throws IOException{
        String message = makeMessage(targetToken,title,body);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(message, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        Response response = client.newCall(request).execute();

        log.info(response.body().string());
    }

    public String makeMessage(String targetToken,String title,String body) throws JsonParseException, JsonProcessingException {
        FCMMessage fcmMessage = FCMMessage.builder()
                .message(FCMMessage.Message.builder()
                        .token(targetToken)
                        .notification(FCMMessage.Notification.builder()
                                .title(title)
                                .body(body)
                                .image(null)
                                .build()
                        )
                        .build()
                )
                .validate_only(false)
                .build();

        return objectMapper.writeValueAsString(fcmMessage);

    }
    public String sendMessageData(String title,String message,String token) throws FirebaseMessagingException {
        Message msg = Message.builder()
                .setAndroidConfig(AndroidConfig.builder()
                        .setTtl(3600*1000)
                        .setPriority(AndroidConfig.Priority.HIGH)
                        .setRestrictedPackageName("com.team.teamrestructuring")
                        .setDirectBootOk(true)
                        .build())
                .putData("title",title)
                .putData("message",message)
                .setToken(token).build();

        String response = FirebaseMessaging.getInstance().send(msg);

        return response;
    }




}
