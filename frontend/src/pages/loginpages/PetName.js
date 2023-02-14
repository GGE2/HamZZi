import React from "react";
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router";
import "../../styles/Modal.css";
import api from "./../../components/api";

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
    api
      .post("/api/pet", {
        name: petName,
        user_nickname: nickname,
      })
      .then(() => {
        localStorage.setItem("petName", petName);
        navigate("/main");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <>
      <div className="Modal">
        <div className="modalbody">
          <h1>햄스터 이름</h1>
          <h2>당신의 햄스터의 이름을 정해주세요</h2>
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
