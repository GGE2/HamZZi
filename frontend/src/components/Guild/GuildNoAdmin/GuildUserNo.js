import React from 'react';

// 일반 길드원
const GuildUserNo = ({
    admin, // 길드 관리자
  user, // 일반 길드원
  length
}) => {
    return (
        <div className='GuildUserNo'>
            {user.nickname}
        </div>
    );
};

export default GuildUserNo;