import { auth } from "../../Firebase";
import { GoogleAuthProvider, signInWithPopup, signOut } from "firebase/auth";
import { useState } from "react";
import axios from "axios";

function Google() {
  const [userData, setUserData] = useState(null);

  function handleGoogleLogin() {
    const provider = new GoogleAuthProvider(); // provider 구글 설정
    signInWithPopup(auth, provider) // 팝업창 띄워서 로그인
      .then((data) => {
        const userData = data.user;
        setUserData(userData); // user data 설정
        console.log(userData); // console에 UserCredentialImpl 출력
        axios
          .post("/api", {
            email: userData.email,
            name: userData.displayName,
          })
          .then((res) => {
            console.log(res);
          });
      })
      .catch((err) => {
        console.log(err);
      });
  }

  function handleGoogleLogout() {
    signOut(auth)
      .then(() => {
        console.log(userData);
        console.log("로그아웃");
      })
      .catch((err) => {
        console.log(err);
      });
  }

  return (
    <div>
      <button onClick={handleGoogleLogin}>
        <img src="btn_google_signin_dark_normal_web.png" alt="" />
      </button>
      <div>
        {userData
          ? "당신의 이름은 : " + userData.displayName
          : "로그인 버튼을 눌러주세요 :)"}
      </div>
      <button onClick={handleGoogleLogout}>로그아웃</button>
    </div>
  );
}

export default Google;
