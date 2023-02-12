import React, { useState, useEffect } from "react";
import axios from "axios";
import api from "./api";

// 안녕

const GetPetInfo = () => {
  const currentLevel = localStorage.getItem("petLevel");
  const nickname = localStorage.getItem("nickname");

  api.get(`/api/pet/${nickname}`).then((res) => {
    console.log(res.data.pet);
    if (currentLevel !== res.data.pet.level) {
      localStorage.setItem("petLevel", res.data.pet.level);
    }
  });
};

export default GetPetInfo;
