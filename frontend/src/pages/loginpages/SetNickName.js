import React, { useState } from "react";
import { useNavigate } from "react-router";
import axios from "axios";

const SetNickName = () => {
  const [nickName, SetNickName] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    SetNickName(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post("http://3.35.88.23:8080/api/user/nickname", {
        nickname: nickName,
      })
      .then((res) => {
        console.log(res);
        navigate("/petName");
      })
      .catch((err) => {
        console.log(err);
        alert("사용할 수 없는 이름입니다!");
      });
  };
  return (
    <div className="modal">
      <h1>환영합니다!</h1>
      <h2>닉네임을 설정해 주세요</h2>
      <form onSubmit={handleSubmit}>
        <input name="nickname" value="nickName" onChange={handleChange} />
        <button type="submit"> 설정하기</button>
      </form>
    </div>
  );
};

export default SetNickName;
