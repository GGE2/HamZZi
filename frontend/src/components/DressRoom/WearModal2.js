import React, { useRef, useEffect, useState } from "react";

const WearModal2 = ({ setIsModal3, id, onWearItem, setShow, type }) => {
  const onCloseModal = () => {
    setIsModal3(false);
  };

  const onWear = () => {
    onWearItem(id);
  };

  return (
    <div className="WearmodalBody">
      <div className="WearTitle">
      {type === "모자" ? <img src={`chara/hat/hat${id}.png`} alt="" /> : null}
        {type === "옷" ?  <img src={`chara/cloth/cloth${id}.png`} alt="" /> : null}
        {type === "배경" ?   <img src={`chara/deco/deco${id}.png`} alt="" /> : null}
      </div>
      바로 장착하시겠습까?
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

export default WearModal2;
