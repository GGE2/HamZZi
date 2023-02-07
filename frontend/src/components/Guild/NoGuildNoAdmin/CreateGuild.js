import React, { useState } from "react";
import axios from "axios";
import Header from "./../../Header";
// import GuildList from "./../GuildList";
import GuildItem from "./../GuildItem";

const CreateGuild = ({
  onSearchGuild,
  guilds,
  setKeyword,
  onGetUserGuildInfo,
  getGuildList,
  onGetGuildAdmin,
  onGetGuilduser,
}) => {
  const nickname = localStorage.getItem("nickname");
  const [GuildName, setGuildName] = useState("");
  const [CreateFlag, setGuildFlag] = useState(false);
  const [SearchFlag, setSearchFlag] = useState(true);
  const [create_guild_menu, setCreate_guild_menu] = useState([true, false]);

  // const [keyword, setKeyword] = useState('')

  const onCreateGuild = async () => {
    if (GuildName) {
      await axios
        .post(
          `http://3.35.88.23:8080/api/guild/found?guild_name=${GuildName}&nickname=${nickname}`
        )
        .then((res) => {
          console.log("길드생성api");
          console.log(res);
          onGetUserGuildInfo();
          // getGuildList();
          onGetGuildAdmin();
          onGetGuilduser();
        })
        .catch((err) => {
          console.log(err);
        });
    }
    else
      alert('이름이 없다')
  };

  const onKeyword = (e) => {
    console.log(e.target.value);
    setGuildName(e.target.value);
  };

  const handleOnKeyPress = (e) => {
    if (e.key === "Enter") {
      console.log("enter");
      onSearchGuild(); // Enter 입력이 되면 클릭 이벤트 실행
    }
  };

  const CreateFlagfunc = () => {
    setCreate_guild_menu([true, false]);
    setGuildName('')
  };

  const SearchFlagfunc = () => {
    setCreate_guild_menu([false, true]);
    setGuildName('')
  };

  return (
    <>
      <div className="FriendHeaderButton">
        <button
          onClick={CreateFlagfunc}
          className={create_guild_menu[0] ? "CreateButton--active" : ""}
        >
          길드 생성
        </button>
        <button
          onClick={SearchFlagfunc}
          className={create_guild_menu[1] ? "CreateButton--active" : ""}
        >
          길드 찾기
        </button>
        <form onSubmit="return false;">
          {/* <input type="text" > */}
          <input
            type="text"
            placeholder="길드 이름을 입력하세요"
            value={GuildName}
            onChange={onKeyword}
            onKeyPress={handleOnKeyPress}
          />
        </form>
        {create_guild_menu[0] && (
          <button onClick={onCreateGuild}>생성하기</button>
        )}
        {create_guild_menu[1] && (
          <button onClick={onSearchGuild}>검색하기</button>
        )}
      </div>
      <div className="GuildList">
        {guilds.map((guild) => (
          <GuildItem
            key={guild.id}
            length={guilds.length}
            guild={guild}
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
