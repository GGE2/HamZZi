import React, { useRef, useEffect, useState } from "react";



const ShopModal = ({setIsModal, id}) => {




  const onCloseModal = () => {
    setIsModal(false)
  }




  return (
    <div className="WearmodalBody">
      <div className="WearTitle"><img src={`hamzzibody/hamzzi${id + 1}.png`} alt="" /></div>
      
      <div>정말 착용 할건가요? <button >착용</button></div>
      {/* <div>안녕하세용</div> */}
      <div id="WearmodalCloseBtn" onClick={onCloseModal}>x</div>
    </div>
  );

};

export default ShopModal;
