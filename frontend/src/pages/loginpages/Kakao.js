import React from "react";
import { useEffect, useState } from "react";
<<<<<<< HEAD
import axios from "axios";
import {KAKAO_AUTH_URL} from './../../KakaoLoginData';

=======
>>>>>>> feature/mobile/homepage

function Kakao() {
  const [user, setUser] = useState(null);
  const [isLogin, setIsLogin] = useState(false);
<<<<<<< HEAD
  // const [acctoken, setToken] = useState('');
=======
>>>>>>> feature/mobile/homepage
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
<<<<<<< HEAD
        // console.log("123");
        console.log(res);
        Kakao.Auth.setAccessToken(res.access_token);
        // setToken(res.access_token)
        console.log("카카오 로그인 성공");
        console.log(Kakao.Auth.getAccessToken());
        axios.post("http://3.35.88.23:8080/api/kakao/sign_in", {
          authorize_code: res.access_token,
        });
=======
        console.log("123");
        console.log(res);
        Kakao.Auth.setAccessToken(res.access_token);
        console.log("카카오 로그인 성공");
        console.log(Kakao.Auth.getAccessToken());
>>>>>>> feature/mobile/homepage

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
<<<<<<< HEAD

            // console.log(test);
=======
>>>>>>> feature/mobile/homepage
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
<<<<<<< HEAD

    // axios
    //       .post("http://3.35.88.23:8080/api/kakao/sign_in", {
    //         accessToken: res.id_token
    //         // name: kakaoAccount.profile.nickname,
    // email: kakaoAccount.email,
    // name: kakaoAccount.profile.nickname,
    //       })
    //       .then((res) => {
    //         console.log(res);
    //       })
    //       .catch((err) => {
    //         console.log(err);
    //       });
=======
>>>>>>> feature/mobile/homepage
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
<<<<<<< HEAD
    // console.log(isLogin);
=======
    console.log(isLogin);
>>>>>>> feature/mobile/homepage
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
<<<<<<< HEAD
    <>
      
=======
    <div>

>>>>>>> feature/mobile/homepage
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
<<<<<<< HEAD
        // <button class='login_btn' onClick={kakaoLogin}>
        <img
          onClick={kakaoLogin}
          className="login_btn"
          src="kakao.png"
          alt="카카오 로그인 버튼"
        />
        // </button>
      )}
      <a href={KAKAO_AUTH_URL}>카카오로 로그인하기</a>
    </>
    
=======
        <button onClick={kakaoLogin}>
          <img
            src="//k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg"
            width="222"
            alt="카카오 로그인 버튼"
          />
        </button>
      )}
    </div>
>>>>>>> feature/mobile/homepage
  );
}
export default Kakao;
