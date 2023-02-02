import { auth } from "../../Firebase";
import { GoogleAuthProvider, signInWithPopup, signOut } from "firebase/auth";
import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router";
import { useDispatch } from "react-redux";
import { setCredentials } from "../../authSlice";

function Google() {
  const [userData, setUserData] = useState(null);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  async function handleGoogleLogin() {
    try {
      const provider = new GoogleAuthProvider(); // provider 구글 설정
      const data = await signInWithPopup(auth, provider); // 팝업창 띄워서 로그인

      setUserData(data.user); // user data 설정
      console.log(data); // console에 UserCredentialImpl 출력
      localStorage.setItem("user", JSON.stringify(data.user.email));
      localStorage.setItem(
        "accessToken",
        JSON.stringify(data.user.accessToken)
      );

      // uid 보내기
      const dummy = await axios.post(
        // 주소 수정 필요
        "http://3.35.88.23:8080/api/user/register",
        {
          email: data.user.email,
          uid: data.user.uid,
        }
      );
      console.log(dummy);
      // dummy가 true -> 기존 사용자 -> 메인페이지 이동
      // false -> 신규 사용자 -> 닉네임 설정 페이지 이동
      dummy ? navigate("/main") : navigate("/nickname");
    } catch (err) {
      console.log(err);
    }
  }

  function handleGoogleLogout() {
    signOut(auth)
      .then(() => {
        console.log(userData);
        localStorage.clear();
        console.log("로그아웃");
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
