import React from "react";
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router";
import "../../styles/Modal.css";

const PetName = () => {
  const [petName, setPetName] = useState("");
  const navigate = useNavigate();
  const nickname = localStorage.getItem("nickname");
  console.log(nickname);

  const handleChange = (e) => {
    setPetName(e.target.value);
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post("http://3.35.88.23:8080/api/pet", {
        name: petName,
        user_nickname: nickname,
      })
      .then(() => {
        navigate("/main");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <>
      <div className="Modal">
        <div className="modalBody">
          <h1>햄스터 이름</h1>
          <h2>당신의 햄스터의 이름을 정해주세요</h2>
          <p>햄스터 이름은 언제든지 수정할 수 있습니다</p>
          <form onSubmit={handleSubmit}>
            <input
              type="text"
              placeholder="햄스터 이름 입력"
              onChange={handleChange}
            />
            <button type="submit">이름 정하기</button>
          </form>
        </div>
      </div>
    </>
  );
};

export default PetName;
