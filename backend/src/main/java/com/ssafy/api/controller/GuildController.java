package com.ssafy.api.controller;

import com.ssafy.api.request.UserRegisterRequest;
import com.ssafy.api.service.GuildService;
import com.ssafy.db.entity.Guild.Guild;
import com.ssafy.db.entity.User.UserProfile;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "guild API", tags = {"Guild"})
@RestController @RequestMapping("api/guild")
@RequiredArgsConstructor
public class GuildController {

    private final GuildService guildService;
    //userProfile은 이메일, 닉네임 등으로 변경될 수 있음

    @PostMapping("/found")
    @ApiOperation(value = "길드 생성", notes = "길드 이름을 입력받는다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    /* Guild-생성 API: 길드를 생성한다 */
    public String foundGuild(@RequestBody String guild_name, @RequestBody UserProfile userProfile) {
        if(!guildService.canJoinGuild(userProfile)) { return "ERROR: " + userProfile.getNickname() + "already joind a guild"; }

        // 중복체크 되어있어서 길드명 중복시 에러 발생
        Guild guild = guildService.foundGuild(guild_name);
        guildService.grantAdmin(userProfile);

        return guild.getGuild_name() + "FOUND OK\nGRANT admin: " + userProfile.getNickname();
    }

    @PutMapping("/join")
    @ApiOperation(value = "길드 가입", notes = "아이디(guild_id)에 해당하는 길드에 가입한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    /* Guild - 일반 사용자 */
    public String joinGuild(@RequestBody Long guild_id, @RequestBody UserProfile userProfile) {
        // 검증
        if(!guildService.canJoinGuild(userProfile)) { return "ERROR: " + userProfile.getNickname() + "already joind a guild"; }
        // 가입
        Guild guild = guildService.joinGuild(guild_id, userProfile);
        return userProfile.getNickname() + "JOINED " + guild.getGuild_name() + "OK";
    }

    public String leaveGuild(@RequestBody Long guild_id, @RequestBody UserProfile userProfile) {
        if(userProfile.getGuild().getGuild_id() != guild_id) {return "ERROR: 가입한 길드가 아닙니다!!";}
        if(guildService.checkAdmin(userProfile)) {return "길드 관리자는 권한을 포기 후 탈퇴 가능";}
        String guildName = userProfile.getGuild().getGuild_name();
        userProfile = guildService.leaveGuild(guild_id, userProfile);
        // now는 null이 떠야 정상
        return userProfile.getNickname() + " LEAVE " + guildName + "\nNOW: " + userProfile.getGuild();
    }

    @GetMapping("/list")
    @ApiOperation(value = "길드 목록", notes = "모든 길드를 출력한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public List<Guild> guildList() {

    }

    @GetMapping("/list/search")
    @ApiOperation(value = "길드 목록 - 이름 검색", notes = "길드 이름으로 검색해서 출력한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public List<Guild> guildList() {

    }
}