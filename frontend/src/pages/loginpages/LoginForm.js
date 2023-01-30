import { signInWithEmailAndPassword } from "firebase/auth";
import { auth } from "../../Firebase";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { setCredentials } from "../../authSlice";
import axios from "axios";
import "../../styles/LoginForm.css";

// import Google from "./features/auth/Google";
// import Kakaopop from "./features/auth/Kakaopop";
// import LoginForm from './LoginForm';

export default function LoginForm() {
  const [email, setEmail] = useState("");
  const [pw, setPw] = useState("");

  const [emailValid, setEmailValid] = useState(false);
  const [pwValid, setPwValid] = useState(false);
  const [notAllow, setNotAllow] = useState(true);

  const [loginflag, setloginflag] = useState(true);

  const dispatch = useDispatch();
  const navigate = useNavigate();

  // 로그인
  const login = async () => {
    try {
      // 파이어베이스 인증 받아오기
      const curUserInfo = await signInWithEmailAndPassword(auth, email, pw);
      console.log(curUserInfo);

      // cookie 저장으로 바꾸기
      localStorage.setItem("user", JSON.stringify(curUserInfo.user.email));
      localStorage.setItem(
        "accessToken",
        JSON.stringify(curUserInfo.user.accessToken)
      );
      dispatch(
        setCredentials({
          user: curUserInfo.user.displayName,
          accessToken: curUserInfo.user.accessToken,
        })
      );

      navigate("/main"); // 로그인하면 메인 페이지로 이동~
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    if (emailValid && pwValid) {
      setNotAllow(false);
      return;
    }
    setNotAllow(true);
  }, [emailValid, pwValid]);

  const handleEmail = (e) => {
    setEmail(e.target.value);
    const regex =
      /^(([^<>()\[\].,;:\s@"]+(\.[^<>()\[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i;
    if (regex.test(e.target.value)) {
      setEmailValid(true);
    } else {
      setEmailValid(false);
    }
  };
  const handlePw = (e) => {
    setPw(e.target.value);
    const regex =
      /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+])(?!.*[^a-zA-z0-9$`~!@$!%*#^?&\\(\\)\-_=+]).{8,20}$/;
    if (regex.test(e.target.value)) {
      setPwValid(true);
    } else {
      setPwValid(false);
    }
  };
  // const onClickConfirmButton = () => {
  //   if (email === User.email && pw === User.pw) {
  //     alert("로그인에 성공했습니다.");
  //   } else {
  //     alert("등록되지 않은 회원입니다.");
  //   }
  // };

  return (
    <>
      <>
        <div className="contentWrap">
          {/* <div className="inputTitle">e메일</div> */}
          <div className="inputWrap">
            <input
              className="input"
              type="text"
              placeholder="email"
              // placeholder="test@gmail.com"
              value={email}
              onChange={handleEmail}
            />
          </div>

          {/* <div className="inputTitle">비밀번호</div> */}
          <div className="inputWrap">
            <input
              className="input"
              type="password"
              placeholder="password"
              // placeholder="영문, 숫자, 특수문자 포함 8자 이상"
              value={pw}
              onChange={handlePw}
            />
          </div>
        </div>

        {/* <div className="login_forgot">
          <div>
            <input type="checkbox" />
            <a href="">Remember me</a>
            <a href="">비번 찾기</a>
          </div>
        </div> */}

        {/* <div style={{padding:"5px"}}> */}
        <button onClick={login} disabled={notAllow} className="bottomButton">
          로그인
        </button>
        {/* </div> */}
      </>
    </>
  );
}
