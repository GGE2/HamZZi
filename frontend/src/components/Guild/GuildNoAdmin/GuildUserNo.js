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
      {nickname === user.nickname ? (
        <>
          <div className="imgicon">
            <img src="./guildlist/general.png" alt="" />
          </div>
          <div className="GuildUserNoNo" onClick={onLeaveGuild}>
            <div className="button_text1">
              <img src="guildlist/quitbtn.png" alt="" />
            </div>
            <div>
            길드원: {user.nickname}
            </div>
          </div>
        </>
      ) : (
        <>
          <div className="imgicon">
            <img src="./guildlist/general.png" alt="" />
          </div>
          <div>길드원: {user.nickname}</div>
        </>
      )}
    </div>
  );
};

export default GuildUserNo;
