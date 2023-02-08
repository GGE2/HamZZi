import React from "react";
import axios from "axios";
import GetPetInfo from "./GetPetInfo";

const increaseExp = () => {
  const petId = localStorage.getItem("petId");
  axios
    .put(`http://3.35.88.23:8080/api/pet/exp?pet_id=${petId}&exp=${5}`)
    .then((res) => {
      console.log(res);
      GetPetInfo();
    });
};

export default increaseExp;
