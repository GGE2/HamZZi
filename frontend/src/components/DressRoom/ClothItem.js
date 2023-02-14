import React, { useState, useRef } from "react";
import { motion } from "framer-motion";
import ShopModal from "./ShopModal";

import { BiLockAlt } from "react-icons/bi";
import WearModal from "./WearModal";

const ClothItem = ({
  id,
  cost,
  onBuyItem,
  myItems,
  type,
  petLevel,
  itemlevel,
  onWearItem,
}) => {
  const [isModal, setIsModal] = useState(false);
  const [isModal2, setIsModal2] = useState(false);

  const outside = useRef();
  const onClick = () => {
    setIsModal(true);
  };

  const saveid = id;

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
            onBuyItem={onBuyItem}
            id={saveid}
            type={type}
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
            setIsModal={isModal2}
            id={id}
            onWearItem={onWearItem}
          />
        </div>
      )}

      {myItems.some((item) => item.item.item_id === id) ? (
        // 구매한 아이템
        <div className="SameItem">
          <motion.div className="DressItem_clicked">
            <img src={`chara/cloth/cloth${id}.png`} alt="" />
          </motion.div>
          보유중
        </div>
      ) : (
        <>
          {petLevel < itemlevel ? (
            <div className="SameItem">
              <motion.div
                // className="DressItem"
                className="DressItem_clicked"
                // onClick={onClick}
              >
                <img src={`chara/cloth/cloth${id}.png`} alt="" />
              </motion.div>
              <BiLockAlt />
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
                <img src={`chara/cloth/cloth${id}.png`} alt="" />
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

export default ClothItem;