import React from "react";
import { useSelector } from "react-redux";
import { selectCurrentUser } from "../../../authSlice";
<<<<<<< HEAD
import Header from "./../../../components/Header";

const Profile = () => {
  const user = useSelector(selectCurrentUser);
  return (
    <>
      <Header data={"MYPAGE"} type={"profile"} />
      <div className="MyBody">
        <div className="UserName">{user}'s Profile</div>
        <div className="MyBody2">
          <div className="HamGrad">
            <div className="HamSlot">

              <div className="HamSlotSlot"><img src="hamzzi.png" style={{width:"100%"}} alt="" /></div>
              <div className="HamSlotSlot"><img src="hamzzi.png" style={{width:"100%"}} alt="" /></div>
              <div className="HamSlotSlot"><img src="hamzzi.png" style={{width:"100%"}} alt="" /></div>
            </div>
            <div className="HamSlot">
              <div className="HamSlotSlot"><img src="hamzzi.png" style={{width:"100%"}} alt="" /></div>
              <div className="HamSlotSlot"><img src="hamzzi.png" style={{width:"100%"}} alt="" /></div>
              <div className="HamSlotSlot"><img src="hamzzi.png" style={{width:"100%"}} alt="" /></div>
            </div>
            <div className="HamSlot2">
              <div className="HamSlotSlot"><img src="hamzzi.png" style={{width:"100%"}} alt="" /></div>
              <div className="HamSlotSlot"><img src="hamzzi.png" style={{width:"100%"}} alt="" /></div>
              <div className="HamSlotSlot"><img src="hamzzi.png" style={{width:"100%"}} alt="" /></div>
            </div>
          </div>
          <div className="GuestBook">여긴 방명록</div>
        </div>
      </div>
    </>
  );
=======

const Profile = () => {
  const user = useSelector(selectCurrentUser);
  return <div>{user}'s Profile</div>;
>>>>>>> feature/mobile/homepage
};

export default Profile;
