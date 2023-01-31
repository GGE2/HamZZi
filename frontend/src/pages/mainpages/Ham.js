import React from "react";
import HamOutfit from "./hampages/HamOutfit";
import HamStatus from "./hampages/HamStatus";
import HamHeader from "./hampages/HamHeader";
import "../../styles/Ham.css";

const Ham = () => {
  return (
    <div className="Ham">
      <HamHeader />

      <HamOutfit />
      <HamStatus />
    </div>
  );
};

export default Ham;
