import React from "react";
import "../styles/Spinner.css";

const LoadingModal = () => {
  return (
    <div className="spinner-container">
      <div className="loading-spinner"><img src="spin.gif" alt="" /></div>
    </div>
  );
};

export default LoadingModal;
