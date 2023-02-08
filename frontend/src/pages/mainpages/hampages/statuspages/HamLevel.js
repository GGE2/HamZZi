import React, { useState } from "react";
import axios from "axios";

const HamLevel = () => {
  const petLevel = localStorage.getItem("petLevel");
  return (
    <div>
      <button className="HamLevel">{petLevel}</button>
    </div>
  );
};

export default HamLevel;
