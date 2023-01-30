import React from 'react';
import { GiAchievement} from "react-icons/gi";

const HamHeader = () => {
    return (
        <div className="Hamheader">

            <div className='HamAchievement'>
                <GiAchievement  size={"40"} color={"orange"} />
                <b>500000</b>
            </div>
        </div>
    );
};

export default HamHeader;