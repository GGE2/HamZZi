import React, { useState, useRef } from "react";
import { motion } from "framer-motion";
import WearModal from "./WearModal";
import { BiLockAlt } from "react-icons/bi";

const MyItem = ({ id,onWearItem, setShow, type, Wear, petLevel, itemlevel }) => {
  // console.log(imgNums[0])
  // const [isclick, setIsclick] = useState(false);
  const [isMyModal, setIsMyModal] = useState(false);
  // console.log(123123123123)
  // console.log(petLevel)
  // console.log(itemlevel)
  // console.log(myItems)
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

      {/* {petLevel < itemlevel ? :} */}
      {type === "모자" && Wear.hat === id && petLevel >= itemlevel ? (
        <>
          <div className="SameItem">
            <motion.div className="DressItem_clicked">
              <img src={`chara/hat/hat${id}.png`} alt="" />
            </motion.div>
            착용 중
          </div>
        </>
      ) : type === "모자" && petLevel >= itemlevel ? (
        <div className="shopitem">
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
        </div>
      ) : type === "옷" && Wear.dress === id && petLevel >= itemlevel ? (
        <>
          <div className="SameItem">
            <motion.div className="DressItem_clicked">
              <img src={`chara/cloth/cloth${id}.png`} alt="" />
            </motion.div>
            착용 중
          </div>
        </>
      ) : type === "옷" && petLevel >= itemlevel ? (
        <div className="shopitem">
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
        </div>
      ) : type === "배경" && Wear.background === id && petLevel >= itemlevel ? (
        <>
          <div className="SameItem">
            <motion.div className="DressItem_clicked">
              <img src={`chara/deco/decco${id}.png`} alt="" />
            </motion.div>
            착용 중
          </div>
        </>
      ) : type === "배경" && petLevel >= itemlevel ? (
        <div className="shopitem">
          <motion.div
            whileHover={{
              scale: 1.03,
              transition: { duration: 0.2 },
            }}
            className="DressItem_Unclicked"
            onClick={onClick}
          >
            <img src={`chara/deco/decco${id}.png`} alt="" />
          </motion.div>
          착용 가능
        </div>)
        : //////////////////////////////////////
         type === "모자" ? (
          <div className="SameItem">
              <motion.div className="DressItem_clicked">
                <img src={`chara/hat/hat${id}.png`} alt="" />
              </motion.div>
              <BiLockAlt />
            </div>
        ) : type === "옷"  ? (
          <>
            <div className="SameItem">
              <motion.div className="DressItem_clicked">
                <img src={`chara/cloth/cloth${id}.png`} alt="" />
              </motion.div>
              <BiLockAlt />
            </div>
          </>
        ) : type === "배경"  ? (
          <div className="SameItem">
             <motion.div className="DressItem_clicked">
                <img src={`chara/deco/decco${id}.png`} alt="" />
              </motion.div>
              <BiLockAlt />
          </div>
        ) : null
      }
    </>
  );
};

export default MyItem;
