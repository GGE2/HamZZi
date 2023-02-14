import React, { useRef, useEffect, useState } from "react";
import { useNavigate } from "react-router";

import GetPetInfo from "./../../../components/GetPetInfo";
import api from "./../../../components/api";

const HamModal = ({ setIsModal, setIsCreate }) => {
  const [petName, setPetName] = useState("");
  const nickname = localStorage.getItem("nickname");

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
        GetPetInfo();
        // setIsModal(false);
        window.location.replace("/main");
      })
      .catch(() => {
        alert("펫 생성에 실패하였습니다");
      });
  };

  return (
    <div className="modalbody">
      <h1>햄스터 이름</h1>
      <h2>당신의 햄스터의 이름을 정해주세요</h2>
      {/* <p>햄스터 이름은 언제든지 수정할 수 있습니다</p> */}
      <form onSubmit={handleSubmit}>
        <div className="nickinputWrap">
          <input
            className="input"
            name="nickname"
            placeholder="햄스터 이름 입력123123"
            onChange={handleChange}
            // value={text}
          />
        </div>
        <div className="setnickimg" type="submit" onClick={()=>handleSubmit}>
          {" "}
          <img src="guildlist/createbtn.png" alt="" />
        </div>

        {/* <button type="submit"> 설정하기</button> */}
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
