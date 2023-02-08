import React from "react";
import ProgressBar from "@ramonak/react-progress-bar";
import axios from "axios";

const HamExp = () => {
  const experience = localStorage.getItem("exp");
  const level = localStorage.getItem("petLevel");
  const petId = localStorage.getItem("petId");

  return (
    // <div className='Expbar'>
    // {/* <div className='progress'>
    // HamExp: 99.161561561%
    // </div> */}
    <>
      <div>
        {/* <div className="Level">
        <button className="button">8</button>
        </div> */}
        <div className="Exp">
          <ProgressBar
            completed={level}
            maxCompleted={5}
            customLabel={" "}
            // className="wrapper"
            // barContainerClassName="container"
            // completedClassName="barCompleted"
            // labelClassName="label"
            bgColor="yellow"
            height="10px"
            width="70%"
            margin="5px"
            labelColor="#000"
            baseBgColor="#000"
          />
        </div>
      </div>
      {/* {level === "5" && (
        <button
          style={{ position: "relative", top: "30px" }}
          onClick={handleGraduate}
        >
          graduate
        </button>
      )} */}
    </>
  );
};

export default HamExp;
