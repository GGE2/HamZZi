import React from "react";
import GuildItem from "./GuildItem";

const GuildList = ({ guilds, onGetUserGuildInfo, onGetGuildAdmin, onGetGuilduser }) => {
  return (
    <div className="GuildList">
      {guilds.map((guild) => (
        <GuildItem
          key={guild.id}
          onGetUserGuildInfo={onGetUserGuildInfo}
          guild={guild}
          length={guilds.length}
          onGetGuildAdmin={onGetGuildAdmin}
          onGetGuilduser={onGetGuilduser}
        />
      ))}
    </div>
  );
};

export default GuildList;
