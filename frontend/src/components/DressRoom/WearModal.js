import React, { useRef, useEffect, useState } from "react";

const WearModal = ({ setIsModal, pid, onWearItem, setShow }) => {
  const onCloseModal = () => {
    setIsModal(false);
  };

  const onWear = () => {
    onWearItem(pid,setShow);
    // setShow({
    //   hatShow: false,
    //   clothShow: false,
    //   decoShow: false,
    //   myShow: true,
    // });
  };

  return (
    <div className="WearmodalBody">
      <div className="WearTitle">
        <img src={`hamzzibody/hamzzi${pid + 1}.png`} alt="" />
      </div>

      <div className="ShopModalBtnList">
        <div className="ShopModalBtn" onClick={onWear}>
          <img src="shop/wearbtn.png" alt="" />
        </div>
        <div className="ShopModalBtn" onClick={onCloseModal}>
          <img src="shop/cancelbtn.png" alt="" />
        </div>
      </div>
    </div>
  );
};

export default WearModal;
