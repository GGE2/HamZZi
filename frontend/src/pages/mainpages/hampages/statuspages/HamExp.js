import React from "react";
import ProgressBar from "@ramonak/react-progress-bar";

const HamExp = () => {
  return (
    // <div className='Expbar'>
    // {/* <div className='progress'>
    // HamExp: 99.161561561%
    // </div> */}
    <div >
      {/* <div className="Level">
        <button className="button">8</button>
        </div> */}
      <div className="Exp">
        <ProgressBar
          completed={50}
<<<<<<< HEAD
          customLabel={" "}
=======
>>>>>>> d71fbd50837b97bd096f25692dba0e251672d352
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
  );
};

export default HamExp;
