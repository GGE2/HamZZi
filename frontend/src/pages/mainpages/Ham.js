import React, { useEffect } from "react";
import HamOutfit from "./hampages/HamOutfit";
import HamStatus from "./hampages/HamStatus";
import HamHeader from "./hampages/HamHeader";
import "../../styles/Ham.css";
import axios from "axios";
import { useDispatch, useSelector } from "react-redux";

import { selectCurrentHamStat, getCurrentStat } from "./../../hamStatSlice";

const Ham = () => {
  const nickname = localStorage.getItem("nickname");
  const dispatch = useDispatch();

  // useEffect(() => {
  //   getPetInfo();
  // }, []);
  return (
    <div className="Ham">
      <HamHeader />

      <HamOutfit />
      <HamStatus />
    </div>
  );
};

export default Ham;
