import React, { useState, useRef, useEffect } from "react";
// import {  } from 'react';
import { GrAdd } from "react-icons/gr";
import HamModal from "./HamModal";
// import SetNickName from './../../loginpages/SetNickName';
import "../../../styles/Modal.css";
import html2canvas from "html2canvas";
import api from "./../../../components/api";
import Draggable from "react-draggable";
import { useSelector } from "react-redux";
import { selectCurrentHamLevel } from "../../../hamStatSlice";

// GrAdd
const HamOutfit = ({ Wear, getAllProfile }) => {
  const nickname = localStorage.getItem("nickname");
  console.log(useSelector(selectCurrentHamLevel));
  const level = useSelector(selectCurrentHamLevel);
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

  useEffect(() => {
    // window.location.replace("/main")
  }, [level]);

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

  const [position_back, setPosition_back] = useState({ x: 0, y: 0 }); // box의 포지션 값
  const [position_hat, setPosition_hat] = useState({ x: 0, y: 0 }); // box의 포지션 값
  const [position_body, setPosition_body] = useState({ x: 0, y: 0 }); // box의 포지션 값
  const [position_cloth, setPosition_cloth] = useState({ x: 0, y: 0 }); // box의 포지션 값
  const [Opacity_back, setOpacity_back] = useState(false);
  const [Opacity_hat, setOpacity_hat] = useState(false);
  const [Opacity_body, setOpacity_body] = useState(false);
  const [Opacity_cloth, setOpacity_cloth] = useState(false);

  const handleStart = () => {
    setOpacity_back(true);
  };
  const handleEnd = () => {
    setOpacity_back(false);
  };
  // 업데이트 되는 값을 set 해줌
  const trackPos = (data) => {
    setPosition_back({ x: data.x, y: data.y });
  };
  // console.log(position_back);

  const test = (e, data) => {
    trackPos(data);
    console.log(e);
    console.log(data);

    // if(data.x > 330) {
    //   setPosition({ x: 330, y: data.y })
    // }
    // if (data.y > 330) {
    //   setPosition({ x: data.x, y: 330 })
    // }
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
        </div>
      )}
      {/* 펫이 있으면 펫 출력 */}
      {isCreate && (
        <div className="HamOutfit" id="div">
          {/* <img className='img3' src="hamzzi.png" alt="" /> */}
          {/* <img src="tennis.png" alt="" /> */}

          {/* <div className="HamBackGround">
            <Draggable
              // bounds={{ left: -10, top: -10, right: 655, bottom: 495 }}
              onDrag={test}
              onStart={handleStart}
              onStop={handleEnd}
              // defaultPosition={{ x: 260, y: 150 }}
            >
              <img
                src={`chara/hamzzi/hamzzi${Wear.background + 1}.png`}
                alt=""
              />
            </Draggable>
          </div> */}
          <div className="HamBody">
            {/* <Draggable
              // bounds={{ left: -10, top: -10, right: 655, bottom: 495 }}
              onDrag={test}
              onStart={handleStart}
              onStop={handleEnd}
            > */}
            <img
              src={`wear/${
                level + Wear.hat + Wear.dress + Wear.background
              }.gif`}
              alt=""
            />
            {/* </Draggable> */}
          </div>
          {/* <div className="HamHat">
            <Draggable
              // bounds={{ left: -10, top: -10, right: 655, bottom: 495 }}
              onDrag={test}
              onStart={handleStart}
              onStop={handleEnd}
            >
              <img src={`chara/hat/hat1.png`} alt="" />
            </Draggable>
          </div>
          <div className=""></div>

          <div className="HamCloths">
            <Draggable
              // bounds={{ left: -10, top: -10, right: 655, bottom: 495 }}
              onDrag={test}
              onStart={handleStart}
              onStop={handleEnd}
            >
              <img src={`chara/cloth/cloth1.png`} alt="" />
            </Draggable>
          </div> */}

          {/* 사진 찍기 */}
          {/* <button className="test" onClick={onCapture}>
            click
          </button> */}
        </div>
      )}
    </>
  );
};

export default HamOutfit;
