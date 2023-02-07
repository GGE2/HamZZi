import React, { useRef, useEffect, useState } from "react";
import { useNavigate } from "react-router";

const HamModal = ({ setIsModal, setIsCreate }) => {
  const petnameInput = useRef();
  const [petName, SetPetName] = useState("");
  const navigate = useNavigate();
  const email = JSON.parse(localStorage.getItem("user"));
  console.log(typeof petName, petName);
  const handleChange = (e) => {
    SetPetName(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (petName.length < 1) {
      petnameInput.current.focus();
      return;
    }
    setIsModal(false);
  };

  const handleClose = () => {
    console.log("닫는다");
    setIsModal(false);
    // console.log()
  };

  return (
    <>
      <div
        className="Modal"
        onClick={() => {
          setIsModal(false);
        }}
      ></div>
      <div className="modalBody">
        <h2>새로운 펫이름을 설정해주세요</h2>
        <p>설정하신 펫이름은 다시 바꿀 수 없습니다</p>
        <form onSubmit={handleSubmit}>
          <input
            name="nickname"
            placeholder="펫이름입력"
            onChange={handleChange}
            ref={petnameInput}
          />
          <button type="submit"> 설정하기</button>
        </form>
        <button onClick={handleClose}>취소</button>
      </div>
    </>
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

export default HamModal;
