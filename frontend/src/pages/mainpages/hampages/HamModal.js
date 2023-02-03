import React, {useRef, useEffect, useState} from "react";
import { useNavigate } from "react-router";

const HamModal = ({setIsModal, setIsCreate}) => {

const [nickName, SetNickName] = useState("");
  const navigate = useNavigate();
  const email = JSON.parse(localStorage.getItem("user"));
  console.log(typeof nickName, nickName);
  const handleChange = (e) => {
    SetNickName(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setIsModal(false)
  };

return (
  <div className="Modal">
    <div className="modalBody">
      <h1>환영합니다!</h1>
      <h2>닉네임을 설정해 주세요</h2>
      <p>설정하신 닉네임은 다시 바꿀 수 없습니다</p>
      <form onSubmit={handleSubmit}>
        <input
          name="nickname"
          placeholder="닉네임입력"
          onChange={handleChange}
        />
        <button type="submit"> 설정하기</button>
      </form>
    </div>
  </div>
)

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
