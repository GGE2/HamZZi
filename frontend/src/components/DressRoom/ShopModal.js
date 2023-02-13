import React, { useRef, useEffect, useState } from "react";

const ShopModal = ({ setIsModal, onBuyItem, id, cost, type }) => {
  const onCloseModal = () => {
    setIsModal(false);
  };

  console.log(id);

  return (
    <div className="ShopmodalBody">
      <div className="ShopTitle">
        {type === "모자" ? <img src={`chara/hat/hat${id}.png`} alt="" /> : null}
        {type === "옷" ?  <img src={`chara/cloth/cloth${id}.png`} alt="" /> : null}
        {type === "배경" ?   <img src={`chara/cloth/cloth${id}.png`} alt="" /> : null}
        {/* <img src={`hamzzibody/hamzzi${id + 1}.png`} alt="" /> */}
      </div>

      <div className="Cost">
        <img src={`coin.png`} alt="" />
        {cost}
      </div>
      <div className="ShopModalBtnList">
        <div className="ShopModalBtn" onClick={() => onBuyItem(id)}>
          <img src="shop/buybtn.png" alt="" />
        </div>
        <div className="ShopModalBtn" onClick={onCloseModal}>
          <img src="shop/cancelbtn.png" alt="" />
        </div>
      </div>

      {/* <div>안녕하세용</div> */}
    </div>
  );
};

export default ShopModal;
