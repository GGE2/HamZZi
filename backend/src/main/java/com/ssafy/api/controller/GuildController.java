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

    /* Guild-생성 API: 길드를 생성한다 */
    public String foundGuild(@RequestBody String guild_name, UserProfile userProfile) {
        //유저프로필을 받아서 이 사람이 길드에 속해 있는지 확인한다.
        //만약 생성에 추가 조건(돈이나 업적점수...?)이 필요하면 그것도 체크
        //이미 길드가 있다 or 추가 조건에 부합하지 않는다면 오류메세지, 없다면 닉네임 추출
        String founderName = userProfile.getNickname();

        Guild guild = guildService.foundGuild(guild_name);
        //길드명 중복체크 해 말아? <---impl

        guildService.grantAdmin(founderName);
        //길드 생성 후 유저에게 길드id 할당, 어드민 권한 true로 변경
        return guild.getGuild_name() + "found OK";
    }

}
