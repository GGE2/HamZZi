package com.ssafy.api.response;

import com.ssafy.db.entity.User.User;
import com.ssafy.db.entity.User.UserProfile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserResponse")
public class UserRes {
	/* swagger 응답을 예쁘게 정의하고 싶다면 Res를 작성하자 */

    @ApiModelProperty(name="user_id")
    Long user_id;
    @ApiModelProperty(name="email")
    String email;
    @ApiModelProperty(name="uid")
    String uid;
    @ApiModelProperty(name="fcmToken")
    String fcmToken;
    @ApiModelProperty(name="userProfile")
    UserProfile userProfile;

	public static UserRes of(User user) {
		UserRes res = new UserRes();
		res.setUser_id(user.getUser_id());
        res.setEmail(user.getEmail());
        res.setUid(user.getUid());
        res.setFcmToken(user.getFcm_token());
        res.setUserProfile(user.getUserProfile());
		return res;
	}
}
