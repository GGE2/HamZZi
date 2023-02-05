import React, { useEffect } from "react";
import Ham from "./mainpages/Ham";
import Board from "./mainpages/Board";
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
<<<<<<< HEAD
import { BiDotsHorizontalRounded } from "react-icons/bi";
import { BsBookmarkStar, BsBookmarkStarFill } from "react-icons/bs";
=======
import { BiDotsHorizontalRounded} from "react-icons/bi";
>>>>>>> d71fbd50837b97bd096f25692dba0e251672d352

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
              <BsBookmarkStarFill color={'orange'} size={"100%"} />
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
<<<<<<< HEAD
            </button> */}
            {/* <button>
              <BsBookmarkStar color={'orange'} size={"100%"} />
            </button> */}
=======
            </button>
>>>>>>> d71fbd50837b97bd096f25692dba0e251672d352
            
          </div>
        </div>
      </div>
    </div>
  );
};

export default Main;
