import React, { useEffect } from "react";
import Ham from "./mainpages/Ham";
import Board from "./mainpages/Board";
import axios from "axios";
import "../styles/Main.css";

import {
  FcSearch,
  FcWebcam,
  FcConferenceCall,
  FcCollaboration,
  FcTodoList,
  FcRating,
  FcBookmark,
} from "react-icons/fc";
import { BiDotsHorizontalRounded } from "react-icons/bi";
import { BsBookmarkStar, BsBookmarkStarFill } from "react-icons/bs";

import { useDispatch } from "react-redux";
import { setCredentials } from "../authSlice";

const Main = () => {
  useEffect(() => {}, []);

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
              <BsBookmarkStarFill color={"orange"} size={"100%"} />
            </button>
            <button>
              <FcWebcam size={"100%"} />
            </button>
            {/* <button>
              <FcConferenceCall size={"100%"} />
            </button>

            <button>
              <FcBookmark size={"100%"} />
            </button>

            <button>
              <FcTodoList size={"100%"} />
            </button>
            <button>
              <FcCollaboration size={"100%"} />
            </button> */}
            {/* <button>
              <BsBookmarkStar color={'orange'} size={"100%"} />
            </button> */}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Main;
