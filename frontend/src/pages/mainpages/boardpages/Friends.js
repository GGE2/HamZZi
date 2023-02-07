import React, { useState, useEffect } from "react";
import Header from "./../../../components/Header";
import axios from "axios";
import '../../../styles/Friend.css'

import FriendList from './../../../components/Friend/FriendList';
import FriendGetList from './../../../components/Friend/FriendGetList';
import FriendSendList from './../../../components/Friend/FriendSendList';

const Friends = () => {
  const [friends, setFriend] = useState([]);
  const [disable, setDisabled] = useState(true);
  const [friendmenu, setFriendmenu] = useState([true, false, false]);

  // 퀘스트 데이터 리스트 가져오기
  const getFriend = () => {
    axios
      .get("https://jsonplaceholder.typicode.com/users")
      .then((res) => {setFriend(res.data) 
        console.log(friends)});
  };

  useEffect(() => {
    getFriend();
  }, []);

  return (
    <>
      <Header data={"Friends"} type={"Friends"} />
      <div className="MyBody">
        {/* <div className="Quests"> */}
        <div className="FriendHeaderButton">
          <button className={friendmenu[0] ? "friendbutton__active": ""} onClick={()=>setFriendmenu([true, false, false])}>친구목록</button>
          <button className={friendmenu[1] ? "friendbutton__active": ""} onClick={()=>setFriendmenu([false, true, false])}>받은신청</button>
          <button className={friendmenu[2] ? "friendbutton__active": ""} onClick={()=>setFriendmenu([false, false, true])}>보낸신청</button>
        </div>
        {/* 버튼 누른거에 따라서 컴포넌트 선택 */}
        {friendmenu[0] && <FriendList friends={friends}/>}
        {friendmenu[1] && <FriendGetList friends={friends}/>}
        {friendmenu[2] && <FriendSendList friends={friends}/>}
      

        {/* <div className="QuestList"></div>  */}
        {/* <QuestList questList={quests} /> */}
        {/* </div> */}
      </div>
    </>
  );
};

export default Friends;
