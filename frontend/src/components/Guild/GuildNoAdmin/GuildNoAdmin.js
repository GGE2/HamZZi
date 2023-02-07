import React from "react";
// import GuildUsersNo from "./GuildUsersNo";
import Header from './../../Header';
import GuildUserNo from './GuildUserNo';

const GuildNoAdmin = ({
  admin, // 길드 관리자
  users, // 일반 길드원
  onLeaveGuild,
}) => {
  console.log(users)
  return (
    <>

      <div className="GuildNoAdmin">
        일반 길드원
        <div className="FriendHeaderButton"><button onClick={onLeaveGuild}>길드 탈퇴</button></div>
        
        {/* <GuildUsersNo admin={admin} users={users} /> */}
        <div className="GuildAdminWrap">길드장: {admin ? admin.nickname : ""}</div>
        {/* {adminname} */}
        {/* 일반 길드원 */}
        <div className="GuildUsersWrap">
        {users
          ? users.map((user,idx) => (
              <GuildUserNo
              key={idx}
                admin={admin}
                user={user}
                
              />
            ))
          : ""}
          </div>
      </div>
    </>
  );
};

export default GuildNoAdmin;
