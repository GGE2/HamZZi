import React, { useRef, useEffect, useState } from "react";
import { useNavigate } from "react-router";
import axios from "axios";
import { BiDotsVerticalRounded } from "react-icons/bi";

const QuestModal = ({ content, point, setIsModal, setIsCreate }) => {
  const [petName, setPetName] = useState("");
  const nickname = localStorage.getItem("nickname");

  const handleChange = (e) => {
    setPetName(e.target.value);
  };

  const onCloseModal = () => {
    setIsModal(false);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // api
    //   .post("/api/pet", {
    //     name: petName,
    //     user_nickname: nickname,
    //   })
    //   .then(() => {

    //     setIsModal(false);
    //     window.location.replace("/main");
    //   })
    //   .catch(() => {
    //     alert("펫 생성에 실패하였습니다");
    //   });
  };

  // const

  return (
    // <img src="coin.png" alt="" />
    <div className="QuestmodalBody">
      {/* <div className="QuestTitle">제목</div> */}
      <div className="QuestTitle">{content}</div>
      <div className="QuestReword">
        보상
        <div className="QuestReword2">
          <img src="coin.png" alt="" />
          <div>x {point}</div>
        </div>
      </div>

      <div id="QuestmodalCloseBtn" onClick={onCloseModal}>
        x
      </div>
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

export default QuestModal;
