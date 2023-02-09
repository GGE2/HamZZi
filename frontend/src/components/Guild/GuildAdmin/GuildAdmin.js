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
}) => {
  // const adminname = admin.nickname
  return (
    <>

      <div className="GuildAdmin">
        <div className="FriendHeaderButton">
          {/* <button onClick={onCreateGuild}>길드 생성</button> */}
          <button onClick={onDeleteGuild}>길드 삭제</button>
          <button onClick={onLeaveGuild}>길드 탈퇴</button>
        </div>
        {/* 길드 관리자 */}
        {/* <div className="Guild"></div> */}
        {/* 길드장: {admin.nickname} */}
        <div className="GuildAdminWrap">길드장: {admin ? admin.nickname : ""}</div>
        {/* {adminname} */}
        {/* 일반 길드원 */}
        <div className="GuildUsersWrap">
        {users
          ? users.map((user, idx) => (
              <GuildUser
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
