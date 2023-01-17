import React, { useEffect, useState } from "react";

const User = {
  email: "test@example.com",
  pw: "test2323@@@",
};

export default function Signup() {
  // 회원가입 데이터
  const [email, setEmail] = useState(""); // 이메일
  const [pw, setPw] = useState(""); // 비밀번호
  const [nickname, setNickname] = useState(""); // 닉네임
  const [name, setName] = useState(""); // 이름
  const [gender, setGender] = useState(""); // 성별
  const [phone, setPhone] = useState(""); // 전화번호

  // 검사
  const [emailValid, setEmailValid] = useState(false); // 이메일 유효성 검사
  const [pwValid, setPwValid] = useState(false); // 비밀번호 유효성 검사
  const [NickValid, setNickValid] = useState(false); // 닉네임 유효성 검사
  const [phoneValid, setPhoneValid] = useState(false); // 전화번호 유효성 검사
  const [notAllow, setNotAllow] = useState(true); // 회원가입 검사
  const [emailCheck, setEmailCheck] = useState(false); // 중복 이메일 검사
  const [pwCheck, setPwCheck] = useState(false); // 비밀번호, 비밀번호 확인 일치하는가

  useEffect(() => {
    if (emailValid && pwValid) {
      setNotAllow(false);
      return;
    }
    setNotAllow(true);
  }, [emailValid, pwValid]);

  // 이메일 유효성 검사
  const handleEmail = (e) => {
    setEmail(e.target.value);
    console.log(email);
    const regex =
      /^(([^<>()\[\].,;:\s@"]+(\.[^<>()\[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i;
    if (regex.test(e.target.value)) {
      setEmailValid(true);
    } else {
      setEmailValid(false);
    }
  };

  // 비밀번호 유효성 검사
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

  // 닉네임 유효성 검사
  const handleNickname = (e) => {
    setNickname(e.target.value);
    console.log(nickname);
    const regex = /^[가-힣|a-z|A-Z|0-9|].{1,}$/;
    if (regex.test(e.target.value)) {
      setNickValid(true);
    } else {
      setNickValid(false);
    }
  };

  // 로그인 일치
  const onClickConfirmButton = () => {
    if (email === User.email && pw === User.pw) {
      alert("로그인에 성공했습니다.");
    } else {
      alert("등록되지 않은 회원입니다.");
    }
  };

  return (
    <div style={{ margin: "auto" }}>
      <div className="signuppage">
        <div className="titleWrap">HAMZZI</div>

        <div className="contentWrap">
          <div className="inputTitle">이메일 주소</div>
          <div className="inputWrap">
            <input
              className="input"
              type="text"
              value={email}
              onChange={handleEmail}
            />
          </div>
          <div className="errorMessageWrap">
            {!emailValid && email.length > 0 && (
              <div>올바른 이메일을 입력해주세요.</div>
            )}
          </div>

          <div style={{ marginTop: "26px" }} className="inputTitle">
            비밀번호
          </div>
          <div className="inputWrap">
            <input
              className="input"
              type="password"
              value={pw}
              onChange={handlePw}
            />
          </div>
          <div className="errorMessageWrap">
            {!pwValid && pw.length > 0 && (
              <div>영문, 숫자, 특수문자 포함 8자 이상 입력해주세요.</div>
            )}
          </div>

          <div>비밀번호 확인</div>
          <div className="inputWrap">
            <input className="input" type="password" />
          </div>

          <div>닉네임</div>
          <div className="inputWrap">
            <input
              className="input"
              type="text"
              value={nickname}
              onChange={handleNickname}
            />
          </div>
          <div className="errorMessageWrap">
            {!NickValid && nickname.length > 0 && (
              <div>한글, 영어, 숫자 포함 2자 이상 입력해주세요.</div>
            )}
          </div>

          <div>이름</div>
          <div className="inputWrap">
            <input className="input" type="password" placeholder="" />
          </div>

          <div>성별</div>
          <select className="pick_gender">
            <option value="">성별 선택</option>
            <option value="남">남</option>
            <option value="여">여</option>
            <option value="제3의성">선택 안함</option>
          </select>

          <div>전화번호</div>
          <div className="inputWrap">
            <input
              className="input"
              type="password"
              placeholder="전화번호 입력"
            />
          </div>
        </div>

        {/* <div className="socialLogin">
        <button>
          <img className="loginBtn" src="btn_login_kakao.png" alt="" />
        </button>
        <button>
          <img className="loginBtn" src="btn_login_google.png" alt="" />
        </button>
      </div> */}

        <div>
          <button
            onClick={onClickConfirmButton}
            disabled={notAllow}
            className="bottomButton"
          >
            확인
          </button>
        </div>
      </div>
    </div>
  );
}
