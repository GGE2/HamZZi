import React, { useRef, useEffect, useState } from "react";
import { useNavigate } from "react-router";
import axios from "axios";
import GetPetInfo from "./../../../components/GetPetInfo";

const HamModal = ({ setIsModal, setIsCreate }) => {
  const [petName, setPetName] = useState("");
  const nickname = localStorage.getItem("nickname");

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
        GetPetInfo();
        setIsModal(false);
        window.location.replace("/main");
      })
      .catch(() => {
        alert("펫 생성에 실패하였습니다");
      });
  };

  return (
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
  );

  // return (
  //   <div className="HamModal">
  //       <input type="text" />
  //     <button className="close" onClick={closeModal}>
  //       X
  //     </button>
  //     <button onClick={CreatePet}>생성</button>
  //     {/* <p>모달창입니다.</p> */}
  //   </div>
  // );
};

export default HamModal;
