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
  const [isModal2, setIsModal2] = useState(false);
  const [isModal3, setIsModal3] = useState(false);
  const [isModal4, setIsModal4] = useState(false);
  // const [isModal5, setIsModal5] = useState(false);
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

      {isModal2 && (
        <div
          className="warning"
          ref={outside}
          onClick={(e) => {
            if (e.target === outside.current) setIsModal2(false);
          }}
        >
          <Warning
            setIsModal={setIsModal2}
            content={"존재하지 않는 계정입니다"}
            content2={"다시 한번 확인해주세요"}
          />
        </div>
      )}

      {isModal3 && (
        <div
          className="warning"
          ref={outside}
          onClick={(e) => {
            if (e.target === outside.current) setIsModal3(false);
          }}
        >
          <Warning
            setIsModal={setIsModal3}
            content={"비밀번호가 틀렸습니다"}
            content2={"다시 한번 확인해주세요"}
          />
        </div>
      )}

      {isModal4 && (
        <div
          className="warning"
          ref={outside}
          onClick={(e) => {
            if (e.target === outside.current) setIsModal4(false);
          }}
        >
          <Warning
            setIsModal={setIsModal4}
            content={"비밀번호가 틀렸습니다"}
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
            <LoginForm setIsModal={setIsModal2} setIsModal2={setIsModal3}/>
            <Google />
            <div className="qrcode">
              <img src="QRCodeImg1.png" alt="" />
            </div>
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
            <Signup setIsModal={setIsModal} setIsModal4={setIsModal4}/>
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
