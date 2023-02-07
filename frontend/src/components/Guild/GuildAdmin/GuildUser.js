import React from "react";

// 길드 관리자
const GuildUser = ({
  user,
  length,
  admin, // 길드 관리자
  onGrantAdmin, // 길드관리자 넘기기
  onKickUser, // 길드원 추방
  guildId, // 길드 id
}) => {
  console.log(123)
  console.log(user);
  console.log(admin)
  return (
    <div className="GuildUser">
      {user.nickname} <button onClick={()=>onKickUser(admin.nickname, user.nickname, guildId)}>추방</button>
      <button onClick={()=>onGrantAdmin(admin.nickname, user.nickname, guildId)}>길드장 임명</button>
    </div>
  );
};

export default GuildUser;
