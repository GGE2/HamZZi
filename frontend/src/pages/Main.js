import React from "react";
import Ham from "./mainpages/Ham";
import Board from "./mainpages/Board";
import "../styles/Main.css";

const Main = () => {
  return (
    <div className="app">
      <div className="Board">
        <div className="Back">
          <div className="Hamster">
            <Ham />
          </div>
          <div className="Screen">
            <Board />
            <div className="BoardButton">
              <button>Todo</button>
              <button>Quest</button>
              <button>Guild</button>
              <button>Friend</button>
              <button style={{borderRadius :'50%'}}>My</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Main;
