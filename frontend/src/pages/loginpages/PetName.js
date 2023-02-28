import React from "react";
import { useState, useRef, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router";
import "../../styles/Modal.css";
import api from "./../../components/api";
import { motion } from "framer-motion";
import Warning from "./../../components/Warning";

const PetName = () => {
  const [petName, setPetName] = useState("");
  const navigate = useNavigate();
  const nickname = localStorage.getItem("nickname");
  // console.log(nickname);
  const [isModal, setIsModal] = useState(false);
  const [isModal2, setIsModal2] = useState(false);
  const text2 = useRef();
  useEffect(() => {
    text2.current.focus();
  }, []);

  const handleChange = (e) => {
    setPetName(e.target.value);
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    if (petName.length < 2 || petName.length > 8) {
      setIsModal(true);
      // console.log(isModal);
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
        localStorage.setItem("petName", petName);
        navigate("/main");
      })
      .catch((error) => {
        console.log(error);
        setIsModal2(true);
      });
  };

  const variants = {
    hidden: { opacity: 0 },
    visibie: { opacity: 1 },
  };
  const outside = useRef();
  return (
    <>
      {isModal && (
        <div
          className="warning"
          ref={outside}
          onClick={(e) => {
            if (e.target === outside.current) setIsModal(false);
          }}
        >
          <Warning
            setIsModal={setIsModal}
            content={"잘못된 형식입니다"}
            content2={"다시 한번 확인해주세요"}
          />
        </div>
      )}

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
            content={"이미 존재하는 펫이름입니다"}
            content2={"다시 입력해주세요"}
          />
        </div>
      )}
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
            <form onSubmit={handleSubmit}>
              <div className="nickinputWrap">
                <input
                  className="input"
                  name="nickname"
                  placeholder="2글자 이상 8글자 미만 햄스터 이름을 입력하세요"
                  onChange={handleChange}
                  // value={text}
                  ref={text2}
                />
              </div>
              <div className="setnickimg" type="submit">
                {" "}
                <img
                  src="guildlist/createbtn.png"
                  onClick={handleSubmit}
                  alt=""
                />
              </div>

              {/* <button type="submit"> 설정하기</button> */}
            </form>
          </div>
        </div>
      </motion.div>
    </>
  );
};

export default PetName;
