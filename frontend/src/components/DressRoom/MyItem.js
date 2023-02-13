import React, { useState, useRef } from "react";
import { motion } from "framer-motion";
import WearModal from "./WearModal";

const MyItem = ({ id,  onWearItem, setShow }) => {
  // console.log(imgNums[0])
  // const [isclick, setIsclick] = useState(false);
  const [isMyModal, setIsMyModal] = useState(false);
  

  const outside = useRef();
  const onClick = () => {
    setIsMyModal(true)
  };

  return (
    <>
      {isMyModal && (
        <div
          className="WearModal"
          ref={outside}
          onClick={(e) => {
            if (e.target === outside.current) setIsMyModal(false);
          }}
        >
          <WearModal setShow={setShow} setIsModal={setIsMyModal} pid={id} onWearItem={onWearItem}/>
        </div>
      )}

      <div className="shopItem">
        <motion.div
          whileHover={{
            scale: 1.03,
            transition: { duration: 0.2 },
          }}
          // className="DressItem"
          className="DressItem_Unclicked"
          onClick={onClick}
        >
      
          <img src={`hamzzibody/hamzzi${id + 1}.png`} alt="" />
        </motion.div>
      </div>
    </>
  );
};

export default MyItem;
