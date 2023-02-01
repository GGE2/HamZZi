import React, { useState } from "react";
import Google from "./loginpages/Google";
import Kakao from "./loginpages/Kakao";
import LoginForm from "./loginpages/LoginForm";
import Signup from "./loginpages/Signup";
import "../styles/Login.css";

const Login = () => {
  const [loginflag, setloginflag] = useState(true);

  const onClickSelectLogin = () => {
    setloginflag(true);
  };
  const onClickSelectSignup = () => {
    setloginflag(false);
  };

  return (
    <div className="loginbody">
<<<<<<< HEAD
      {/* <div className="loginback"> */}
        
        {loginflag ? (
          <div className="loginback">
            <div className="selectlogin">
          <button
            // className="selectBtn1"
            style={{ borderRight: "1px solid black" }}
            onClick={onClickSelectLogin}
            className={loginflag ? 'selectBtn1' : 'selectBtn2'}
          >
            로그인
          </button>

          <button className={loginflag ? 'selectBtn2' : 'selectBtn1'} onClick={onClickSelectSignup}
          >
            회원가입
          </button>
        </div>
=======
      <div className="loginback">
        <div className="selectlogin">
          <div>
            <button onClick={onClickSelectLogin}>로그인페이지</button>
          </div>
          <div>
            <button onClick={onClickSelectSignup}>회원가입페이지</button>
          </div>
        </div>
        {loginflag ? (
          <div>
>>>>>>> feature/mobile/homepage
            <LoginForm />
            <Google />
            <Kakao />
          </div>
        ) : (
<<<<<<< HEAD
          <div className="loginback">
            <div className="selectlogin">
          <button
            // className="selectBtn1"
            style={{ borderRight: "1px solid black" }}
            onClick={onClickSelectLogin}
            className={loginflag ? 'selectBtn1' : 'selectBtn2'}
          >
            로그인
          </button>

          <button className={loginflag ? 'selectBtn2' : 'selectBtn1'} onClick={onClickSelectSignup}
          >
            회원가입
          </button>
        </div>
=======
          <div>
>>>>>>> feature/mobile/homepage
            <Signup />
            <Google />
            <Kakao />
          </div>
        )}
      </div>
<<<<<<< HEAD
    // </div>
=======
    </div>
>>>>>>> feature/mobile/homepage
  );
};

export default Login;
