import React, { useState } from "react";
import Google from "./loginpages/Google";
import Kakao from "./loginpages/Kakao";
import LoginForm from "./loginpages/LoginForm";
import Signup from "./loginpages/Signup";
import "../styles/Login.css";
import { useNavigate } from "react-router";
import Main from "./Main";
import { Link } from "react-router-dom";

const Login = () => {
  const [loginflag, setloginflag] = useState(true);

  const navigate = useNavigate();

  const onClickSelectLogin = () => {
    setloginflag(true);
  };
  const onClickSelectSignup = () => {
    setloginflag(false);
  };
  const token = localStorage.getItem("accessToken");

  return (
    <>
      {token ? navigate("/main") : null}
      <div className="loginbody">
        {/* <div className="loginback"> */}
        <div className="backimg">
          <img src="title.png" alt="" />
        </div>

        {loginflag ? (
          <div className="loginback">
            <div className="selectlogin">
              <button
                // className="selectBtn1"
                style={{ borderRight: "1px solid #803a21" }}
                onClick={onClickSelectLogin}
                className={loginflag ? "selectBtn1" : "selectBtn2"}
              >
                로그인
              </button>

              <button
                className={loginflag ? "selectBtn2" : "selectBtn1"}
                onClick={onClickSelectSignup}
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
                style={{ borderRight: "1px solid #803a21" }}
                onClick={onClickSelectLogin}
                className={loginflag ? "selectBtn1" : "selectBtn2"}
              >
                로그인
              </button>

              <button
                className={loginflag ? "selectBtn2" : "selectBtn1"}
                onClick={onClickSelectSignup}
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
    </>
  );

  // </div>
};

export default Login;
