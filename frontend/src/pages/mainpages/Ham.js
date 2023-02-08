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
      <HamHeader />

      <HamOutfit />
      <HamStatus petName={props.petName} />
    </div>
  );
};

export default Ham;
