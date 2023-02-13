import React, { useState, useRef } from "react";
import { motion } from "framer-motion";
import WearModal from "./WearModal";

const MyItem = ({ id, onWearItem, setShow, type,Wear }) => {
  // console.log(imgNums[0])
  // const [isclick, setIsclick] = useState(false);
  const [isMyModal, setIsMyModal] = useState(false);

  const outside = useRef();
  const onClick = () => {
    setIsMyModal(true);
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
          <WearModal
            type={type}
            setShow={setShow}
            setIsModal={setIsMyModal}
            id={id}
            onWearItem={onWearItem}
          />
        </div>
      )}


        {/* <div className="SameItem">
          <motion.div
            className="DressItem_clicked"
            onClick={onClick}
          >
            <img src={`chara/cloth/cloth${id}.png`} alt="" />
            장착중
          </motion.div>
        </div> */}


        {type === "모자" && Wear.hat === id ? (
            <>
            <div className="SameItem">
          <motion.div
            className="DressItem_clicked"
            onClick={onClick}
          >
            <img src={`chara/hat/hat${id}.png`} alt="" />
          </motion.div>
            착용 중
          
        </div>
            </>
          ) : (type === "모자") ? <div className="shopitem">
          <motion.div
            whileHover={{
              scale: 1.03,
              transition: { duration: 0.2 },
            }}
            className="DressItem_Unclicked"
            onClick={onClick}
          >
            <img src={`chara/hat/hat${id}.png`} alt="" />
          </motion.div>
          착용 가능
        </div> : 
          (type === "옷" && Wear.dress) ? 
          <>
            <div className="SameItem">
          <motion.div
            className="DressItem_clicked"
            onClick={onClick}
          >
            <img src={`chara/cloth/cloth${id}.png`} alt="" />
          </motion.div>
          착용 중
        </div>
            </> : (type === '옷') ?<div className="shopitem">
        <motion.div
          whileHover={{
            scale: 1.03,
            transition: { duration: 0.2 },
          }}
          className="DressItem_Unclicked"
          onClick={onClick}
        >
          <img src={`chara/cloth/cloth${id}.png`} alt="" />
        </motion.div>
        착용 가능
      </div> :
            (type === "배경" && Wear.background === id) ?  <>
         <div className="SameItem">
          <motion.div
            className="DressItem_clicked"
            onClick={onClick}
          >
            <img src={`chara/cloth/cloth${id}.png`} alt="" />
          </motion.div>
          착용 중
        </div>
            </> :  <div className="shopitem">
        <motion.div
          whileHover={{
            scale: 1.03,
            transition: { duration: 0.2 },
          }}
          className="DressItem_Unclicked"
          onClick={onClick}
        >
          <img src={`chara/cloth/cloth${id}.png`} alt="" />
        </motion.div>
        착용 가능
      </div>}

      {/* <div className="shopItem">
        <motion.div
          whileHover={{
            scale: 1.03,
            transition: { duration: 0.2 },
          }}
          className="DressItem_Unclicked"
          onClick={onClick}
        >
          <img src={`chara/cloth/cloth${id}.png`} alt="" />
        </motion.div>
      </div> */}
    </>
  );
};

export default MyItem;
