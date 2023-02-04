package com.ssafy.api.response;

import com.ssafy.db.entity.Guild.Guild;
import com.ssafy.db.entity.User.UserProfile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserProfileResponse")
public class UserProfileRes {
	/* swagger 응답을 예쁘게 정의하고 싶다면 Res를 작성하자 */

    @ApiModelProperty(name="nickname")
    String nickname;
    @ApiModelProperty(name="point")
    int point;
    @ApiModelProperty(name="guild")
    Guild guild;
    @ApiModelProperty(name="is_admin")
    boolean is_admin;
//    @ApiModelProperty(name="guild_id")
//    Long guild_id;

	public static UserProfileRes of(UserProfile userProfile) {
		UserProfileRes res = new UserProfileRes();
		res.setNickname(userProfile.getNickname());
        res.setPoint(userProfile.getPoint());
		return res;
	}
}
