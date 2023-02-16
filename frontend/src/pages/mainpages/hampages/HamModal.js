import React, { useRef, useEffect, useState } from "react";
import { useNavigate } from "react-router";

import GetPetInfo from "./../../../components/GetPetInfo";
import api from "./../../../components/api";
import Warning from './../../../components/Warning';

const HamModal = ({ setIsModal, setIsCreate }) => {
  const [petName, setPetName] = useState("");
  const nickname = localStorage.getItem("nickname");
  const [isModal2, setIsModal2] = useState(false);

  const handleChange = (e) => {
    setPetName(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (petName.length < 2 || petName.length > 8) {
      setIsModal2(true);
      console.log(isModal2);
      // alert('2글자 이상 8글자 미만으로 입력해주세요.')
      setPetName("");
      // setText("");
      return;
    }
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
  const outside = useRef();
  return (
    <>
    {isModal2 && (
        <div
          className="warning"
          ref={outside}
          onClick={(e) => {
            if (e.target === outside.current) setIsModal2(false);
          }}
        >
          <Warning
            setIsModal={setIsModal2}
            content={"잘못된 형식입니다"}
            content2={"다시 한번 확인해주세요"}
          />
        </div>
      )}
    <div className="modalbody">
      <h1>햄스터 이름</h1>
      <h2>당신의 햄스터의 이름을 정해주세요</h2>
      {/* <p>햄스터 이름은 언제든지 수정할 수 있습니다</p> */}
      <form onSubmit={handleSubmit}>
        <div className="nickinputWrap">
          <input
            className="input"
            name="nickname"
            placeholder="2글자 이상 8글자 미만 햄스터 이름을 입력하세요"
            onChange={handleChange}
          />
        </div>
        <div className="setnickimg" type="submit" onClick={handleSubmit}>
          {" "}
          <img src="guildlist/createbtn.png" alt="" />
        </div>
      </form>
    </div>
    </>
  );
  

};

export default HamModal;
