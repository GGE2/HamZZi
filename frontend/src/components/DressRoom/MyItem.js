import React, { useState, useRef } from "react";
import { motion } from "framer-motion";
import WearModal from "./WearModal";

const MyItem = ({ id }) => {
  // console.log(imgNums[0])
  // const [isclick, setIsclick] = useState(false);

  const [isModal, setIsModal] = useState(false);

  const outside = useRef();
  const onClick = () => {
    setIsModal(true);
  };

  return (
    <>
      {isModal && (
        <div
          className="WearModal"
          ref={outside}
          onClick={(e) => {
            if (e.target === outside.current) setIsModal(false);
          }}
        >
          <WearModal setIsModal={setIsModal} id={id} />
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
