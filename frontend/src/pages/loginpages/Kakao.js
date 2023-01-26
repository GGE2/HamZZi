import React from "react";
import { useEffect, useState } from "react";

function Kakao() {
  const [user, setUser] = useState(null);
  const [isLogin, setIsLogin] = useState(false);
  const { Kakao } = window;

  const initKakao = async () => {
    const jsKey = "b04032665604a78bb091be183ecca878";
    if (Kakao && !Kakao.isInitialized()) {
      await Kakao.init(jsKey);
      console.log(`kakao 초기화 ${Kakao.isInitialized()}`);
    }
  };

  const kakaoLogin = async () => {
    await Kakao.Auth.loginForm({
      success(res) {
        console.log("123");
        console.log(res);
        Kakao.Auth.setAccessToken(res.access_token);
        console.log("카카오 로그인 성공");
        console.log(Kakao.Auth.getAccessToken());

        Kakao.API.request({
          url: "/v2/user/me",
          success(res) {
            console.log("카카오 인가 요청 성공");
            setIsLogin(true);
            const kakaoAccount = res.kakao_account;
            console.log(kakaoAccount);
            localStorage.setItem("email", kakaoAccount.email);
            localStorage.setItem(
              "profileImg",
              kakaoAccount.profile.profile_image_url
            );
            localStorage.setItem("nickname", kakaoAccount.profile.nickname);
          },
          fail(error) {
            console.log(error);
          },
        });
      },
      fail(error) {
        console.log(error);
      },
    });
  };

  const kakaoLogout = () => {
    // const jsKey = "b04032665604a78bb091be183ecca878";
    // Kakao.init(jsKey);
    // Kakao.isInitialized();

    Kakao.Auth.logout((res) => {
      console.log(Kakao.Auth.getAccessToken()); //
      console.log(res);
      //   localStorage.removeItem("email");
      //   localStorage.removeItem("profileImg");
      //   localStorage.removeItem("nickname");
      localStorage.clear();
      deleteCookie();
      setUser(null);
    });
  };
  function deleteCookie() {
    document.cookie =
      "authroize-access-token; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;";
  }

  useEffect(() => {
    initKakao();
    Kakao.Auth.getAccessToken() ? setIsLogin(true) : setIsLogin(false);
  }, [user]);

  useEffect(() => {
    // console.log(isLogin);
    if (isLogin) {
      setUser({
        email: localStorage.getItem("email"),
        profileImg: localStorage.getItem("profileImg"),
        nickname: localStorage.getItem("nickname"),
      });
    }
  }, [isLogin]);

  const token = () => {
    console.log(Kakao.Auth.getAccessToken());
  };

  return (
    <div>

      {user ? (
        <div>
          <button onClick={kakaoLogout}>로그아웃</button>
          <h2>카카오 로그인 성공!</h2>
          <h3>카카오 프로필 사진</h3>
          <img src={user.profileImg} alt="" />
          <h3>카카오 닉네임</h3>
          <h4>{user.nickname}</h4>
          <h3>카카오 이메일</h3>
          <h4>{user.email}</h4>

          {/* <div>{user}</div> */}
        </div>
      ) : (
        <button onClick={kakaoLogin}>
          <img
            src="//k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg"
            width="222"
            alt="카카오 로그인 버튼"
          />
        </button>
      )}
    </div>
  );
}
export default Kakao;
