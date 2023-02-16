import React from "react";
import axios from "axios";
import GetPetInfo from "./GetPetInfo";
import api from "./api";

const increaseExp = () => {
  const petId = localStorage.getItem("petId");
  const nickname = localStorage.getItem("nickname");
  api
    .put(`/api/pet/exp?pet_id=${petId}&exp=${3}&nickname=${nickname}`)
    .then((res) => {
      console.log(res);
      GetPetInfo();
    });
};

export default increaseExp;
