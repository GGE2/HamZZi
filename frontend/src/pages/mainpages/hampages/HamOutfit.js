import React, { useState } from "react";
// import {  } from 'react';
import { GrAdd } from "react-icons/gr";
import HamModal from './HamModal';
// import SetNickName from './../../loginpages/SetNickName';
import '../../../styles/Modal.css'

// GrAdd
const HamOutfit = () => {
  const [isCreate, setIsCreate] = useState(false);
  const [isModal, setIsModal] = useState(false);


  // 캐릭터 생성 창을 누르면 모달창을 띄워서 펫 이름을 받는다.
  // 모달창 노출
  const onclickCreatePet = () => {
    // setIsCreate();
    setIsModal(true)
  };

  


  return (
    <>
    {/* 모달창 띄우기 */}
    {isModal && <HamModal setIsModal={setIsModal} setIsCreate={setIsCreate} />}
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
          <img src="animal-3629_128.gif" alt="" />
        </div>
      )}
    </>
  );
};

export default HamOutfit;
