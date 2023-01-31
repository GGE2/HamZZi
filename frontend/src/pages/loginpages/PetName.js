import React from "react";
import { useState } from "react";
// import { axios } from "axios";
import { useNavigate } from "react-router";

const PetName = () => {
  const [petName, setPetName] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    setPetName(e.target.value);
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    // axios
    //   .post("/api/pet/name", {
    //     pet_name: petName,
    //   })
    //   .then(() => {
    //     navigate("/main");
    //   })
    //   .catch((error) => {
    //     console.log(error);
    //   });
  };

  return (
    <div>
      <h1>햄스터 이름</h1>
      <h2>당신의 햄스터의 이름을 정해주세요</h2>
      <form onSubmit={handleSubmit}>
        <input type="text" value="petName" onChange={handleChange} />
        <button type="submit">이름 정하기</button>
      </form>
    </div>
  );
};

export default PetName;
