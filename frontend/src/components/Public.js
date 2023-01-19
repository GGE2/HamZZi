import { Router, Routes, Route } from "react-router-dom";
// import { REST_API_KEY, REDIRECT_URI } from "./KakaoLoginData";
import { useEffect, useState } from "react";
import Login from "../Login";

function Public() {
  //   const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}`;

  //   const handleLogin = () => {
  //     window.location.href = KAKAO_AUTH_URL;
  //   };

  const [init, setInit] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  // useEffect(() => {
  //   authService.onAuthStateChanged((user) => {
  //     if (user) {
  //       setIsLoggedIn(true);
  //     } else {
  //       setIsLoggedIn(false);
  //     }
  //     setInit(true);
  //   });
  // }, []);

  return (
    <div className="App">
      <div className="Board">
        <div className="Back">
          {/* <div className="slide1">
            <img src="hamzzi.png" alt="" />
          </div>
          <Login className="slide2" /> */}

          <Login />

          {/* <Signup /> */}
          {/* <Signup_firebase /> */}
          {/* <Kakaopop /> */}
        </div>
        {/* <div className="ButtonList">
          <button className="btn"> WHO</button>
          <button className="btn"> THE</button>
          <button className="btn"> HELL</button>
          <button className="btn"> ARE</button>
          <button className="btn"> YOU</button>
          <button className="btn"> ???</button>
        </div> */}
      </div>
    </div>
  );
}

export default Public;
