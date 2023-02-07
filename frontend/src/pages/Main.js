import React, { useEffect, useState } from "react";
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
import { getCurrentStat } from "../hamStatSlice";

const Main = () => {
  const [petName, setPetName] = useState("");
  const [name, setName] = useState("");
  const email = JSON.parse(localStorage.getItem("user"));
  const nickname = localStorage.getItem("nickname");
  const dispatch = useDispatch();

  const getProfile = () => {
    axios
      .get(`http://3.35.88.23:8080/api/user/mypage?email=${email}`)
      .then((res) => {
        // console.log(res.data);
        setName(res.data.nickname);
        localStorage.setItem("nickname", res.data.nickname);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  const getPetInfo = () => {
    axios
      .get(`http://3.35.88.23:8080/api/pet/${nickname}`)
      .then((res) => {
        console.log(res.data);
        const physical = res.data[2].physical;
        const artistic = res.data[2].artistic;
        const intelligent = res.data[2].intelligent;
        const inactive = res.data[2].inactive;
        const energetic = res.data[2].energetic;
        const etc = res.data[2].etc;
        setPetName(res.data[0].pet_name);
        localStorage.setItem("petId", res.data[0].pet_id);
        localStorage.setItem("petName", res.data[0].pet_name);
        localStorage.setItem("petLevel", res.data[0].level);
        localStorage.setItem("exp", res.data[0].exp);
        const data = {
          physical,
          artistic,
          intelligent,
          inactive,
          energetic,
          etc,
        };
        dispatch(getCurrentStat(data));
        console.log(data);
        console.log("DISPATCHED!!");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    getProfile();
  }, []);

  useEffect(() => {
    getPetInfo();
  }, [name]);

  return (
    <div className="app">
      <div className="Board">
        <div className="Back">
          <div className="Hamster">
            <Ham petName={petName} />
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
