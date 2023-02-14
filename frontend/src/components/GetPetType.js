import React from "react";
import api from "./api";
import { useDispatch } from "react-redux";
import { getPetType } from "../hamStatSlice";

const GetPetType = () => {
  const nickname = localStorage.getItem("nickname");
  const dispatch = useDispatch();

  api.get(`/api/pet/${nickname}`).then((res) => {
    console.log(res.data.petInfo.type);
    // dispatch(getPetType(res.data.petInfo.type));
  });

  return <div>GetPetType</div>;
};

export default GetPetType;
