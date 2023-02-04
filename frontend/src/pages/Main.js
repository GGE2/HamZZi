import React, { useEffect } from "react";
import Ham from "./mainpages/Ham";
import Board from "./mainpages/Board";
import "../styles/Main.css";

import { FcSearch, FcWebcam, FcConferenceCall,  FcCollaboration, FcTodoList, FcRating } from "react-icons/fc";
import { BiDotsHorizontalRounded, BiDotsVerticalRounded } from "react-icons/bi";

import { useDispatch } from "react-redux";
import { setCredentials } from "../authSlice";

const Main = () => {
  const dispatch = useDispatch();

  useEffect(() => {
    const email = localStorage.getItem("user");
    if (email !== null) {
      dispatch(
        setCredentials({
          user: email,
        })
      );
    }
  });

  return (
    <div className="app">
      <div className="Board">
        <div className="Back">
          <div className="Hamster">
            <Ham />
          </div>
          <div className="Screen">
            <Board />
          </div>
          <div className="buttonflex">
            <button>
              <FcSearch size={"100%"} />
            </button>
            <button>
              <FcWebcam size={"100%"} />
            </button>
            <button>
              <FcConferenceCall size={"100%"} />
            </button>
            
            <button>
              <FcRating size={"100%"} />
            </button>
            
            <button>
              <FcTodoList size={"100%"} />
            </button>
            <button>
              <FcCollaboration size={"100%"} />
            </button>
            <button>
              <BiDotsHorizontalRounded size={"100%"} />
            </button>
            <button>
              <BiDotsVerticalRounded size={"100%"} />
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Main;
