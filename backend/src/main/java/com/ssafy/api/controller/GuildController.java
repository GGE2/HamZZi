package com.ssafy.api.controller;

import com.ssafy.api.request.UserRegisterRequest;
import com.ssafy.api.service.GuildService;
import com.ssafy.db.entity.Guild.Guild;
import com.ssafy.db.entity.User.UserProfile;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "guild API", tags = {"Guild"})
@RestController @RequestMapping("api/guild")
@RequiredArgsConstructor
public class GuildController {

    private final GuildService guildService;

    @PostMapping("/found")
    @ApiOperation(value = "길드 생성", notes = "길드 이름을 입력받는다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })

    /* Guild-생성 API: 길드를 생성한다 */ //userProfile은 이메일, 닉네임 등으로 변경될 수 있음
    public String foundGuild(@RequestBody String guild_name, @RequestBody UserProfile userProfile) {

        if(!guildService.canJoinGuild(userProfile)) { return "ERROR: " + userProfile.getNickname() + "already joind a guild"; }

        Guild guild = guildService.foundGuild(guild_name);

        guildService.grantAdmin(nickname);

        return guild.getGuild_name() + "FOUND OK";
    }

}
