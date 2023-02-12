package com.ssafy.api.controller;

import com.ssafy.api.service.KakaoService;
import com.ssafy.api.service.KakaoServiceForAndroid;
import com.ssafy.api.service.KakaoServiceForWeb;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("api/kakao")
@Api(value = "Kakao API", tags = {"Kakao"})
public class MainController {
    @Autowired
    KakaoServiceForAndroid kakaoServiceForAndroid;
    @Autowired
    KakaoServiceForWeb kakaoServiceForWeb;

//
//    @ResponseBody
    @GetMapping()
    public String testConnect() {
        return "연결성공";
    }
    @GetMapping("/app")
    public Map<String,Object> kakao(@RequestParam("code") String code){
        Map<String,Object> result = kakaoServiceForAndroid.execKakaoLogin(code);
        System.out.println(result.toString());
        return result;
    }
    @RequestMapping("/web")
    public String kakaoSignIn(@RequestParam("code") String code) {
        Map<String,Object> result = kakaoServiceForWeb.execKakaoLogin(code);
        System.out.println(result.toString());
        return "redirect:98207802012408560593bd7763f3bedd://ouath?customToken="+result.get("customToken").toString();
    }
}
