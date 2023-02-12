import React, { useRef, useEffect, useState } from "react";




const ShopModal = ({  setIsModal, onBuyItem, id, cost}) => {
  const [petName, setPetName] = useState("");
  const nickname = localStorage.getItem("nickname");

  
  const onCloseModal = () => {
    setIsModal(false)
  }





  return (
    <div className="ShopmodalBody">
      <div className="ShopTitle"><img src={`hamzzibody/hamzzi${id + 1}.png`} alt="" /></div>
      
      <div className="Cost">
            <img src={`coin.png`} alt="" />
            {cost}
          </div>
      <div>정말 구매 할건가요? <button onClick={() => onBuyItem(id)}>구매</button></div>
      {/* <div>안녕하세용</div> */}
      <div id="ShopmodalCloseBtn" onClick={onCloseModal}>x</div>
    </div>
  );

};

export default ShopModal;
