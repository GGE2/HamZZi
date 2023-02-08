import React, { useState, useEffect } from "react";
import { BsPencil } from "react-icons/bs";
import axios from "axios";

const HamName = (props) => {
  const petName = localStorage.getItem("petName");
  const nickname = localStorage.getItem("nickname");
  const [name, setName] = useState(null);

  useEffect(() => {}, []);

  return (
    <div>
      <BsPencil color={"red"} />
      {props.petName}
    </div>
  );
};

export default HamName;
