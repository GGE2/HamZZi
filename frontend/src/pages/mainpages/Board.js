import React from "react";
import Friends from "./boardpages/Friends";
import Profile from "./boardpages/Profile";
import Quests from "./boardpages/Quests";
import Todos from "./boardpages/Todos";
import "../../styles/Board.css"


const Board = () => {
  return (
    <div>
      Board
      <Friends />
      <Profile />
      <Quests />
      <Todos />
      
    </div>
  );
};

export default Board;
