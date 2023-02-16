import React from "react";
import { GiAchievement } from "react-icons/gi";
import DropdownMenu from "./../../../components/GuestBook/DropdownMenu";
import { useSelector } from "react-redux";
import { selectCurrentPoint } from "./../../../pointSlice";

const HamHeader = ({ onDeleteUser }) => {
  const point = useSelector(selectCurrentPoint);
  const nickname = localStorage.getItem("nickname");

  return (
    <div className="Hamheader">
      <div className="Costt">
        <img src={`coin.png`} alt="" />
        {point}
      </div>
      <div className="Nickname"> {nickname} </div>

      <div className="Profile">
        <DropdownMenu onDeleteUser={onDeleteUser} />
      </div>
    </div>
  );
};

export default HamHeader;
