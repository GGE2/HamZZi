import React, { useState, useRef } from "react";

import GuildItem from "./GuildItem";
import api from "./../../api";

import { motion } from "framer-motion";

const CreateGuild = ({
  onSearchGuild,
  guilds,
  // setKeyword,
  onGetUserGuildInfo,
  getGuildList,
  onGetGuildAdmin,
  onGetGuilduser,
  admin,
}) => {
  const nickname = localStorage.getItem("nickname");
  const [GuildName, setGuildName] = useState("");
  const textRef = useRef();

  const onCreateGuild = async () => {
    if (GuildName.length < 1) {
      textRef.current.focus();
      return;
    }
    if (GuildName) {
      await api
        .post(`/api/guild/found?guild_name=${GuildName}&nickname=${nickname}`)
        .then((res) => {
          console.log("길드생성api");
          console.log(res);
          onGetUserGuildInfo(nickname);
          // getGuildList();
          onGetGuildAdmin(res.data.guild_id);
          onGetGuilduser(res.data.guild_id);
        })
        .catch((err) => {
          console.log(err);
        });
    }
  };

  const onSearchG = () => {
    if (GuildName.length < 1) {
      textRef.current.focus();
      return;
    }
    onSearchGuild(GuildName);
  };

  const onGuildName = (e) => {
    console.log(e.target.value);
    setGuildName(e.target.value);
  };

  return (
    <>
      <div className="onCreateGuildHeaderButton">
        <div className="inputWrapGuild div1">
          <form className="inputt" onSubmit={onSearchG}>
            <input
              type="text"
              placeholder="길드 이름을 입력하세요"
              value={GuildName}
              onChange={onGuildName}
              ref={textRef}
            />
          </form>
        </div>

        <div onClick={onSearchG} className="searchbtn">
          <img src="guildlist/searchbtn.png" alt="" />
        </div>
        <div onClick={onCreateGuild} className="createbtn">
          <img src="guildlist/createbtn.png" alt="" />
        </div>
      </div>
      <div className="GuildList">
        <div className="GuildList_tab">
          <img src="guildlist/guildbar.png" alt="" />
        </div>
        {guilds.map((guild) => (
          <GuildItem
            key={guild.id}
            length={guild.personnel}
            guildId={guild.guild_id}
            guildName={guild.guild_name}
            guildAdmin={guild.admin_nickname}
            onGetGuildAdmin={onGetGuildAdmin}
            onGetGuilduser={onGetGuilduser}
            onGetUserGuildInfo={onGetUserGuildInfo}
          />
        ))}
      </div>
    </>
  );
};

export default CreateGuild;
