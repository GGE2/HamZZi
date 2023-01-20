import React, { useEffect, useState } from "react";
import Google from "./features/auth/Google";
import Kakaopop from "./features/auth/Kakaopop";
import Signup from "./features/auth/Signup";

const User = {
  email: "test@example.com",
  pw: "test2323@@@",
};

export default function Login() {
  const [email, setEmail] = useState("");
  const [pw, setPw] = useState("");

  const [emailValid, setEmailValid] = useState(false);
  const [pwValid, setPwValid] = useState(false);
  const [notAllow, setNotAllow] = useState(true);

  const [loginflag, setloginflag] = useState(true);

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
  const onClickConfirmButton = () => {
    if (email === User.email && pw === User.pw) {
      alert("로그인에 성공했습니다.");
    } else {
      alert("등록되지 않은 회원입니다.");
    }
  };

  const onClickSelectLogin = () => {
    setloginflag(true);
  };
  const onClickSelectSignup = () => {
    setloginflag(false);
  };

  return (
    <div className="page">
      <div className="selectlogin">
        <div>
          <button onClick={onClickSelectLogin}>로그인</button>
        </div>
        <div>
          <button onClick={onClickSelectSignup}>회원가입</button>
        </div>
      </div>

      {loginflag ? (
        <div>
          <div className="loginform">
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
              {/* <div className="errorMessageWrap">
          {!emailValid && email.length > 0 && (
            <div>올바른 이메일을 입력해주세요.</div>
          )}
        </div> */}

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
              {/* <div className="errorMessageWrap">
          {!pwValid && pw.length > 0 && (
            <div>영문, 숫자, 특수문자 포함 8자 이상 입력해주세요.</div>
          )}
        </div> */}
            </div>
          </div>

          <div className="login_forgot">
            <div>
              <input type="checkbox" />
              <a href="">Remember me</a>
            </div>
            <a href="">비번 찾기</a>
          </div>
          <div>
            <button
              onClick={onClickConfirmButton}
              disabled={notAllow}
              className="bottomButton"
            >
              로그인
            </button>
          </div>
          <Google />
          <Kakaopop />
        </div>
      ) : (
        <Signup />
      )}
    </div>
  );
}
