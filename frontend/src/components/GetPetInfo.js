import React, { useState, useEffect } from "react";
import axios from "axios";
import api from './api';

const GetPetInfo = () => {
  const currentLevel = localStorage.getItem("petLevel");
  const nickname = localStorage.getItem("nickname");

  api.get(`/api/pet/${nickname}`).then((res) => {
    console.log(res.data[2]);
    if (currentLevel !== res.data[0].level) {
      localStorage.setItem("petLevel", res.data[0].level);
    }
  });
};

export default GetPetInfo;
