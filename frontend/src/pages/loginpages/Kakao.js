import React from "react";
import { useEffect, useState } from "react";
import axios from "axios";

function Kakao() {
  const [user, setUser] = useState(null);
  const [isLogin, setIsLogin] = useState(false);
  const { Kakao } = window;

  const redirectUri = "http://3.35.88.23:8001/kakao_login/web";
  const onClickToAuthorize = () => {
    Kakao.Auth.authorize({
      redirectUri: redirectUri,
    });
  };

  useEffect(() => {
    const authorizeCodeFromKakao = window.location.search.split("=")[1];
    if (authorizeCodeFromKakao !== undefined) {
      console.log(`authorizeCodeFromKakao : ${authorizeCodeFromKakao}`);

      const body = {
        grant_type: "authorization_code",
        client_id: "ee31ee0a2e88cca397f0fded9f02b392",
        redirect_uri: redirectUri,
        code: authorizeCodeFromKakao,
      };

      const queryStringBody = Object.keys(body)
        .map((k) => encodeURIComponent(k) + "=" + encodeURI(body[k]))
        .join("&");

      fetch("https://kauth.kakao.com/oauth/token", {
        method: "POST",
        headers: {
          "content-type": "application/x-www-form-urlencoded;charset=utf-8",
        },
        body: queryStringBody,
      })
        .then((res) => res.json())
        .then((data) => {
          console.log(data);
        });
    } else {
      console.log("No AuthorizeCodeFromKakao");
    }
  }, []);

  // const initKakao = async () => {
  //   const jsKey = "e595889459eeb8a91530f0a20e725351";
  //   if (Kakao && !Kakao.isInitialized()) {
  //     await Kakao.init(jsKey);
  //     console.log(`kakao 초기화 ${Kakao.isInitialized()}`);
  //   }
  // };

  // const kakaoLogin = async () => {
  //   await Kakao.Auth.loginForm({
  //     success(res) {
  //       console.log(res);
  //       Kakao.Auth.setAccessToken(res.access_token);
  //       console.log("카카오 로그인 성공");
  //       console.log(Kakao.Auth.getAccessToken());
  //       // axios.post("http://3.35.88.23:8080/api/kakao/sign_in", {
  //       //   authorize_code: res.access_token,
  //       // });

  //       Kakao.API.request({
  //         url: "/v2/user/me",
  //         success(res) {
  //           console.log("카카오 인가 요청 성공");
  //           setIsLogin(true);
  //           const kakaoAccount = res.kakao_account;
  //           console.log(kakaoAccount);
  //           localStorage.setItem("email", kakaoAccount.email);
  //           localStorage.setItem(
  //             "profileImg",
  //             kakaoAccount.profile.profile_image_url
  //           );
  //           localStorage.setItem("nickname", kakaoAccount.profile.nickname);
  //         },
  //         fail(error) {
  //           console.log(error);
  //         },
  //       });
  //     },
  //     fail(error) {
  //       console.log(error);
  //     },
  //   });

  // };

  // const kakaoLogout = () => {
  //   // const jsKey = "b04032665604a78bb091be183ecca878";
  //   // Kakao.init(jsKey);
  //   // Kakao.isInitialized();

  //   Kakao.Auth.logout((res) => {
  //     console.log(Kakao.Auth.getAccessToken()); //
  //     console.log(res);
  //     //   localStorage.removeItem("email");
  //     //   localStorage.removeItem("profileImg");
  //     //   localStorage.removeItem("nickname");
  //     localStorage.clear();
  //     deleteCookie();
  //     setUser(null);
  //   });
  // };
  // function deleteCookie() {
  //   document.cookie =
  //     "authroize-access-token; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;";
  // }

  // useEffect(() => {
  //   initKakao();
  //   Kakao.Auth.getAccessToken() ? setIsLogin(true) : setIsLogin(false);
  // }, [user]);

  // useEffect(() => {
  //   // console.log(isLogin);
  //   if (isLogin) {
  //     setUser({
  //       email: localStorage.getItem("email"),
  //       profileImg: localStorage.getItem("profileImg"),
  //       nickname: localStorage.getItem("nickname"),
  //     });
  //   }
  // }, [isLogin]);

  // const token = () => {
  //   console.log(Kakao.Auth.getAccessToken());
  // };

  return (
    <>
      {user ? (
        <div>
          {/* <button onClick={kakaoLogout}>로그아웃</button> */}
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
        // <button class='login_btn' onClick={kakaoLogin}>
        <img
          onClick={onClickToAuthorize}
          className="login_btn"
          src="kakao.png"
          alt="카카오 로그인 버튼"
        />
        // </button>
      )}
      {/* <a href={KAKAO_AUTH_URL}>카카오로 로그인하기</a> */}
    </>
  );
}
export default Kakao;
