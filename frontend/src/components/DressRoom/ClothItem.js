import React from 'react';
import { motion } from 'framer-motion';

const ClothItem = ({imgNums, it, index}) => {
    // console.log(imgNums[0])

    const onClick = () => {

    }

    return (
        <motion.div
        whileHover={{
            scale: 1.1,
            transition: { duration: 0.2 },
          }}
        //   whileTap={{ scale: 0.5 }}
         className='ClothItem'>
         {/* className={ClothItem[index] ? "DressItem_clicked" : "DressItem_Unclicked"} */}
         
      
            <img src={`hamzzibody/hamzzi${index+1}.png`} alt="" />
        </motion.div>
    );
};

export default ClothItem;