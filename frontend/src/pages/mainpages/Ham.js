import React from "react";
import HamOutfit from "./hampages/HamOutfit";
import HamStatus from "./hampages/HamStatus";
import HamHeader from "./hampages/HamHeader";
import "../../styles/Ham.css";


const Ham = (props) => {
  return (
    <div className="Ham">
      <HamHeader onDeleteUser={props.onDeleteUser}/>

      <HamOutfit Wear={props.Wear} getAllProfile={props.getAllProfile}/>
      <HamStatus petName={props.petName} setWear={props.setWear}/>
    </div>
  );
};

export default Ham;
