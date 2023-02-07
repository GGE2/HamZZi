import React from "react";
import axios from "axios";

const KakaoLogin = () => {
  const PARAMS = new URL(document.location).searchParams;
  const KAKAO_CODE = PARAMS.get("code");

  const kakaologin = async () => {
    try {
      const res = await axios.post("http://3.35.88.23:8001/kakao_login/web", {
        authorize_code: KAKAO_CODE,
      });
      console.log(res);
    } catch (err) {
      console.log(err);
    }
  };

  // useEffect() {

  // }

  return (
    <div>
      {KAKAO_CODE}
      기다려 로그인 중임~
    </div>
  );
};

export default KakaoLogin;
