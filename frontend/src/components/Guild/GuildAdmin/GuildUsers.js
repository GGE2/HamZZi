import React from 'react';
import GuildUser from './GuildUser';

const GuildUsers = ({
    admin, // 길드 관리자
  users, // 일반 길드원
  onGrantAdmin, // 길드관리자 넘기기
  onKickUser, // 길드원 추방
}) => {
    return (
        <div className='GuildList'> 
            {users.map((user) => <GuildUser key={user.id} user={user} length={users.length} admin={admin} onGrantAdmin={onGrantAdmin} onKickUser={onKickUser}/>)}
            
        </div>
    );
};

export default GuildUsers;