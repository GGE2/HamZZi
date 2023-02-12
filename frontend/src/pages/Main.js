import React, { useEffect, useState } from "react";
import Ham from "./mainpages/Ham";
// import Board from "./mainpages/Board";
import axios from "axios";
import "../styles/Main.css";

import Todos from "./mainpages/boardpages/Todos";
import Guild from "./mainpages/boardpages/Guild";
import Quests from "./mainpages/boardpages/Quests";
import DressRoom from "./mainpages/boardpages/DressRoom";
import Profile from "./mainpages/boardpages/Profile";

import { useDispatch } from "react-redux";
// import { setCredentials } from "../authSlice";
import { getCurrentStat } from "../hamStatSlice";
import api from "./../components/api";
import LoadingModal from "./../components/LoadingModal";
import { receivePoint } from "../pointSlice";
// import SetNickName from './loginpages/SetNickName';

const Main = () => {
  const [petName, setPetName] = useState("");
  const [name, setName] = useState("");
  const email = JSON.parse(localStorage.getItem("user"));
  // const nickname = localStorage.getItem("nickname");
  const [nickname, SetNickName] = useState();
  const dispatch = useDispatch();

  const [user, setUser] = useState({});
  const [guildUsers, setGuildUsers] = useState({
    admin: false,
    guild: null,
    nickname: nickname,
  });
  const [guildId, setGuildId] = useState(0);
  const [guildName, setGuildName] = useState("");

  const [loading1, setLoading1] = useState(true);
  const [loading2, setLoading2] = useState(true);
  const [loading3, setLoading3] = useState(true);

  // 화면 보여주는 플래그
  const [show, setShow] = useState({
    todoShow: true,
    guildShow: false,
    friendShow: false,
    profileShow: false,
  });
  // 버튼 눌림 css
  const [menu, setMenu] = useState([true, false, false, false, false]);

  const getPetInfo = (nickname) => {
    setLoading1(true);
    api
      .get(`/api/pet/${nickname}`)
      .then((res) => {
        console.log(res.data);
        const physical = res.data.petStat.physical;
        const artistic = res.data.petStat.artistic;
        const intelligent = res.data.petStat.intelligent;
        const inactive = res.data.petStat.inactive;
        const energetic = res.data.petStat.energetic;
        const etc = res.data.petStat.etc;
        setPetName(res.data.pet.pet_name);
        localStorage.setItem("petId", res.data.pet.pet_id);
        localStorage.setItem("petName", res.data.pet.pet_name);
        localStorage.setItem("petLevel", res.data.pet.level);
        localStorage.setItem("exp", res.data.pet.exp);
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
        setLoading1(false);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const getProfile = () => {
    setLoading2(true);
    api
      .get(`/api/user/mypage?email=${email}`)
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
        dispatch(receivePoint(res.data.point));
        SetNickName(res.data.nickname);
        setLoading2(false);
        getPetInfo(res.data.nickname);
        onGetUserGuildInfo(res.data.nickname);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // 유저 길드 정보 가져오기 api
  // 닉네임으로 가져옴. nickname
  const onGetUserGuildInfo = async (nickname) => {
    setLoading3(true);
    await api.get(`/api/guild/user?nickname=${nickname}`).then((res) => {
      console.log("유저 길드 정보 가져오기 api");
      console.log(res.data);
      if (res.data.guild) {
        setGuildId(res.data.guild.guild_id);
        console.log(guildId);
        setGuildUsers(res.data);
        setGuildName(res.data.guild.guild_name);
      }
      setLoading3(false);
    });
  };

  useEffect(() => {
    // callData()
    getProfile();
    // getPetInfo();
    //     onGetUserGuildInfo(); // 유저 길드 정보 가져오기
  }, []);

  useEffect(() => {
    getPetInfo();
  }, [name]);

  // 메뉴 선택 함수
  const onClickTodo = () => {
    setShow({
      todoShow: true,
      guildShow: false,
      friendShow: false,
      profileShow: false,
      dressShow: false,
    });
    setMenu([true, false, false, false, false]);
  };

  const onClickGuild = () => {
    setShow({
      todoShow: false,
      guildShow: true,
      friendShow: false,
      profileShow: false,
      dressShow: false,
    });
    setMenu([false, true, false, false, false]);
  };

  const onClickFriend = () => {
    setShow({
      todoShow: false,
      guildShow: false,
      friendShow: true,
      profileShow: false,
      dressShow: false,
    });
    setMenu([false, false, true, false, false]);
  };

  const onClickProfile = () => {
    setShow({
      todoShow: false,
      guildShow: false,
      friendShow: false,
      profileShow: true,
      dressShow: false,
    });
    setMenu([false, false, false, true, false]);
  };

  const onClickDressRoom = () => {
    setShow({
      todoShow: false,
      guildShow: false,
      friendShow: false,
      profileShow: false,
      dressShow: true,
    });
    setMenu([false, false, false, false, true]);
  };

  return (
    <div className="app">
      <div className="Board">
        <div className="Back">
          {loading2 === false ? (
            <>
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
                    guildName={guildName}
                  />
                )}

                {show.friendShow && <Quests user={user} />}
                {show.profileShow && <Profile user={user} />}
                {show.dressShow && <DressRoom user={user} />}
              </div>

              <div className="buttonflex">
                <button
                  className={menu[0] ? "BoardButto--active0" : ""}
                  onClick={onClickTodo}
                  style={{ borderRight: "3px solid black" }}
                >
                  Todo
                </button>
                <button
                  className={menu[1] ? "BoardButto--active1" : ""}
                  onClick={onClickGuild}
                  style={{ borderRight: "3px solid black" }}
                >
                  Guild
                </button>
                <button
                  className={menu[2] ? "BoardButto--active2" : ""}
                  onClick={onClickFriend}
                  style={{ borderRight: "3px solid black" }}
                >
                  Quest
                </button>
                <button
                  className={menu[3] ? "BoardButto--active3" : ""}
                  onClick={onClickProfile}
                >
                  Room
                </button>
                <button
                  className={menu[4] ? "BoardButto--active4" : ""}
                  onClick={onClickDressRoom}
                >
                  Shop
                </button>
              </div>
            </>
          ) : (
            <LoadingModal />
          )}
        </div>
      </div>
    </div>
  );
};

export default Main;
