import React, { useState, useRef } from "react";
import { motion } from "framer-motion";
import ShopModal from "./ShopModal";

import { BiLockAlt } from "react-icons/bi";
import WearModal from "./WearModal";
import WearModal2 from "./WearModal2";
// import { useSelector } from 'react-redux';
// import { selectCurrentHamLevel } from "../../hamStatSlice";

const HatItem = ({
  id,
  cost,
  onBuyItem,
  myItems,
  type,
  petLevel,
  itemlevel,
  onWearItem,
  onGetItemAllList,
  onGetItemUserNickList,
}) => {
  const [isModal, setIsModal] = useState(false);
  const [isModal2, setIsModal2] = useState(false);
  const [isModal3, setIsModal3] = useState(false);
  // const petLevel = useSelector(selectCurrentHamLevel)

  const outside = useRef();
  const onClick = () => {
    setIsModal(true);
  };

  return (
    <>
      {isModal && (
        <div
          className="ShopModal"
          ref={outside}
          onClick={(e) => {
            if (e.target === outside.current) setIsModal(false);
          }}
        >
          <ShopModal
            setIsModal={setIsModal}
            setIsModal2={setIsModal2}
            setIsModal3={setIsModal3}
            onBuyItem={onBuyItem}
            id={id}
            cost={cost}
            type={type}
            onGetItemAllList={onGetItemAllList}
            onGetItemUserNickList={onGetItemUserNickList}
          />
        </div>
      )}

      {isModal2 && (
        <div
          className="WearModal"
          ref={outside}
          onClick={(e) => {
            if (e.target === outside.current) setIsModal2(false);
          }}
        >
          <WearModal
            type={type}
            setIsModal={setIsModal2}
            id={id}
            onWearItem={onWearItem}
          />
        </div>
      )}

      {isModal3 && (
        <div
          className="WearModal"
          ref={outside}
          onClick={(e) => {
            if (e.target === outside.current) setIsModal3(false);
          }}
        >
          <WearModal2
            type={type}
            setIsModal3={setIsModal3}
            id={id}
            onWearItem={onWearItem}
            
          />
        </div>
      )}

      {myItems.some((item) => item.item.item_id === id) ? (
        // 구매한 아이템
        <div className="SameItem">
          <motion.div className="DressItem_clicked">
            <img src={`chara/hat/hat${id}.png`} alt="" />
          </motion.div>
          보유중
        </div>
      ) : (
        <>
          {petLevel < itemlevel ? (
            <div className="SameItem">
              <motion.div className="DressItem_clicked">
                <img src={`chara/hat/hat${id}.png`} alt="" />
              </motion.div>
              <BiLockAlt /> Lv.{itemlevel}
            </div>
          ) : (
            <div className="shopitem">
              <motion.div
                whileHover={{
                  scale: 1.03,
                  transition: { duration: 0.2 },
                }}
                // className="DressItem"
                className="DressItem_Unclicked"
                onClick={onClick}
              >
                <img src={`chara/hat/hat${id}.png`} alt="" />
              </motion.div>
              <div className="Cost">
                <img src={`coin.png`} alt="" />
                {cost} 
              </div>
            </div>
          )}
        </>
      )}
    </>
  );
};

HatItem.defaultProps = {
  myItems: [],
};

export default HatItem;
