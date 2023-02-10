import React, {useState, useEffect} from "react";
import axios from "axios";
import api from "../../api";

const GuildItem = ({
  guildId,
  length,
  onGetUserGuildInfo,
  onGetGuilduser,
  onGetGuildAdmin,
  guildName,

}) => {
  const nickname = localStorage.getItem("nickname");
  // const [admin, setAdmin] = useState
  console.log(123)
// console.log(admin)

  // 길드 가입 api
  const onJoinGuild = async () => {
    await api
      .put(`/api/guild/join?guild_id=${guildId}&nickname=${nickname}`)
      .then((res) => {
        console.log("길드가입api");
        console.log(res);
        onGetUserGuildInfo(nickname); // 유저 길드 정보 업데이트
        onGetGuildAdmin(guildId);
        onGetGuilduser(guildId);
      });
  };

  return (
    <>
      <div className="GuildItem">
        <div>{guildName}</div>
        <div>강교철~</div>
        <div>{length}</div>
        <button onClick={onJoinGuild}>가입하기</button>
      </div>
      
    </>
  );
};

export default GuildItem;
