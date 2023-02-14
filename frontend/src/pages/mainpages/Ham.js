import React, { useEffect } from "react";
import HamOutfit from "./hampages/HamOutfit";
import HamStatus from "./hampages/HamStatus";
import HamHeader from "./hampages/HamHeader";
import "../../styles/Ham.css";
import axios from "axios";
import { useDispatch, useSelector } from "react-redux";

import { selectCurrentHamStat, getCurrentStat } from "./../../hamStatSlice";

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
