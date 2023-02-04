<<<<<<< HEAD
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
          completed={80}
          // className="wrapper"
          // barContainerClassName="container"
          // completedClassName="barCompleted"
          // labelClassName="label"
          bgColor="green"
          width="70%"
          margin="5px"
          labelColor="#fff"
          baseBgColor="#000"
        />
      </div>
    </div>
  );
};

export default HamExp;
=======
import React from 'react';

const HamExp = () => {
    return (
        <div>
            HamExp
        </div>
    );
};

export default HamExp;
>>>>>>> feature/mobile/homepage
