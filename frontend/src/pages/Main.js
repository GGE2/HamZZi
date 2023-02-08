import React, { useEffect, useState } from "react";
import Ham from "./mainpages/Ham";
// import Board from "./mainpages/Board";
import axios from "axios";
import "../styles/Main.css";

import Todos from "./mainpages/boardpages/Todos";
import Guild from "./mainpages/boardpages/Guild";
import Friends from "./mainpages/boardpages/Friends";
import Profile from "./mainpages/boardpages/Profile";

import {
  FcSearch,
  FcWebcam,
  // FcConferenceCall,
  // FcCollaboration,
  // FcTodoList,
  // FcRating,
  // FcBookmark,
} from "react-icons/fc";
// import { BiDotsHorizontalRounded } from "react-icons/bi";
import { BsBookmarkStar, BsBookmarkStarFill } from "react-icons/bs";

import { useDispatch } from "react-redux";
// import { setCredentials } from "../authSlice";
import { getCurrentStat } from "../hamStatSlice";

const Main = () => {
  const [petName, setPetName] = useState("");
  const [name, setName] = useState("");
  const email = JSON.parse(localStorage.getItem("user"));
  const nickname = localStorage.getItem("nickname");
  const dispatch = useDispatch();

  const [user, setUser] = useState({});
  const [guildUsers, setGuildUsers] = useState({
    admin: false,
    guild: null,
    nickname: nickname,
  });
  const [guildId, setGuildId] = useState(0);

  // 화면 보여주는 플래그
  const [show, setShow] = useState({
    todoShow: true,
    guildShow: false,
    friendShow: false,
    profileShow: false,
  });
  // 버튼 눌림 css
  const [menu, setMenu] = useState([true, false, false, false]);

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
    getPetInfo();
    onGetUserGuildInfo(); // 유저 길드 정보 가져오기
  }, []);

  useEffect(() => {
    getPetInfo();
  }, [name]);

  const getProfile = () => {
    axios
      .get(`http://3.35.88.23:8080/api/user/mypage?email=${email}`)
      .then((res) => {
        console.log("회원 정보 조회 api");
        console.log(res.data);
        localStorage.setItem("nickname", res.data.nickname);
        setUser({
          // guild: res.data.guild,
          point: res.data.point,
          nickname: res.data.nickname,
          rest_point: res.data.rest_point,
        });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // 유저 길드 정보 가져오기 api
  // 닉네임으로 가져옴. nickname
  const onGetUserGuildInfo = async () => {
    await axios
      .get(`http://3.35.88.23:8080/api/guild/user?nickname=${nickname}`)
      .then((res) => {
        console.log("유저 길드 정보 가져오기 api");
        console.log(res.data);
        if (res.data.guild) {
          setGuildId(res.data.guild.guild_id);
          console.log(guildId);
          setGuildUsers(res.data);
        }

        // setGuilds(res.data);
      });
  };

  // 메뉴 선택 함수
  const onClickTodo = () => {
    setShow({
      todoShow: true,
      guildShow: false,
      friendShow: false,
      profileShow: false,
    });
    setMenu([true, false, false, false]);
    console.log("투두");
  };

  const onClickGuild = () => {
    setShow({
      todoShow: false,
      guildShow: true,
      friendShow: false,
      profileShow: false,
    });
    setMenu([false, true, false, false]);
    console.log("길드");
    console.log(show);
  };

  const onClickFriend = () => {
    setShow({
      todoShow: false,
      guildShow: false,
      friendShow: true,
      profileShow: false,
    });
    setMenu([false, false, true, false]);
    console.log("친구");
  };

  const onClickProfile = () => {
    setShow({
      todoShow: false,
      guildShow: false,
      friendShow: false,
      profileShow: true,
    });
    setMenu([false, false, false, true]);
    console.log("프로필");
    console.log(show);
  };

  return (
    <div className="app">
      <div className="Board">
        <div className="Back">
          <div className="Hamster">
            <Ham petName={petName} />
          </div>
          <div className="Screen">
            {show.todoShow && <Todos user={user} />}
            {show.guildShow && (
              <Guild
                // user={user}
                setGuildUsers={setGuildUsers}
                guildUsers={guildUsers}
                guildId={guildId}
                onGetUserGuildInfo={onGetUserGuildInfo}
              />
            )}
            {show.friendShow && <Friends user={user} />}
            {show.profileShow && <Profile user={user} />}
          </div>

          <div className="buttonflex">
            {/* <button>
              <FcSearch size={"100%"} />
            </button>
            <button>
              <BsBookmarkStarFill color={"orange"} size={"100%"} />
            </button>
            <button>
              <FcWebcam size={"100%"} />
            </button> */}
            <button
              className={menu[0] ? "BoardButto--active" : ""}
              onClick={onClickTodo}
              style={{ borderRight: "3px solid black" }}
            >
              Todo
            </button>
            <button
              className={menu[1] ? "BoardButto--active" : ""}
              onClick={onClickGuild}
              style={{ borderRight: "3px solid black" }}
            >
              Guild
            </button>
            <button
              className={menu[2] ? "BoardButto--active" : ""}
              onClick={onClickFriend}
              style={{ borderRight: "3px solid black" }}
            >
              Friend
            </button>
            <button
              className={menu[3] ? "BoardButto--active" : ""}
              onClick={onClickProfile}
            >
              Room
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Main;
