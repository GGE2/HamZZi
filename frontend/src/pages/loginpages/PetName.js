import React from "react";
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router";
import "../../styles/Modal.css";
import api from './../../components/api';
import { motion } from "framer-motion";

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

  const variants = {
    hidden: { opacity: 0 },
    visibie: { opacity: 1 },
  };

  return (
    <>
     <motion.div
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      exit={{ y: -100, opacity: 0 }}
      transition={{ duration: 0.5 }}
      variants={variants}
    >
      <div className="Modal">
        <div className="modalbody">
          <h1>햄스터 이름</h1>
          <h2>당신의 햄스터의 이름을 정해주세요</h2>
          {/* <p>햄스터 이름은 언제든지 수정할 수 있습니다</p> */}
          <form onSubmit={handleSubmit}>
          <div className="nickinputWrap">
            <input
            className="input"
              name="nickname"
              placeholder="햄스터 이름 입력"
              onChange={handleChange}
              // value={text}
            />
             </div>
             <div className="setnickimg" type="submit" onClick={handleSubmit}>    <img src="guildlist/createbtn.png" alt="" /></div>
         
            {/* <button type="submit"> 설정하기</button> */}
          </form>
        </div>
      </div>

      </motion.div>
    </>
  );
};

export default PetName;
