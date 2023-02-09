import React, { useState } from "react";
import "../../../styles/DressRoom.css";
import DressList from "./../../../components/DressRoom/DressList";
import ClothList from "./../../../components/DressRoom/ClothList";
import { motion } from "framer-motion";

const DressRoom = () => {
  const [isCloth, setIsCloth] = useState(true);
  // 버튼 눌림 css
  const [menu, setMenu] = useState([true, false]);

  const onCheckCloth = () => {
    setIsCloth(true);
    setMenu([false, true]);
  };

  const onCheckDress = () => {
    setIsCloth(false);
    setMenu([true, false]);
  };

  return (
    <div className="DressRoom">
      <div className="DressButton">
        <motion.button
          className={menu[0] ? "DressButton_clicked" : "DressButton_Unclicked"}
          onClick={onCheckDress}
        >
          <div className={menu[0] ? "DressButton_clicked_text" : "DressButton_Unclicked_text"}>모자</div>
        </motion.button>
        <motion.button
          className={menu[1] ? "DressButton_clicked" : "DressButton_Unclicked"}
          onClick={onCheckCloth}
        >
          <div className={menu[1] ? "DressButton_clicked_text" : "DressButton_Unclicked_text"}>의류</div>{" "}
        </motion.button>
      </div>
      {isCloth ? <ClothList /> : <DressList />}
    </div>
  );
};

export default DressRoom;
