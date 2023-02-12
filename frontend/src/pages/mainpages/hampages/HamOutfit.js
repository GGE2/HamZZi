import React, { useState, useRef, useEffect } from "react";
// import {  } from 'react';
import { GrAdd } from "react-icons/gr";
import HamModal from "./HamModal";
// import SetNickName from './../../loginpages/SetNickName';
import "../../../styles/Modal.css";
import axios from "axios";
import html2canvas from "html2canvas";
import api from "./../../../components/api";

// GrAdd
const HamOutfit = () => {
  const nickname = localStorage.getItem("nickname");
  const [isCreate, setIsCreate] = useState(false);
  const [isModal, setIsModal] = useState(false);

  const outside = useRef();
  // 캐릭터 생성 창을 누르면 모달창을 띄워서 펫 이름을 받는다.
  // 모달창 노출
  const onclickCreatePet = () => {
    // setIsCreate();
    setIsModal(true);
  };
  useEffect(() => {
    api.get(`/api/pet/${nickname}`).then((res) => {
      setIsCreate(res.data.pet);
    });
    console.log(isCreate);
  }, []);

  const onCapture = () => {
    console.log("onCapture");
    html2canvas(document.getElementById("div")).then((canvas) => {
      onSaveAs(canvas.toDataURL("image/png"), "image-download.png", 0.1);
    });
  };

  const onSaveAs = (uri, filename) => {
    console.log("onSaveAs");
    var link = document.createElement("a");
    document.body.appendChild(link);
    link.href = uri;
    link.download = filename;
    link.click();
    document.body.removeChild(link);
  };

  return (
    <>
      {/* 모달창 띄우기 */}
      {isModal && (
        <div
          className="Modal"
          ref={outside}
          onClick={(e) => {
            if (e.target === outside.current) setIsModal(false);
          }}
        >
          <HamModal setIsModal={setIsModal} setIsCreate={setIsCreate} />
        </div>
      )}
      {/* 펫이 없으면 만드는 추가 */}
      {!isCreate && (
        <div className="HamOutfit">
          <div className="PetCreate" onClick={onclickCreatePet}>
            <GrAdd size={"50%"} />
          </div>
          {/* <div className="HamBackGround">
            배경</div> */}
          {/* <div className="HamBody">본체</div>
          <div className="HamCloths">옷</div> */}
        </div>
      )}
      {/* 펫이 있으면 펫 출력 */}
      {isCreate && (
        <div className="HamOutfit" id="div">
          {/* <img className='img3' src="hamzzi.png" alt="" /> */}
          <img src="tennis.png" alt="" />
          <button className="test" onClick={onCapture}>
            click
          </button>
        </div>
      )}
    </>
  );
};

export default HamOutfit;
