import React, { useState, useRef, useEffect } from "react";
// import {  } from 'react';
import { GrAdd } from "react-icons/gr";
import HamModal from "./HamModal";
// import SetNickName from './../../loginpages/SetNickName';
import "../../../styles/Modal.css";
import axios from "axios";

// GrAdd
const HamOutfit = () => {
  const nickname = localStorage.getItem("nickname");
  const [isCreate, setIsCreate] = useState();
  const [isModal, setIsModal] = useState(false);

  const outside = useRef();
  // 캐릭터 생성 창을 누르면 모달창을 띄워서 펫 이름을 받는다.
  // 모달창 노출
  const onclickCreatePet = () => {
    // setIsCreate();
    setIsModal(true);
  };
  useEffect(() => {
    axios.get(`http://3.35.88.23:8080/api/pet/${nickname}`).then((res) => {
      setIsCreate(res.data[0]);
    });
    console.log(isCreate);
  }, []);

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
        <div className="HamOutfit">
          {/* <img className='img3' src="hamzzi.png" alt="" /> */}
          <img src="tennis.png" alt="" />
        </div>
      )}
    </>
  );
};

export default HamOutfit;
