import React from "react";
import { GiAchievement } from "react-icons/gi";
import DropdownMenu from "./../../../components/GuestBook/DropdownMenu";
import { useSelector } from "react-redux";
import { selectCurrentPoint } from "./../../../pointSlice";

const HamHeader = () => {
  const point = useSelector(selectCurrentPoint);

  return (
    <div className="Hamheader">
      <div className="Costt">
        <img src={`coin.png`} alt="" />
        {point}
      </div>
      {/* <div className="HamAchievement">
        <GiAchievement size={"40"} color={"orange"} />
        <b>500000</b>
      </div> */}
      <div className="Profile">
        <DropdownMenu />
      </div>
    </div>
  );
};

export default HamHeader;
