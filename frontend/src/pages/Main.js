import React, { useEffect, useState } from "react";
import Ham from "./mainpages/Ham";
import "../styles/Main.css";

import Todos from "./mainpages/boardpages/Todos";
import Guild from "./mainpages/boardpages/Guild";
import Quests from "./mainpages/boardpages/Quests";
import Shop from "./mainpages/boardpages/Shop";
import Profile from "./mainpages/boardpages/Profile";

import { useDispatch } from "react-redux";
import { getCurrentStat, getPetLevel, getPetType } from "../hamStatSlice";
import api from "./../components/api";
import LoadingModal from "./../components/LoadingModal";
import LoadingModal2 from "./../components/LoadingModal2";
import { receivePoint } from "../pointSlice";
import History from './mainpages/boardpages/History';

const Main = () => {
  const [petName, setPetName] = useState("");
  const [name, setName] = useState("");
  const email = JSON.parse(localStorage.getItem("user"));
  const uid = JSON.parse(localStorage.getItem("uid"));
  // const [point, setPoint] = useState(0)
  // const [restpoint, setRestPoint] = useState(0)

  // 아이템 착용하는거
  const [Wear, setWear] = useState({
    hat: 0,
    dress: 0,
    background: 0,
  });

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

  const [loading, setLoading] = useState(true);

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
    api
      .get(`/api/pet/${nickname}`)
      .then((res) => {
        // console.log(res.data);
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
        dispatch(getPetLevel(res.data.pet.level));
        dispatch(getPetType(res.data.petInfo.type));
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

        // console.log(data);
        // console.log("DISPATCHED!!");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const getProfile = () => {
    setLoading(true);
    api
      .get(`/api/user/mypage?email=${email}`)
      .then((res) => {
        // console.log("회원 정보 조회 api");
        // console.log(res.data);
        localStorage.setItem("nickname", res.data.nickname);
        setUser({
          point: res.data.point,
          nickname: res.data.nickname,
          rest_point: res.data.rest_point,
        });
        dispatch(receivePoint(res.data.point));
        SetNickName(res.data.nickname);
        getPetInfo(res.data.nickname);
        onGetUserGuildInfo(res.data.nickname);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // 유저
  const getAllProfile = () => {
    setLoading(true);
    api
      .get(`/api/user/info/${uid}`)
      .then((res) => {
        // console.log("회원 모든 정보 조회 api");
        // console.log(res.data);
        localStorage.setItem("nickname", res.data.userProfile.nickname);
        // console.log("nickname 설정!!!");
        setUser({
          // guild: res.data.guild,
          point: res.data.userProfile.point,
          nickname: res.data.userProfile.nickname,
          rest_point: res.data.userProfile.rest_point,
        });
        dispatch(receivePoint(res.data.userProfile.point));
        SetNickName(res.data.userProfile.nickname);
        getPetInfo(res.data.userProfile.nickname);
        onGetUserGuildInfo(res.data.userProfile.nickname);
        setWear({
          hat: res.data.userProfile.hat,
          dress: res.data.userProfile.dress,
          background: res.data.userProfile.background,
        });
        // setLoading2(false);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // // 유저
  const getShopUpdate = () => {
    setLoading(true);
    api
      .get(`/api/user/info/${uid}`)
      .then((res) => {
        // console.log("회원 모든 정보 조회 api");
        // console.log(res.data);
        // localStorage.setItem("nickname", res.data.userProfile.nickname);
        setUser({
          // guild: res.data.guild,
          point: res.data.userProfile.point,
          nickname: res.data.userProfile.nickname,
          rest_point: res.data.userProfile.rest_point,
        });
        setWear({
          hat: res.data.userProfile.hat,
          dress: res.data.userProfile.dress,
          background: res.data.userProfile.background,
        });
        dispatch(receivePoint(res.data.userProfile.point));
        // SetNickName(res.data.userProfile.nickname);
        // getPetInfo(res.data.userProfile.nickname);
        // onGetUserGuildInfo(res.data.userProfile.nickname);
        getPetInfo(
          res.data.userProfile.nickname,
          res.data.userProfile.hat,
          res.data.userProfile.dress
        );
        // setLoading2(false);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // 유저 길드 정보 가져오기 api
  // 닉네임으로 가져옴. nickname
  const onGetUserGuildInfo = async (nickname) => {
    await api.get(`/api/guild/user?nickname=${nickname}`).then((res) => {
      // console.log("유저 길드 정보 가져오기 api");
      // console.log(res.data);
      if (res.data.guild) {
        setGuildId(res.data.guild.guild_id);
        // console.log(guildId);
        setGuildUsers(res.data);
        setGuildName(res.data.guild.guild_name);
      }
    });
  };

  // 회원탈퇴
  const onDeleteUser = async () => {
    await api.delete(`/api/user/delete?email=${email}`).then((res) => {
      // console.log("유저 삭제");
      // console.log(res.data);
    });
  };

  useEffect(() => {
    // callData()
    getAllProfile();
    // getProfile();
    // getPetInfo();
    //     onGetUserGuildInfo(); // 유저 길드 정보 가져오기
  }, []);

  useEffect(() => {
    getPetInfo();
  }, [name]);

  useEffect(() => {
    setTimeout(() => {
      setLoading(false);
    }, 1500);
  });

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

  const onClickQuest = () => {
    setShow({
      todoShow: false,
      guildShow: false,
      friendShow: true,
      profileShow: false,
      dressShow: false,
    });
    setMenu([false, false, true, false, false]);
  };

  const onClickHistory = () => {
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
    <>
      <div className="app">
        <div className="appboard">
          <div className="Board">
            <div className="Back">
              <div className="Hamster">
                {loading === false ? (
                  <Ham
                    petName={petName}
                    Wear={Wear}
                    getAllProfile={getAllProfile}
                    onDeleteUser={onDeleteUser}
                    setWear={setWear}
                  />
                ) : null}
              </div>
              <div className="Screen">
                <div className="spring">
                  <img src="homeback/spring.png" alt="" />
                </div>
                {loading === false ? (
                  <>
                    {show.todoShow && <Todos user={user} setUser={setUser} />}

                    {show.guildShow && (
                      <Guild
                        setGuildUsers={setGuildUsers}
                        guildUsers={guildUsers}
                        guildId={guildId}
                        onGetUserGuildInfo={onGetUserGuildInfo}
                        guildName={guildName}
                      />
                    )}

                    {show.friendShow && <Quests user={user} />}
                    {show.dressShow && (
                      <Shop
                        user={user}
                        getAllProfile={getAllProfile}
                        getShopUpdate={getShopUpdate}
                        Wear={Wear}
                      />
                    )}
                    {show.profileShow && <History />}
                  </>
                ) : (
                  <LoadingModal2 />
                )}
              </div>
              <div className="buttonflex">
                <button
                  className={menu[0] ? "BoardButto--active0" : "button1"}
                  onClick={onClickTodo}
                >
                  Todo
                </button>
                <button
                  className={menu[1] ? "BoardButto--active1" : "button2"}
                  onClick={onClickGuild}
                >
                  Guild
                </button>
                <button
                  className={menu[2] ? "BoardButto--active2" : "button3"}
                  onClick={onClickQuest}
                >
                  Quest
                </button>
                <button
                  className={menu[4] ? "BoardButto--active4" : "button4"}
                  onClick={onClickDressRoom}
                  >
                  Shop
                </button>
                  <button
                    className={menu[3] ? "BoardButto--active3" : "button5"}
                    onClick={onClickHistory}
                  >
                    History
                  </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Main;
