import React from "react";
import ProgressBar from "@ramonak/react-progress-bar";
import { useSelector } from 'react-redux';
import { selectCurrentExp } from "../../../../ExpSlice";

const HamExp = ({graduatee}) => {
  // const experience = localStorage.getItem("exp");
  const level = localStorage.getItem("petLevel");
  // const petId = localStorage.getItem("petId");
  const petLevel = localStorage.getItem("petLevel");
  // const expp = localStorage.getItem("exp")
  // const expp = useSelector(selectCurrentExp)
  const exp = [0, 14, 30, 60, 66]
  // console.log(exp[1])

  return (
    <>
      <div className="HamExp">
      {level === "5" ? (
          <>
            <div className="graduatebtn" onClick={graduatee}>
              <img src="graduateB.png" alt="" />
            </div>
          </>
      )
          : <>
          <div className="PetName">{level}</div>
        
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
            baseBgColor="rgb(146,85,56)" //
          />
        </div>
        <div className="ExpBar">
          <img src="hamzziStatus/expbar.png" alt="" />
        </div>
          </>
        }
        
      </div>
    </>
  );
};

export default HamExp;
