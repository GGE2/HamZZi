import React from "react";
import ProgressBar from "@ramonak/react-progress-bar";

const HamExp = () => {
  // const experience = localStorage.getItem("exp");
  const level = localStorage.getItem("petLevel");
  // const petId = localStorage.getItem("petId");
  const petLevel = localStorage.getItem("petLevel");

  return (

    <>
      <div className="HamExp">
        {petLevel}
        <div className="ProgressBar">
          <ProgressBar
            completed={level}
            maxCompleted={5}
            customLabel={" "}
            // className="wrapper"
            // barContainerClassName="container"
            // completedClassName="barCompleted"
            // labelClassName="label"
           
            bgColor="yellow"
            height="20px"
            width="14.8em"
            // margin="5px"
            labelColor="#000"
            baseBgColor="rgb(146,85,56)"  //
          />
        </div>
        <div className="ExpBar">
          <img src="hamzziStatus/expbar.png" alt="" />
        </div>
      </div>

    </>
  );
};

export default HamExp;
