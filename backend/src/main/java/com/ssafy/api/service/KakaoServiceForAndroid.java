package com.ssafy.api.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.auth.UserRecord.UpdateRequest;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class KakaoServiceForAndroid {

    private static final Logger log = LogManager.getLogger(KakaoServiceForAndroid.class);

    // 카카오 로그인 프로세스 진행 (최종 목표는 Firebase CustomToken 발행)
    public Map<String,Object> execKakaoLogin(String authorize_code) {
        Map<String,Object> result = new HashMap<String,Object>();

        /*System.out.println("authorize_code="+authorize_code);
        // 1. 엑세스 토큰 받기
        String accessToken = getKakaoAccessToken(authorize_code);
        result.put("accessToken", accessToken);

        System.out.println("accessToken="+accessToken);*/

        // 2. 사용자 정보 읽어오기
        Map<String,Object> userInfo = getKakaoUserInfo(authorize_code);
        result.put("userInfo", userInfo);

        // 3. Firebase CustomToken 발행
        if(userInfo != null) {
            try {
                result.put("customToken", createFirebaseCustomToken(userInfo));
                result.put("errYn", "N");
                result.put("errMsg", "");
            } catch (FirebaseAuthException e) {
                // firebase 로그인 에러
                result.put("errYn", "Y");
                result.put("errMsg", "FirebaseException : "+ e.getMessage());
            } catch(Exception e) {
                result.put("errYn", "Y");
                result.put("errMsg", "Exception : "+ e.getMessage());
            }

        } else {
            // 카카오 로그인 취소 or 실패
            result.put("errYn", "Y");
            result.put("errMsg", "Kakao Login Fail");
        }

        System.out.println(userInfo.toString());
        System.out.println(result.get("customToken"));
        return result;
    }

    public String getKakaoAccessToken(String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            // key바뀌면 key값 변경하기
            sb.append("&client_id=ee31ee0a2e88cca397f0fded9f02b392");  //본인이 발급받은 key
            // 서버에 올릴때 주소 바꾸기
            sb.append("&redirect_uri=http://3.35.88.23:8080/api/kakao/app");     // 본인이 설정해 놓은 경로
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            // 결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.debug("responseCode : " + responseCode);

            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.debug("response body : " + result);

            // Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonElement element = JsonParser.parseString(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            log.debug("access_token : " + access_Token);
            log.debug("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return access_Token;
    }

    public Map<String, Object> getKakaoUserInfo (String access_Token) {

        //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        Map<String, Object> userInfo = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            log.debug("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.debug("response body : " + result);

            JsonElement element = JsonParser.parseString(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();
            String id = element.getAsJsonObject().get("id").getAsString();

            userInfo.put("id", id);
            userInfo.put("nickname", nickname);
            userInfo.put("email", email);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    public String kakaoLogout(String access_Token) {
        String reqURL = "https://kapi.kakao.com/v1/user/logout";
        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            // 결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.debug("responseCode : " + responseCode);

            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.debug("response body : " + result);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }

    // 기본 적으로 유효기간은 1시간 이며 유저 정보를 이용해서 생성할 수 있는 방법이 어러개 있음. ( 공식문서 참고 )
    public String createFirebaseCustomToken(Map<String,Object> userInfo) throws Exception {

        UserRecord userRecord;
        String uid = userInfo.get("id").toString();
        String email = userInfo.get("email").toString();
        String displayName = userInfo.get("nickname").toString();

        // 1. 사용자 정보로 파이어 베이스 유저정보 update, 사용자 정보가 있다면 userRecord에 유저 정보가 담긴다.
        try {
            UpdateRequest request = new UpdateRequest("kakao"+uid);
            request.setEmail(email);
            request.setDisplayName(displayName);
            userRecord = FirebaseAuth.getInstance().updateUser(request);

            // 1-2. 사용자 정보가 없다면 > catch 구분에서 createUser로 사용자를 생성한다.
        } catch (FirebaseAuthException e) {

            CreateRequest createRequest = new CreateRequest();
            createRequest.setUid("kakao"+uid);
            createRequest.setEmail(email);
            createRequest.setEmailVerified(false);
            createRequest.setDisplayName(displayName);

            userRecord = FirebaseAuth.getInstance().createUser(createRequest);
        }

        // 2. 전달받은 user 정보로 CustomToken을 발행한다.
        return FirebaseAuth.getInstance().createCustomToken(userRecord.getUid());
    }


}
