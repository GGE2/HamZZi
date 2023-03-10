import React, { useState, useEffect } from "react";
import axios from "axios";
import "../../../styles/Guild.css";
import CreateGuild from "./../../../components/Guild/NoGuildNoAdmin/CreateGuild";
import GuildNoAdmin from "./../../../components/Guild/GuildNoAdmin/GuildNoAdmin";
import GuildAdmin from "../../../components/Guild/GuildAdmin/GuildAdmin";
import api from "./../../../components/api";
import { motion } from "framer-motion";
import LoadingModal from "./../../../components/LoadingModal";

const Guild = ({
  guildUsers,
  guildId,
  setGuildUsers,
  onGetUserGuildInfo,
  guildName,
}) => {
  const [admin, setAdmin] = useState();
  const [users, setUsers] = useState();

  const [guilds, setGuilds] = useState([]);
  const [loading, setLoading] = useState(true);
  const variants = {
    hidden: { opacity: 0 },
    visibie: { opacity: 1 },
  };
  useEffect(() => {
    setTimeout(() => {
      setLoading(false);
    }, 700);
  });
  const nickname = localStorage.getItem("nickname");

  // 길드 리스트 가져오기
  const getGuildList = async () => {
    await api.get("/api/guild/list").then((res) => {
      // console.log("길드리스트 api");
      // console.log(res);
      setGuilds(res.data);
    });
  };

  // 특정 길드 검색 api
  const onSearchGuild = async (keyword) => {
    await api
      .get(`/api/guild/list/search?guild_name=${keyword}`)
      .then((res) => {
        // console.log("특정 길드 검색 api");
        // console.log(res);
        setGuilds(res.data);
        // setLoading(false)
      });
  };

  // 길드 세부정보 api
  const onGetGuildDetail = async () => {
    await api.get(`/api/guild/detail?guild_id=${guildId}`).then((res) => {
      // console.log("길드 세부정보 api");
      // console.log(res);
      // setGuilds(res.data);
      // setLoading(false)
    });
  };

  // 길드 세부정보 -관리자 api
  const onGetGuildAdmin = async (id) => {
    await api.get(`/api/guild/detail/admin?guild_id=${id}`).then((res) => {
      // console.log("길드 세부정보 -관리자 api");
      // console.log(res.data[0]);
      setAdmin(res.data[0]);
    });
  };
  // 길드 세부정보 -사 용자 api
  const onGetGuilduser = async (id) => {
    await api.get(`/api/guild/detail/user?guild_id=${id}`).then((res) => {
      // console.log("길드 세부정보 -사용자 api");
      // console.log(res.data);
      setUsers(res.data);
    });
  };

  // 길드 탈퇴 api
  const onLeaveGuild = async () => {
    await api
      .get(`/api/guild/leave?guild_id=${guildId}&nickname=${nickname}`)
      .then((res) => {
        // console.log("길드 탈퇴 api");
        // console.log(res);
        getGuildList();
        setGuildUsers({
          admin: false,
          guild: null,
          nickname: nickname,
        });
      });
  };

  // 길드 0 길드장 0 전용 api
  // 관리자 임명 api
  const onGrantAdmin = async (admin, user, id) => {
    await api
      .put(
        `/api/guild/admin/grant?nickname_admin=${admin}&nickname_granted=${user}`
      )
      .then((res) => {
        // console.log("관리자 임명 api");
        // console.log(res);
        onGetUserGuildInfo(admin);
        getGuildList();
        setGuildUsers({
          admin: false,
          guild: true,
          nickname: nickname,
        });
        onGetGuildAdmin(id);
        onGetGuilduser(id);
      });
  };

  // 길드 삭제 api
  const onDeleteGuild = async () => {
    await api
      .delete(`/api/guild/delete?guild_id=${guildId}&nickname=${nickname}`)
      .then((res) => {
        // console.log("길드 삭제 api");
        // console.log(res);
        getGuildList();
        setGuildUsers({
          admin: false,
          guild: null,
          nickname: nickname,
        });
        setLoading(false);
      });
  };

  // 길드 강퇴 api
  const onKickUser = async (admin, user, id) => {
    await api
      .put(
        `/api/guild/kick?nickname_admin=${admin}&nickname_user=${user}&guild_id=${id}`
      )
      .then((res) => {
        // console.log("길드 강퇴 api");
        // console.log(res);
        getGuildList();
        onGetGuildAdmin(id);
        onGetGuilduser(id);
        setLoading(false);
      });
  };

  useEffect(() => {
    setLoading(true);
    onGetUserGuildInfo();
    getGuildList();
    onGetGuildAdmin(guildId); // 길드 관리자 가져오기
    onGetGuilduser(guildId); // 길드원 리스트 가져오기
  }, []);

  return (
    <>
      <div className="DressRoom2">
        <div className="postit2"></div>
        {loading ? (
          <LoadingModal />
        ) : (
          <motion.div
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ y: -100, opacity: 0 }}
            transition={{ duration: 1 }}
            variants={variants}
          >
            <div className="MyBody">
              {guildUsers.admin === false && guildUsers.guild === null && (
                <CreateGuild
                  guilds={guilds}
                  onSearchGuild={onSearchGuild}
                  // setKeyword={setKeyword}
                  onGetUserGuildInfo={onGetUserGuildInfo}
                  getGuildList={getGuildList}
                  onGetGuildAdmin={onGetGuildAdmin}
                  onGetGuilduser={onGetGuilduser}
                  admin={admin}
                />
              )}
              {/* 길드 o 길드장 x */}

              {guildUsers.admin === false && guildUsers.guild && (
                <GuildNoAdmin
                  admin={admin}
                  users={users}
                  onLeaveGuild={onLeaveGuild}
                  guildName={guildName}
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
                  guildName={guildName}
                />
              )}
            </div>
          </motion.div>
        )}
      </div>
    </>
  );
};

export default Guild;
