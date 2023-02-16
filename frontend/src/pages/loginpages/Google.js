import { auth } from "../../Firebase";
import { GoogleAuthProvider, signInWithPopup, signOut } from "firebase/auth";
import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router";
import { useDispatch } from "react-redux";
import { setCredentials } from "../../authSlice";
import api from "./../../components/api";

function Google() {
  const [userData, setUserData] = useState(null);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  async function handleGoogleLogin() {
    try {
      const provider = new GoogleAuthProvider(); // provider 구글 설정
      const data = await signInWithPopup(auth, provider); // 팝업창 띄워서 로그인

      setUserData(data.user); // user data 설정
      const email = data.user.email;
      // console.log(data); // console에 UserCredentialImpl 출력
      localStorage.setItem("user", JSON.stringify(data.user.email));
      localStorage.setItem(
        "accessToken",
        JSON.stringify(data.user.accessToken)
      );
      localStorage.setItem("uid", JSON.stringify(data.user.uid));

      // uid 보내기
      const dummy = await api.get(`/api/user/uid/${email}`);
      // console.log(dummy.data);
      // dummy가 true -> 기존 사용자 -> 메인페이지 이동
      // false -> 신규 사용자 -> 닉네임 설정 페이지 이동
      dummy.data
        ? navigate("/main")
        : api
            .post("/api/user/register", {
              email: email,
              uid: data.user.uid,
            })
            .then(() => {
              navigate("/nickname");
            })
            .catch((err) => {
              console.log(err);
            });
    } catch (err) {
      console.log(err);
    }
  }

  function handleGoogleLogout() {
    signOut(auth)
      .then(() => {
        // console.log(userData);
        localStorage.clear();
        // console.log("로그아웃");
      })
      .catch((err) => {
        console.log(err);
      });
  }

  return (
    <>
      <img
        className="login_btn"
        onClick={handleGoogleLogin}
        src="google.png"
        alt=""
      />
      <div>{userData ? "당신의 이름은 : " + userData.displayName : ""}</div>
      {/* <button onClick={handleGoogleLogout}>로그아웃</button> */}
    </>
  );
}

export default Google;
