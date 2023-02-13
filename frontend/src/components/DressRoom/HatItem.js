import React, { useState, useRef } from "react";
import { motion } from "framer-motion";
import ShopModal from './ShopModal';


const HatItem = ({ id, cost, onBuyItem, myItems, }) => {
 
  const [isModal, setIsModal] = useState(false);

  const outside = useRef();
  const onClick = () => {
    setIsModal(true);
  };


  return (
    <>
      {isModal && <div
          className="ShopModal"
          ref={outside}
          onClick={(e) => {
            if (e.target === outside.current) setIsModal(false);
          }}
        >
          <ShopModal setIsModal={setIsModal} onBuyItem={onBuyItem} id={id} cost={cost}/>
        </div>}

      {myItems.some((item) => item.item.item_id === id) ? (
        // 구매한 아이템
        <div className="SameItem">
          <motion.div
            // whileHover={{
            //   scale: 1.03,
            //   transition: { duration: 0.2 },
            // }}
            // className="DressItem"
            className="DressItem_clicked"
            // onClick={onClick}
          >
            <img src={`hamzzibody/hamzzi${id + 1}.png`} alt="" />
          </motion.div>
          <div className="Cost">
            <img src={`coin.png`} alt="" />
            {cost}
          </div>
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
            <img src={`hamzzibody/hamzzi${id + 1}.png`} alt="" />
          </motion.div>
          <div className="Cost">
            <img src={`coin.png`} alt="" />
            {cost}
          </div>
        </div>
      )}
    </>
    
  );
};

HatItem.defaultProps = {
  myItems: [],
};

export default HatItem;
