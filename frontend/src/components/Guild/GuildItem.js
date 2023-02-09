import React from "react";
import axios from "axios";
import api from './../api';

const GuildItem = ({
  guild,
  length,
  onGetUserGuildInfo,
  onGetGuilduser,
  onGetGuildAdmin,
}) => {
  const nickname = localStorage.getItem("nickname");

  // 길드 가입 api
  const onJoinGuild = async () => {
    await api
      .put(
        `/api/guild/join?guild_id=${guild.guild_id}&nickname=${nickname}`
      )
      .then((res) => {
        console.log("길드가입api");
        console.log(res);
        onGetUserGuildInfo(); // 유저 길드 정보 업데이트
        onGetGuildAdmin(guild.guild_id)
        onGetGuilduser(guild.guild_id)
      });
  };

  return (
    
    <div className="GuildItem">
      {guild.guild_name}
      <button onClick={onJoinGuild}>가입하기</button>
    </div>
  );
};

export default GuildItem;
