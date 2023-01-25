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
            <LoginForm />
            <Google />
            <Kakao />
          </div>
        ) : (
          <div>
            <Signup />
            <Google />
            <Kakao />
          </div>
        )}
      </div>
    </div>
  );
};

export default Login;
