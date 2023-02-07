import React, { useState, useEffect, useRef } from "react";
import { useSelector } from "react-redux";
import { selectCurrentHamStat } from "./../../hamStatSlice";
import Friends from "./boardpages/Friends";
import Profile from "./boardpages/Profile";
import Quests from "./boardpages/Quests";
import Todos from "./boardpages/Todos";
import Guild from "./boardpages/Guild";
import "../../styles/Board.css";
import axios from "axios";
import { useDispatch } from "react-redux";
import { getCurrentStat } from "./../../hamStatSlice";

const Board = () => {
  const [name, setName] = useState("");
  const hamStat = useSelector(selectCurrentHamStat);
  const [show, setShow] = useState({
    todoShow: true,
    questShow: false,
    guildShow: false,
    friendShow: false,
    profileShow: false,
  });

  const onClickTodo = () => {
    setShow({
      questShow: false,
      guildShow: false,
      friendShow: false,
      profileShow: false,
      todoShow: true,
    });
  };

  const onClickQuest = () => {
    setShow({
      todoShow: false,

      guildShow: false,
      friendShow: false,
      profileShow: false,
      questShow: true,
    });
  };

  const onClickGuild = () => {
    setShow({
      todoShow: false,
      questShow: false,

      friendShow: false,
      profileShow: false,
      guildShow: true,
    });
  };

  const onClickFriend = () => {
    setShow({
      todoShow: false,
      questShow: false,
      guildShow: false,

      profileShow: false,
      friendShow: true,
    });
  };

  const onClickProfile = () => {
    setShow({
      todoShow: false,
      questShow: false,
      guildShow: false,
      friendShow: false,
      profileShow: true,
    });
  };
  const email = JSON.parse(localStorage.getItem("user"));
  const nickname = localStorage.getItem("nickname");
  const dispatch = useDispatch();

  // const getProfile = () => {
  //   axios
  //     .get(`http://3.35.88.23:8080/api/user/mypage?email=${email}`)
  //     .then((res) => {
  //       // console.log(res.data);
  //       setName(res.data.nickname);
  //       localStorage.setItem("nickname", res.data.nickname);
  //     })
  //     .catch((err) => {
  //       console.log(err);
  //     });
  // };
  // const getPetInfo = () => {
  //   axios
  //     .get(`http://3.35.88.23:8080/api/pet/${nickname}`)
  //     .then((res) => {
  //       console.log(res.data);
  //       const physical = res.data[2].physical;
  //       const artistic = res.data[2].artistic;
  //       const intelligent = res.data[2].intelligent;
  //       const inactive = res.data[2].inactive;
  //       const energetic = res.data[2].energetic;
  //       const etc = res.data[2].etc;
  //       localStorage.setItem("petId", res.data[0].pet_id);
  //       localStorage.setItem("petName", res.data[0].pet_name);
  //       localStorage.setItem("petLevel", res.data[0].level);
  //       localStorage.setItem("exp", res.data[0].exp);
  //       const data = {
  //         physical,
  //         artistic,
  //         intelligent,
  //         inactive,
  //         energetic,
  //         etc,
  //       };
  //       dispatch(getCurrentStat(data));
  //       console.log(data);
  //       console.log("DISPATCHED!!");
  //     })
  //     .catch((err) => {
  //       console.log(err);
  //     });
  // };

  // useEffect(() => {
  //   getProfile();
  // }, []);

  // useEffect(() => {
  //   getPetInfo();
  // }, [name]);

  return (
    <>
      {show.todoShow && <Todos />}
      {/* {show.questShow && <Quests />} */}
      {show.guildShow && <Guild />}
      {show.friendShow && <Friends />}
      {show.profileShow && <Profile />}

      {/* 보드 하단 버튼 리스트 */}
      <div className="BoardButton">
        <button
          onClick={onClickTodo}
          style={{ borderRight: "3px solid black" }}
        >
          Todo
        </button>
        {/* <button
          onClick={onClickQuest}
          style={{ borderRight: "3px solid black" }}
        >
          Quest
        </button> */}
        <button
          onClick={onClickGuild}
          style={{ borderRight: "3px solid black" }}
        >
          Guild
        </button>
        <button
          onClick={onClickFriend}
          style={{ borderRight: "3px solid black" }}
        >
          Friend
        </button>
        <button onClick={onClickProfile}>Profile</button>
      </div>
    </>
  );
};

export default Board;
