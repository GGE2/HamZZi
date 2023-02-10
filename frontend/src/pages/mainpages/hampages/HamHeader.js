import React from "react";
import { GiAchievement } from "react-icons/gi";
import DropdownMenu from "./../../../components/GuestBook/DropdownMenu";
import { useSelector } from "react-redux";
import { selectCurrentPoint } from "./../../../pointSlice";

const HamHeader = () => {
  const point = useSelector(selectCurrentPoint);

  return (
    <div className="Hamheader">
      <p>내 포인트: {point} </p>
      <div className="HamAchievement">
        <GiAchievement size={"40"} color={"orange"} />
        <b>500000</b>
      </div>
      <div className="Profile">
        <DropdownMenu />
      </div>
    </div>
  );
};

export default HamHeader;
