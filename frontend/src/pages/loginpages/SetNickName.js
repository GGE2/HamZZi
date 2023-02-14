import React, { useState } from "react";
import { useNavigate } from "react-router";
import axios from "axios";
import "../../styles/Modal.css";
import api from "./../../components/api";
import "../../styles/LoginForm.css";
import { motion } from "framer-motion";

const SetNickName = () => {
  const [nickName, SetNickName] = useState("");
  const [text, setText] = useState("");
  const [modal, setModal] = useState(true);
  const navigate = useNavigate();
  const email = JSON.parse(localStorage.getItem("user"));
  // console.log(typeof nickName, nickName);
  const handleChange = (e) => {
    setText(e.target.value);
    SetNickName(e.target.value);
  };

  const closeModal = () => {
    setModal(false);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    api
      // 요청 주소 수정 예정
      .put(`/api/user/nickname?nickname=${nickName}&email=${email}`)
      .then((res) => {
        // console.log(res);
        localStorage.setItem("nickname", nickName);
        api
          .post(`/api/quest/user/${nickName}`)
          .then((res) => {
            // console.log("퀘스트 입니다!!!!!!!!!", res);
            api
              .post(`/api/count?nickname=${nickName}`, {
                nickname: nickName,
              })
              .then(() => {
                navigate("/petName");
              });
          })
          .catch((err) => {
            console.error(err);
          });
      })
      .catch((err) => {
        console.log(err);
        SetNickName("");
        setText("");
        alert("사용할 수 없는 이름입니다!");
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
          <h1>환영합니다!</h1>
          <h2>닉네임을 설정해 주세요</h2>
          <p>*설정하신 닉네임은 다시 바꿀 수 없습니다*</p>
          <form onSubmit={handleSubmit}>
            <div className="nickinputWrap">
              <input
                className="input"
                name="nickname"
                placeholder="닉네임입력"
                onChange={handleChange}
                value={text}
              />
            </div>
            <div className="setnickimg" type="submit" onClick={handleSubmit}>
              {" "}
              <img src="guildlist/createbtn.png" alt="" />
            </div>

            {/* <button type="submit"> 설정하기</button> */}
          </form>
          {/* <div className="inputWrap">
            <input
              className="input"
              type="text"
              placeholder="email"
              placeholder="test@gmail.com"
              value={email}
              onChange={handleEmail}
            />
          </div> */}
        </div>
      </div>
      </motion.div>
    </>
  );
};

export default SetNickName;
