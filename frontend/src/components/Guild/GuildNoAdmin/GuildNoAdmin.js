import React from "react";
// import GuildUsersNo from "./GuildUsersNo";

import GuildUserNo from "./GuildUserNo";

const GuildNoAdmin = ({
  admin, // 길드 관리자
  users, // 일반 길드원
  onLeaveGuild,
  guildName
}) => {
  const guildname = guildName
  const nickname = localStorage.getItem("nickname");
  return (
    <>
      <div className="GuildNoAdmin">
        <div className="GuildNoAdminHeader">
          <div className="titless">{(guildName)}</div>
          <div className="button-text">
            
            STUDY ROOM
          </div>
        </div>

  

        {/* <GuildUsersNo admin={admin} users={users} /> */}
        <div className="GuildAdminWrap">
        <div className="GuildAdminWrap2">
            <div>
          <img src="./levellogo/lvlogo (4).png" alt="" /></div>
          <div>길드장: {admin ? admin.nickname : ""}
          </div>
        </div>
        </div>
        {/* {adminname} */}
        {/* 일반 길드원 */}
        <div className="GuildUsersWrap">
          
          {users
            ? users.map((user, idx) => (
                <GuildUserNo key={idx} admin={admin} user={user} nickname={nickname} onLeaveGuild={onLeaveGuild}/>
              ))
            : ""}
        </div>
      </div>
    </>
  );
};

export default GuildNoAdmin;
