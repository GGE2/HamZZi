package com.ssafy.api.controller;

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

    /* NO ADMIN */ /////////////////////////////////////////////////////////////////////////////////
    /* Guild 생성 API: 길드를 생성하고 생성한 유저에게 admin 권한을 준다 */ // admin 권한이 필요하지 않음
    @PostMapping("/found")
    @ApiOperation(value = "길드 생성", notes = "길드 이름을 입력받는다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String foundGuild(@RequestBody String guild_name, @RequestBody String nickname) {
        if(!guildService.canJoinGuild(nickname)) { return "ERROR: " + nickname + "already joind a guild"; }

        Guild guild = guildService.foundGuild(guild_name);
        guildService.grantAdmin(nickname);

        return guild.getGuild_name() + "FOUND OK\nGRANT admin: " + nickname;
    }
    /* Guild 가입 API: 아이디(guild_id)에 해당하는 길드에 가입한다 */
    @PutMapping("/join")
    @ApiOperation(value = "길드 가입", notes = "아이디(guild_id)에 해당하는 길드에 가입한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String joinGuild(@RequestBody Long guild_id, @RequestBody String nickname) {
        boolean check = guildService.joinGuild(guild_id, nickname);
        if(!check) { return "ERROR: " + nickname + "already joind a guild"; }
        return nickname + "JOIN guild\nGUILD ID: " + guild_id ;
    }
    /* Guild 탈퇴 API: 사용자가 소속된 길드를 탈퇴한다 */
    @GetMapping("/leave")
    @ApiOperation(value = "길드 탈퇴", notes = "길드를 탈퇴한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String leaveGuild(@RequestBody Long guild_id, @RequestBody String nickname) {
        boolean check = guildService.leaveGuild(guild_id, nickname);
        if(!check) { return "ERROR: CHECK admin or " + nickname + "'s guild_id IS NOT " + guild_id; }
        return nickname + "LEAVE guild\nGUILD ID: " + guild_id ;
    }

    //list들 Res 타입으로 응답시킬까...?
    /* FIND */ /////////////////////////////////////////////////////////////////////////////////////
    /* Guild 리스트: 전체 길드 리스트 */
    @GetMapping("/list")
    @ApiOperation(value = "길드 목록", notes = "모든 길드를 출력한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public List<Guild> guildList() {
        List<Guild> list = guildService.findGuildList();
        int listSize = list.size();
        return list;
    }
    /* Guild 리스트: LIKE %guild_name% 길드 리스트 */
    @GetMapping("/list/search")
    @ApiOperation(value = "길드 목록 - 이름 검색", notes = "guild_name 을 포함하는 길드 전체 목록")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public List<Guild> findGuildListByName(@RequestParam String guild_name) {
        List<Guild> list = guildService.findGuildListByName(guild_name);
        int listSize = list.size();
        return list;
    }
    /* Guild 세부정보: 길드 정보 출력 */
    @GetMapping("/detail")
    @ApiOperation(value = "길드 세부정보", notes = "길드의 정보를 출력한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public Guild findGuild(@RequestParam Long guild_id) {
        Guild guild = guildService.findGuild(guild_id);
        return guild;
    }
    /* Guild 세부정보: 길드 소속 사용자 정보(NO admin) 출력 */
    @GetMapping("/detail/user")
    @ApiOperation(value = "길드 세부정보-사용자", notes = "일반 길드원을 출력한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public List<UserProfile> findGuildUser(@RequestParam Long guild_id) {
        List<UserProfile> list = guildService.findGuildUser(guild_id);
        int listSize = list.size();
        return list;
    }
    /* Guild 세부정보: 길드 소속 어드민 정보 출력 */
    @GetMapping("/detail/admin")
    @ApiOperation(value = "길드 세부정보-관리자", notes = "길드 관리자를 출력한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public List<UserProfile> findGuildAdmin(@RequestParam Long guild_id) {
        List<UserProfile> list = guildService.findGuildAdmin(guild_id);
        int listSize = list.size();
        return list;
    }

    //어드민 권한이 없으면 사용할 수 없는 기능
    /* ADMIN ONLY */ ///////////////////////////////////////////////////////////////////////////////
    /* ADMIN 임명: 같은 길드의 일반 길드원을 admin에 임명한다 */
    @PutMapping("/admin/grant")
    @ApiOperation(value = "길드 관리자 임명", notes = "일반 길드원을 관리자에 임명한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String grantAdmin(@RequestParam String nickname_admin, @RequestParam String nickname_granted) {
        //A의 소속 길드와 B의 소속 길드가 같은지 체크
        Long guildA = guildService.belongingGuild(nickname_admin);
        Long guildB = guildService.belongingGuild(nickname_granted);
        if(guildA != guildB) { return "ERROR: NO USER: " + nickname_granted + " IN THIS GUILD"; }

        //A가 B를 관리자에 임명한다고 했을 때, A의 권한 체크
        if(!guildService.checkAdmin(nickname_admin)) { return "ERROR: GRANT - CAN'T ACCESS GENERAL USER"; }
        boolean check = guildService.grantAdmin(nickname_granted);
        //B에게 권한부여 실패 - B가 이미 어드민이거나 길드 미소속이면 false
        if(!check) { return "FAIL TO GRANT ADMIN: " + nickname_granted; }
        return nickname_admin + "GRANT " + nickname_granted + "ADMIN";
    }

    /* ADMIN 그만두기: 자신의 is_admin을 false로 만든다 */
    @PutMapping("/admin/quit")
    @ApiOperation(value = "길드 관리자 그만두기", notes = "길드 관리자를 그만둔다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String quitAdmin(@RequestParam String nickname) {
        boolean check = guildService.quitAdmin(nickname);
        if(!check) { return "ERROR: QuitAdmin - CAN'T ACCESS GENERAL USER OR NO GUILD USER"; }
        return nickname + ": NOW GENERAL USER";
    }
    /* GUILD에서 사용자 강퇴: 해당 nickname의 일반 user를 길드에서 강퇴한다 */
    @PutMapping("/kick")
    @ApiOperation(value = "길드 강퇴", notes = "어드민이 길드에서 일반유저를 강퇴시킨다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String kickGuildUser(@RequestParam String nickname_admin, @RequestParam String nickname_user) {
        //A의 소속 길드와 B의 소속 길드가 같은지 체크
        Long guildA = guildService.belongingGuild(nickname_admin);
        Long guildB = guildService.belongingGuild(nickname_user);
        if(guildA != guildB) { return "ERROR: NO USER: " + nickname_user + " IN THIS GUILD"; }

        //A가 B를 강퇴한다고 했을 때, A의 권한 체크
        if(!guildService.checkAdmin(nickname_admin)) { return "ERROR: KICK - CAN'T ACCESS GENERAL USER"; }
        boolean check = guildService.kickUser(nickname_user);
        // 어드민이면 강퇴할 수 없음
        if(!check) { return "FAIL TO KICK: " + nickname_user; }
        return nickname_user + "KICKED BY" + nickname_admin;
    }
    /* GUILD 삭제: 길드를 삭제한다 */
    @DeleteMapping("/delete")
    @ApiOperation(value = "길드 삭제", notes = "길드를 삭제한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String deleteGuild(@RequestParam Long guild_id, @RequestParam String nickname) {
        if(!guildService.checkAdmin(nickname)) { return "ERROR: DELETE GUILD - CAN'T ACCESS GENERAL USER"; }

        String guild_name = guildService.findGuild(guild_id).getGuild_name();

        boolean check = guildService.deleteGuild(guild_id);
        if(!check) { return "FAIL TO DELETE GUILD"; }

        Guild guild = guildService.findGuild(guild_id);
        //now STATUS null 이 정상!!
        return guild_name + "DELETE\nNOW STATUS:" + guild;
    }
}