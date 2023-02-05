import React, { useState } from "react";
import Google from "./loginpages/Google";
import Kakao from "./loginpages/Kakao";
import LoginForm from "./loginpages/LoginForm";
import Signup from "./loginpages/Signup";
import "../styles/Login.css";
<<<<<<< HEAD
import { useNavigate } from 'react-router';
import Main from './Main';
import { Link } from "react-router-dom";
=======
import { KAKAO_AUTH_URL } from "./../KakaoLoginData";
import axios from "axios";
>>>>>>> d71fbd50837b97bd096f25692dba0e251672d352

const Login = () => {
  const [loginflag, setloginflag] = useState(true);

  const navigate = useNavigate()

  const onClickSelectLogin = () => {
    setloginflag(true);
  };
  const onClickSelectSignup = () => {
    setloginflag(false);
  };
<<<<<<< HEAD
  
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
=======

  const onclickTest = async () => {
    try {
      const test = await axios.get(
        'https://kauth.kakao.com/oauth/authorize?client_id=e595889459eeb8a91530f0a20e725351&redirect_uri=http://3.35.88.23:8001/kakao_login/web&response_type=code&scope=account_email birthday gender profile_nickname'
        // `${KAKAO_AUTH_URL}`
      );
    } catch (err) {
      console.log(err);
    }
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
            <button onClick={onclickTest}>테스트</button>
          </div>
>>>>>>> d71fbd50837b97bd096f25692dba0e251672d352
          <LoginForm />
          <Google />
          <Kakao />
        </div>
      ) : (
        <div className="loginback">
          <div className="selectlogin">
<<<<<<< HEAD
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
            <button
              // className="selectBtn1"
              style={{ borderRight: "1px solid black" }}
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
>>>>>>> d71fbd50837b97bd096f25692dba0e251672d352
          <Signup />
          <Google />
          <Kakao />
        </div>
      )}
    </div>
<<<<<<< HEAD
    
=======
>>>>>>> d71fbd50837b97bd096f25692dba0e251672d352
    // </div>
  );
};

export default Login;
