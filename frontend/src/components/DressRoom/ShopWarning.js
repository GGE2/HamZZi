import React, { useRef, useEffect, useState } from "react";


const ShopWarning = ({ setIsModal4, id, setShow, type }) => {
  const onCloseModal = () => {
    setIsModal4(false);
  };
 
  

  return (
    <div className="WearmodalBody">
      <div className="WearTitle">
      {type === "모자" ? <img src={`chara/hat/hat${id}.png`} alt="" /> : null}
        {type === "옷" ?  <img src={`chara/cloth/cloth${id}.png`} alt="" /> : null}
        {type === "배경" ?   <img src={`chara/deco/deco${id}.png`} alt="" /> : null}
      </div>
      금액이 부족합니다
      <div className="ShopModalBtnList">
        <div className="ShopModalBtn" onClick={onCloseModal}>
          <img src="shop/cancelbtn.png" alt="" />
        </div>
      </div>
    </div>
  );
};

export default ShopWarning;
