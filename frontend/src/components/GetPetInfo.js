import React, { useState, useEffect,  } from "react";
// import { useDispatch } from "react-redux";
import api from "./api";
// import { getPetType } from "../hamStatSlice";

// 안녕

const GetPetInfo = () => {
  const currentLevel = localStorage.getItem("petLevel");
  const nickname = localStorage.getItem("nickname");
  // const dispatch = useDispatch();

  api.get(`/api/pet/${nickname}`).then((res) => {
    console.log(res.data.pet);
    // dispatch(getPetType(res.data.petInfo.type));
    if (currentLevel !== res.data.pet.level) {
      localStorage.setItem("petLevel", res.data.pet.level);
    }
  });
};

export default GetPetInfo;
