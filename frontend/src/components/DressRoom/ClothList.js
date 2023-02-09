import React, {useState} from 'react';
import ClothItem from './ClothItem';

const ClothList = () => {
    const [imgNums, setImgNum] = useState([false, false, false, false, false, false, false, false])
    return (
        <div className='ClothList'>
         의류
            {imgNums.map((it,idx) => <ClothItem key={idx} index={idx} it={it} imgNums={imgNums}/>)}
        </div>
    );
};

export default ClothList;