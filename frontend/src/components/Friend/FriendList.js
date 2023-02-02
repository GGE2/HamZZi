import React from 'react';
import FriendItem from './FriendItem';


const FriendList = ({friends}) => {
    return (
        <div className='FriendList'>
            {friends.map((friend) => <FriendItem key={friend.id} friend={friend} length={friends.length} />)}
        </div>
    );
};

export default FriendList;