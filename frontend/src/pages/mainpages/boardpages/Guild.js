import React, { useState, useEffect } from "react";
import Header from "./../../../components/Header";
import axios from "axios";
import "../../../styles/Guild.css";
import CreateGuild from "./../../../components/Guild/NoGuildNoAdmin/CreateGuild";
import GuildNoAdmin from "./../../../components/Guild/GuildNoAdmin/GuildNoAdmin";
import GuildAdmin from "../../../components/Guild/GuildAdmin/GuildAdmin";

const Guild = ({
  user,
  guildUsers,
  guildId,
  setGuildUsers,
  onGetUserGuildInfo,
}) => {
  const [admin, setAdmin] = useState();
  const [users, setUsers] = useState();

  const [guilds, setGuilds] = useState([]);
  const [keyword, setKeyword] = useState("");
  const [loading, setLoading] = useState(null)
  const nickname = localStorage.getItem("nickname");

  // console.log("유저");
  // console.log(user);
  // 길드 리스트 가져오기
  const getGuildList = async () => {
    await axios.get("http://3.35.88.23:8080/api/guild/list").then((res) => {
      console.log("길드리스트 api");
      console.log(res);
      setGuilds(res.data);
    });
  };

  // 특정 길드 검색 api
  const onSearchGuild = async () => {
    await axios
      .get(`http://3.35.88.23:8080/api/guild/list/search?guild_name=${keyword}`)
      .then((res) => {
        console.log("특정 길드 검색 api");
        console.log(res);
        setGuilds(res.data);
        setLoading(false)
      });
  };

  // 길드 세부정보 api
  const onGetGuildDetail = async () => {
    await axios
      .get(`http://3.35.88.23:8080/api/guild/detail?guild_id=${guildId}`)
      .then((res) => {
        console.log("길드 세부정보 api");
        console.log(res);
        // setGuilds(res.data);
        setLoading(false)
      });
  };

    // 길드 세부정보 -관리자 api
    const onGetGuildAdmin = async (id) => {
      await axios
        .get(`http://3.35.88.23:8080/api/guild/detail/admin?guild_id=${id}`)
        .then((res) => {
          console.log("길드 세부정보 -관리자 api");
          console.log(res.data[0]);
          setAdmin(res.data[0]);
          // setGuilds(res.data);
          setLoading(false)
        });
    };
    // 길드 세부정보 -사 용자 api
    const onGetGuilduser = async (id) => {
      await axios
        .get(`http://3.35.88.23:8080/api/guild/detail/user?guild_id=${id}`)
        .then((res) => {
          console.log("길드 세부정보 -사용자 api");
          console.log(res.data);
          setUsers(res.data);
          // setGuilds(res.data);
          setLoading(false)
        });
    };



  // 길드 탈퇴 api
  const onLeaveGuild = async () => {
    await axios
      .get(
        `http://3.35.88.23:8080/api/guild/leave?guild_id=${guildId}&nickname=${nickname}`
      )
      .then((res) => {
        console.log("길드 탈퇴 api");
        console.log(res);
        setGuildUsers({
          admin: false,
          guild: null,
          nickname: nickname,
        });
        setLoading(false)
        // setGuilds(res.data);
      });
  };

  // 길드 0 길드장 0 전용 api
  // 관리자 임명 api
  const onGrantAdmin = async (admin, user, id) => {
    await axios
      .put(
        `http://3.35.88.23:8080/api/guild/admin/grant?nickname_admin=${admin}&nickname_granted=${user}`
      )
      .then((res) => {
        console.log("관리자 임명 api");
        console.log(res);
        // setGuilds(res.data);
        onGetUserGuildInfo();
        getGuildList();
        onGetGuildAdmin(id);
        onGetGuilduser(id);
        setLoading(false)
      });
  };
  // 길드 삭제 api
  const onDeleteGuild = async () => {
    await axios
      .delete(
        `http://3.35.88.23:8080/api/guild/delete?guild_id=${guildId}&nickname=${nickname}`
      )
      .then((res) => {
        console.log("길드 삭제 api");
        console.log(res);
        // setGuilds(res.data);
        getGuildList();
        setGuildUsers({
          admin: false,
          guild: null,
          nickname: nickname,
        });
        setLoading(false)
      });
  };

  // 길드 강퇴 api
  const onKickUser = async (admin, user, id) => {
    await axios
      .put(
        `http://3.35.88.23:8080/api/guild/kick?nickname_admin=${admin}&nickname_user=${user}`
      )
      .then((res) => {
        console.log("길드 강퇴 api");
        console.log(res);
        onGetGuildAdmin(id);
        onGetGuilduser(id);
        // setGuilds(res.data);
        setLoading(false)
      });
  };

  useEffect(() => {
    setLoading(true)
    onGetUserGuildInfo();
    getGuildList();

    onGetGuildAdmin(guildId); // 길드 관리자 가져오기
    onGetGuilduser(guildId); // 길드원 리스트 가져오기

    // onGetGuildDetail();
    // onLeaveGuild()

    // onSearchGuild()
  }, []);

  return (
    <>
      <Header data={"길드"} type={"Guild"} />
      <div className="MyBody">
        {/* <CreateGuild
            guilds={guilds}
            onSearchGuild={onSearchGuild}
            setKeyword={setKeyword}
          /> */}
        {/* 길드 x 길드장 x */}

        {guildUsers.admin === false && guildUsers.guild === null && (
          <CreateGuild
            guilds={guilds}
            onSearchGuild={onSearchGuild}
            setKeyword={setKeyword}
            onGetUserGuildInfo={onGetUserGuildInfo}
            getGuildList={getGuildList}
            onGetGuildAdmin={onGetGuildAdmin}
            onGetGuilduser={onGetGuilduser}
          />
        )}
        {/* 길드 o 길드장 x */}

        {guildUsers.admin === false && guildUsers.guild && (
          <GuildNoAdmin
            admin={admin}
            users={users}
            onLeaveGuild={onLeaveGuild}
          />
        )}
        {/* 길드 o 길드장 o */}
        {guildUsers.admin === true && guildUsers.guild && (
          <GuildAdmin
            admin={admin}
            users={users}
            guildId={guildId}
            onDeleteGuild={onDeleteGuild}
            onGrantAdmin={onGrantAdmin}
            onKickUser={onKickUser}
            onLeaveGuild={onLeaveGuild}
          />
        )}

        {/* {user.isGuild} */}
        {/* <Header data={"Guild"} type={"Guild"} />
      <div className="MyBody">
        <div className="GuildHeaderButton">길드 이름
        <button>rtc</button></div>
        <GuildPersons persons={persons} />
      </div> */}
      </div>
    </>
  );
};

export default Guild;
