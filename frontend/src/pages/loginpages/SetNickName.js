import React, { useState, useRef, useEffect } from "react";
import { useNavigate } from "react-router";
import axios from "axios";
import "../../styles/Modal.css";
import api from "./../../components/api";
import "../../styles/LoginForm.css";
import { motion } from "framer-motion";
import LoadingModal from "./../../components/LoadingModal";
import LoadingModal2 from "./../../components/LoadingModal2";
import ShopModal from "./../../components/DressRoom/ShopModal";
import Warning from "../../components/Warning";

const SetNickName = () => {
  const [nickName, SetNickName] = useState("");
  const [text, setText] = useState("");
  const [modal, setModal] = useState(true);
  const [isModal, setIsModal] = useState(false);
  const [isModal2, setIsModal2] = useState(false);
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
    if (text.length < 2 || text.length > 8) {
      setIsModal(true);
      // console.log(isModal)
      // alert('2글자 이상 8글자 미만으로 입력해주세요.')
      SetNickName("");
      setText("");
      return;
    }
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
        setIsModal2(true);
        // alert("사용할 수 없는 이름입니다!");
      });
  };

  const variants = {
    hidden: { opacity: 0 },
    visibie: { opacity: 1 },
  };

  const outside = useRef();
  const text2 = useRef();

  useEffect(() => {
    text2.current.focus();
  }, []);

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
            content={"이미 존재하는 닉네임입니다"}
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
            <h1>환영합니다!</h1>
            <h2>닉네임을 설정해 주세요</h2>
            <p>햄최몇에서 사용될 당신의 이름입니다</p>
            <form onSubmit={handleSubmit}>
              <div className="nickinputWrap">
                <input
                  className="input"
                  name="nickname"
                  placeholder="2글자 이상 8글자 미만 닉네임을 입력하세요"
                  onChange={handleChange}
                  value={text}
                  ref={text2}
                />
              </div>
              <div className="setnickimg">
                {" "}
                <img
                  src="guildlist/createbtn.png"
                  type="submit"
                  onClick={handleSubmit}
                  alt=""
                />
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
