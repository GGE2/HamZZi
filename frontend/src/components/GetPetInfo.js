import React, { useState, useEffect } from "react";
import axios from "axios";

const GetPetInfo = () => {
  const currentLevel = localStorage.getItem("petLevel");
  const nickname = localStorage.getItem("nickname");

  axios.get(`http://3.35.88.23:8080/api/pet/${nickname}`).then((res) => {
    console.log(res.data[2]);
    if (currentLevel !== res.data[0].level) {
      localStorage.setItem("petLevel", res.data[0].level);
    }
  });
};

export default GetPetInfo;
