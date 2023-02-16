import React from "react";

const HistoryItem = ({ type, pet_name, graduate_date }) => {
  return (
    <div className="historyflex">
      <div className="HistoryItem">
        <div>
          {" "}
          <img src={`wearlist/${500 + JSON.stringify(type)}.gif`} alt="" />
        </div>
      </div>
      <div className="Nickname">{pet_name}</div>
      <div>{graduate_date}</div>
    </div>
  );
};

export default HistoryItem;
