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
  // 리종길
  const email = JSON.parse(localStorage.getItem("user"));
  const dispatch = useDispatch();
  const [name, setName] = useState("");
  const hamStat = useSelector(selectCurrentHamStat);
  const [show, setShow] = useState({
    todoShow: true,
    questShow: false,
    guildShow: false,
    friendShow: false,
    profileShow: false,
  });

  // 리진성
  const nickname = localStorage.getItem("nickname");
  const [user, setUser] = useState({});
  const [guildUsers, setGuildUsers] = useState({
    admin: false,
    guild: null,
    nickname: nickname,
  });
  const [guildId, setGuildId] = useState(0);
  const [menu, setMenu] = useState([true, false, false, false]);

  // 메뉴 선택 함수
  const onClickTodo = () => {
    setShow({
      questShow: false,
      guildShow: false,
      friendShow: false,
      profileShow: false,
      todoShow: true,
    });
    setMenu([true, false, false, false]);
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
    setMenu([ false, true, false, false]);
  };

  const onClickFriend = () => {
    setShow({
      todoShow: false,
      questShow: false,
      guildShow: false,

      profileShow: false,
      friendShow: true,
    });
    setMenu([false, false, true, false]);
  };

  const onClickProfile = () => {
    setShow({
      todoShow: false,
      questShow: false,
      guildShow: false,
      friendShow: false,
      profileShow: true,
    });
    setMenu([false, false, false, true]);
  };
  

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
        if(res.data.guild){
          setGuildId(res.data.guild.guild_id);
          console.log(guildId)
         setGuildUsers(res.data);
        }
        
        // setGuilds(res.data);
      });
  };

  

  useEffect(() => {
    getProfile(); // 유저 프로필 정보 가져오기
    onGetUserGuildInfo(); // 유저 길드 정보 가져오기
  }, []);

  return (
    <>
      {show.todoShow && <Todos user={user} />}
      {/* {show.questShow && <Quests />} */}
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

      {/* 보드 하단 버튼 리스트 */}
      <div className="BoardButton">
        <button
          className={menu[0] ? "BoardButto--active" : ''}
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
          Profile
        </button>
      </div>
    </>
  );
};

export default Board;
