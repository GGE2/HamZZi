import React from "react";
import axios from "axios";
import GetPetInfo from "./GetPetInfo";
import api from "./api";

const increaseExp = () => {
  const petId = localStorage.getItem("petId");
  api.put(`/api/pet/exp?pet_id=${petId}&exp=${3}`).then((res) => {
    console.log(res);
    GetPetInfo();
  });
};

export default increaseExp;
