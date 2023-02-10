import React, { useState } from "react";

import GuildItem from "./GuildItem";
import api from './../../api';

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

  const onCreateGuild = async () => {
    if (GuildName) {
      await api
        .post(
          `/api/guild/found?guild_name=${GuildName}&nickname=${nickname}`
        )
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
    else
      alert('이름이 없다')
  };

  const onGuildName = (e) => {
    console.log(e.target.value);
    setGuildName(e.target.value);
  };

  return (
    <>
    
      <div className="onCreateGuildHeaderButton">
        
      
      <div className="inputWrapGuild div1">
      <form className="inputt">
          <input
            type="text"
            placeholder="길드 이름을 입력하세요"
            value={GuildName}
            onChange={onGuildName}
       
          />
          </form>
          </div>
        
        
        {/* <motion.button
        whileHover={{ top: 1}}
        whileTap={{ scale: 0.9 }}
        transition={{ type: "spring", stiffness: 400, damping: 17 }}
          onClick={onCreateGuild}
        > */}
       
        <div onClick={()=>onSearchGuild(GuildName)} className="searchbtn"><img src="guildlist/searchbtn.png" alt="" /></div>
        <div onClick={onCreateGuild} className="createbtn"><img src="guildlist/createbtn.png" alt="" /></div>
        
        {/* {create_guild_menu[0] && (
          <>
          <form >
          <input
            type="text"
            placeholder="길드 이름을 입력하세요"
            value={GuildName}
            onChange={onGuildName}
            onKeyPress={handleOnKeyPress}
          />
        </form>
          <button onClick={onCreateGuild}>생성하기</button>
          </>
        )}
        {create_guild_menu[1] && (
          <>
          <form >
          <input
            type="text"
            placeholder="길드 이름을 검색하세요"
            value={keyword}
            onChange={onKeyword}
            onKeyPress={handleOnKeyPress}
          />
        </form>
          <button onClick={()=> {onSearchGuild(keyword)}}>검색하기</button>
          </>
        )} */}
      </div>
      <div className="GuildList">
        <div className="GuildList_tab">
          <img src="guildlist/guildbar.png" alt="" />
        </div>
        {guilds.map((guild) => (
          <GuildItem
            key={guild.id}
            length={guilds.length}
            guildId={guild.guild_id}
            guildName={guild.guild_name}
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
