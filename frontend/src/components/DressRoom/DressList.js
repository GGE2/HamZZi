import React from 'react';
import DressItem from './DressItem';
import { useState } from 'react';

const DressList = () => {
    const [imgNums, setImgNum] = useState([false, false, false, false, false, false, false, false])
    return (
        <div className='DressList'>
            모자
            {imgNums.map((it,idx) => <DressItem key={idx} index={idx} it={it} imgNums={imgNums}/>)}
        </div>
    );
};

export default DressList;