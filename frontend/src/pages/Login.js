import React, { useState, useRef } from "react";
import Google from "./loginpages/Google";
import Kakao from "./loginpages/Kakao";
import LoginForm from "./loginpages/LoginForm";
import Signup from "./loginpages/Signup";
import "../styles/Login.css";
import { useNavigate } from "react-router";
import Warning from "../components/Warning";
// import Main from "./Main";
// import { Link } from "react-router-dom";

const Login = () => {
  const [loginflag, setloginflag] = useState(true);
  const [isModal, setIsModal] = useState(false);
  const navigate = useNavigate();

  const onClickSelectLogin = () => {
    setloginflag(true);
  };
  const onClickSelectSignup = () => {
    setloginflag(false);
  };
  const token = localStorage.getItem("accessToken");

  const outside = useRef();
  return (
    <>
      {token ? navigate("/main") : null}
      {isModal && (
        <div
          className="warning"
          ref={outside}
          onClick={(e) => {
            if (e.target === outside.current) setIsModal(false);
          }}
        >
          <Warning
            setIsModal={setIsModal}
            content={"이미 존재하는 계정입니다"}
            content2={"다시 한번 확인해주세요"}
          />
        </div>
      )}
      <div className="loginbody">
        <div className="backimg">
          <img src="title.png" alt="" />
        </div>

        {loginflag ? (
          <div className="loginback">
            {/* <div className="backback"> */}
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
            {/* <Kakao /> */}
            <div className="qrcode">
              <img src="QRCodeImg1.png" alt="" />
            </div>
            {/* </div> */}
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
            <Signup setIsModal={setIsModal} />
            <Google />
            {/* <Kakao /> */}
          </div>
        )}
      </div>
    </>
  );

  // </div>
};

export default Login;
