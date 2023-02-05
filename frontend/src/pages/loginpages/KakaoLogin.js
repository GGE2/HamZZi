import React, {useEffect} from "react";
import axios from "axios";

const KakaoLogin = () => {
  const PARAMS = new URL(document.location).searchParams;
  const KAKAO_CODE = PARAMS.get("code");

<<<<<<< HEAD
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
=======
  useEffect(() => {
    axios.post("http://3.35.88.23:8001/kakao_login/web", {
        authorize_code: KAKAO_CODE,
      })
      .then(res => console.log(res))
      .catch(err => console.log(err))
  }, [])
>>>>>>> d71fbd50837b97bd096f25692dba0e251672d352

  return (
    <div>
      {KAKAO_CODE}
      기다려 로그인 중임~
    </div>
  );
};

export default KakaoLogin;
