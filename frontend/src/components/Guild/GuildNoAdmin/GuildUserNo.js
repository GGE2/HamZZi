import React from "react";

// 일반 길드원
const GuildUserNo = ({
  admin, // 길드 관리자
  user, // 일반 길드원
  length,
  nickname,
  onLeaveGuild,
}) => {
  
  return (
    <div className="GuildUserNo">
        {nickname === user.nickname ?<> <div>
        <img src="./levellogo/lvlogo (2).png" alt="" />
      </div>
      
      <div><button onClick={onLeaveGuild}>탈퇴</button> 길드원: {user.nickname}</div></>  :
      <><div>
        <img src="./levellogo/lvlogo (2).png" alt="" />
      </div>
      <div>길드원: {user.nickname}</div></>}
      
    </div>
  );
};

export default GuildUserNo;
