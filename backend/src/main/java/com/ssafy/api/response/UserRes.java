package com.ssafy.api.response;

import com.ssafy.db.entity.User.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 본인 정보 조회 API ([GET] /api/v1/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserResponse")
public class UserRes {
	@ApiModelProperty(name="User ID")
	Long user_id;

	public static UserRes of(User user) {
		UserRes res = new UserRes();
		res.setUser_id(user.getUser_id());
		return res;
	}
}
