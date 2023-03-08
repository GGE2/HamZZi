import React, { useRef, useEffect, useState } from "react";
import { useNavigate } from "react-router";
import '../../src/styles/Modal.css'




const LoadingModal2 = ({ setIsModal, setIsCreate }) => {
  const [petName, setPetName] = useState("");
  const nickname = localStorage.getItem("nickname");

  const handleChange = (e) => {
    setPetName(e.target.value);
  };



  return (
    <div className="Modal2">
    {/* <div className="modalbody"> */}
     <div className="spinner-container">
      
      <div className="loading-spinner"><img src="spin.gif" alt="" /></div>
    </div>
    {/* </div> */}
    </div>
  );

};

export default LoadingModal2;
