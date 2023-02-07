import React from "react";
import axios from "axios";

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
    await axios
      .put(
        `http://3.35.88.23:8080/api/guild/join?guild_id=${guild.guild_id}&nickname=${nickname}`
      )
      .then((res) => {
        console.log("길드가입api");
        console.log(res);
        onGetUserGuildInfo(); // 유저 길드 정보 업데이트
        onGetGuildAdmin(res.data.guild_id)
        onGetGuilduser(res.data.guild_id)
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
