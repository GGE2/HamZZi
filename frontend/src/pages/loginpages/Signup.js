import React, { useEffect, useState, useRef } from "react";
import "../../styles/Signup.css";
import { auth } from "../../Firebase";
import {
  createUserWithEmailAndPassword,
  signInWithEmailAndPassword,
} from "firebase/auth";
import axios from "axios";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { setCredentials } from "../../authSlice";
import api from "./../../components/api";

import { AiOutlineCheckCircle, AiOutlineCloseCircle } from "react-icons/ai";
import Warning from "./../../components/Warning";

export default function Signup({ setIsModal, setIsModal4 }) {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  // const [isModal, setIsModal] = useState(false);

  // 회원가입 데이터(이메일, 비밀번호, 비밀번호확인, 닉네임, 이름, 성별, 전화번호)
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [passwordConfirm, setPasswordConfirm] = useState("");

  // 오류메세지 상태 저장
  const [emailMessage, setEmailMessage] = useState("");
  const [passwordMessage, setPasswordMessage] = useState("");
  const [passwordConfirmMessage, setPasswordConfirmMessage] = useState("");

  // 검사
  const [emailValid, setEmailValid] = useState(false); // 이메일 유효성 검사
  const [passwordValid, setPasswordValid] = useState(false); // 비밀번호 유효성 검사
  const [passwordConfirmValid, setPasswordConfirmValid] = useState(false);

  const [notAllow, setNotAllow] = useState(true); // 회원가입 검사

  // 파이어 베이스 회원가입하기
  const [errorMsg, setErrorMsg] = useState(" ");

  const register = async () => {
    try {
      setErrorMsg(" ");
      // 파이어 베이스에 가입하기
      const createdUser = await createUserWithEmailAndPassword(
        auth,
        email,
        password
      );
      // console.log(createdUser);
      // 우리 db에 정보 전달
      // const dummy = await axios.post(
      //   "http://3.35.88.23:8080/api/user/register",
      //   {
      //     email: email,
      //     uid: createdUser.user.uid,
      //   }
      // );

      // 로그인 로직
      const curUserInfo = await signInWithEmailAndPassword(
        auth,
        email,
        password
      );
      // console.log(curUserInfo);

      setEmail("");
      setPassword("");
      setPasswordConfirm("");
      // localStorage 저장
      localStorage.setItem("user", JSON.stringify(curUserInfo.user.email));
      localStorage.setItem(
        "accessToken",
        JSON.stringify(curUserInfo.user.accessToken)
      );
      localStorage.setItem("uid", JSON.stringify(curUserInfo.user.uid));
      dispatch(
        setCredentials({
          user: curUserInfo.user.displayName,
          token: curUserInfo.user.accessToken,
        })
      );
      // uid 보내기
      const dummy = await api.post("/api/user/register", {
        email: curUserInfo.user.email,
        uid: curUserInfo.user.uid,
      });
      navigate("/nickname");
    } catch (err) {
      console.log(err.code);
      switch (err.code) {
        case "auth/weak-password":
          setIsModal(true);
          // alert("비밀번호는 8자리 이상이어야 합니다")
          setErrorMsg("비밀번호는 8자리 이상이어야 합니다");
          break;
        case "auth/invalid-email":
          setIsModal4(true);
          // alert("잘못된 이메일 주소입니다")
          setErrorMsg("잘못된 이메일 주소입니다");
          break;
        case "auth/email-already-in-use":
          // alert("이미 가입되어 있는 계정입니다")
          setIsModal(true);
          setErrorMsg("이미 가입되어 있는 계정입니다");
          break;
        default:
          break;
      }
    }
  };

  // 회원가입 버튼 활성화 체크
  useEffect(() => {
    if (emailValid && passwordValid && passwordConfirmValid) {
      setNotAllow(false);
      return;
    }
    setNotAllow(true);
  }, [emailValid, passwordValid, passwordConfirmValid]);

  // 이메일 유효성 검사
  const handleEmail = (e) => {
    const currentEmail = e.target.value;
    // console.log(currentEmail)
    setEmail(currentEmail);
    // console.log(email);
    const regex =
      /^(([^<>()\[\].,;:\s@"]+(\.[^<>()\[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i;
    if (currentEmail === "") {
      setEmailMessage("");
      setEmailValid(false);
    } else if (!regex.test(e.target.value)) {
      setEmailMessage("이메일의 형식이 올바르지 않습니다!");
      setEmailValid(false);
    } else {
      setEmailMessage("사용 가능한 이메일 입니다.");
      setEmailValid(true);
    }
  };

  // 비밀번호 유효성 검사
  const handlePw = (e) => {
    const currentPassword = e.target.value;
    setPassword(currentPassword);
    // console.log(1123123);
    // console.log(e.target.value);
    const regex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
    if (currentPassword === "") {
      setPasswordMessage("");
      setPasswordValid(false);
    } else if (!regex.test(e.target.value)) {
      setPasswordMessage(
        "숫자+영문자+특수문자 조합으로 8자리 이상 입력해주세요!"
      );
      setPasswordValid(false);
    } else {
      setPasswordMessage("");
      setPasswordValid(true);
    }
  };

  // 비밀번호 확인 유효성 검사
  const handlePasswordConfirm = (e) => {
    const currentPasswordConfirm = e.target.value;
    setPasswordConfirm(currentPasswordConfirm);
    if (currentPasswordConfirm === "") {
      setPasswordConfirmMessage("");
      setPasswordConfirmValid(false);
    } else if (password !== currentPasswordConfirm) {
      setPasswordConfirmMessage("비밀번호가 똑같지 않아요!");
      setPasswordConfirmValid(false);
    } else {
      setPasswordConfirmMessage("일치");
      setPasswordConfirmValid(true);
    }
  };
  const outside = useRef();
  // 중복 이메일 검사

  return (
    <>
      <div className="contentWrap">
        <div className="siginup_inputTitle">이메일 주소</div>
        <div className="siginup_inputWrap">
          <input
            className="siginup_input"
            type="text"
            value={email}
            onChange={handleEmail}
            placeholder="ssafy123@naver.com"
          />
          {emailValid ? (
            <div className="error_icon">
              <AiOutlineCheckCircle color="orange" />
            </div>
          ) : (
            <div className="error_icon">
              <AiOutlineCloseCircle color="#dadada" />
            </div>
          )}
        </div>
        {/* <div className="siginup_errorMessageWrap">{emailMessage}</div> */}

        <div className="siginup_inputTitle">비밀번호</div>
        <div className="siginup_inputWrap">
          <input
            className="siginup_input"
            type="password"
            value={password}
            onChange={handlePw}
            placeholder="숫자,영문자,특수문자 8자리 이상"
          />
          {passwordValid ? (
            <div className="error_icon">
              <AiOutlineCheckCircle color="orange" />
            </div>
          ) : (
            <div className="error_icon">
              <AiOutlineCloseCircle color="#dadada" />
            </div>
          )}
        </div>
        {/* <div className="siginup_errorMessageWrap">{passwordMessage}</div> */}

        <div className="siginup_inputTitle">비밀번호 확인</div>
        <div className="siginup_inputWrap">
          <input
            className="siginup_input"
            type="password"
            value={passwordConfirm}
            onChange={handlePasswordConfirm}
          />
          {passwordConfirmValid ? (
            <div className="error_icon">
              <AiOutlineCheckCircle color="orange" />
            </div>
          ) : (
            <div className="error_icon">
              <AiOutlineCloseCircle color="#dadada" />
            </div>
          )}
        </div>
        {/* <div className="siginup_errorMessageWrap">{passwordConfirmMessage}</div> */}
      </div>

      <button
        onClick={register}
        disabled={notAllow}
        className="siginup_bottomButton"
      >
        가입하기
      </button>
    </>
  );
}
