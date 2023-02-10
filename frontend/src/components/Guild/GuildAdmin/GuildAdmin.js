import React from "react";
import GuildUser from "./GuildUser";

const GuildAdmin = ({
  admin, // 길드 관리자
  users, // 일반 길드원 리스트
  onDeleteGuild, // 길드 삭제
  onGrantAdmin, // 길드관리자 넘기기
  onKickUser, // 길드원 추방
  onLeaveGuild, // 길드 탈퇴
  guildId, // 길드 id
  guildName, // 길드 이름
}) => {
  const nickname = localStorage.getItem("nickname");
  return (
    <>
      <div className="GuildNoAdmin">
        <div className="GuildNoAdminHeader">
          <div className="titless">{(guildName)}</div>
          <div className="button_text">
            
        <img src="guildlist/studyroombtn.png" alt="" />
          </div>
        </div>
        {/* 길드 관리자 */}
        {/* <div className="Guild"></div> */}
        {/* 길드장: {admin.nickname} */}
        <div className="GuildAdminWrap">
          <div className="GuildAdminWrap2">
            <div className="GuildAdminWrap3">
              <img src="./guildlist/master.png" alt="" />
            </div>
            <div className="GuildUserNoNo">
              <div className="button_text1" onClick={onDeleteGuild}>
                <img src="guildlist/deletebtn.png" alt="" />
              
              </div>
              길드장: {admin ? admin.nickname : ""}
            </div>
          </div>
        </div>
        {/* {adminname} */}
        {/* 일반 길드원 */}
        <div className="GuildUsersWrap">
          {users
            ? users.map((user, idx) => (
                <GuildUser
                  nickname={nickname}
                  key={idx}
                  admin={admin}
                  user={user}
                  onGrantAdmin={onGrantAdmin}
                  onKickUser={onKickUser}
                  onLeaveGuild={onLeaveGuild}
                  guildId={guildId}
                />
              ))
            : ""}
        </div>
      </div>
    </>
  );
};

export default GuildAdmin;
