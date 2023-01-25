import React from "react";
import HamExp from "./statuspages/HamExp";
import HamLevel from "./statuspages/HamLevel";
import HamName from "./statuspages/HamName";
import "../../../styles/HamStatus.css";

const HamStatus = () => {
  return (
    <div className="HamStatus">
      HamStatus
      <HamExp />
      <HamLevel />
      <HamName />
    </div>
  );
};

export default HamStatus;
