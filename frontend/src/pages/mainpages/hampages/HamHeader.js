import React from 'react';
import { GiAchievement} from "react-icons/gi";
import DropdownMenu from './../../../components/GuestBook/DropdownMenu';



const HamHeader = () => {
    return (
        <div className="Hamheader">
            <div className='HamAchievement'>
                {/* <GiAchievement  size={"40"} color={"orange"} /> */}
                <img src="coin.png" alt="" />
                <img src="./levellogo/lvlogo (1).png" alt="" />
                <b>500000</b>
            </div>
            <div className="Profile">

                <DropdownMenu/>
                
            </div>
        </div>
    );
};

export default HamHeader;