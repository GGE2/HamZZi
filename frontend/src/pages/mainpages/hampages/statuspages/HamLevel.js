import React, { useState } from "react";
import axios from "axios";

const HamLevel = () => {
  const petLevel = localStorage.getItem("petLevel");
  // const level = localStorage.getItem("petLevel");
  
  return (
    <div>
      {/* <button className="HamLevel">{petLevel}{petLevel === "5" && (
          <>
            <div className="graduatebtn" onClick={graduatee}>
              <img src="graduateB.png" alt="" />
            </div>
          </>
        )}</button> */}
    </div>
  );
};

export default HamLevel;
