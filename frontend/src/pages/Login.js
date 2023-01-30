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
            <LoginForm />
            <Google />
            <Kakao />
          </div>
        ) : (
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
            <Signup />
            <Google />
            <Kakao />
          </div>
        )}
      </div>
    // </div>
  );
};

export default Login;