import React, { useState, useEffect } from "react";
import Header from "./../../../components/Header";
import axios from "axios";
import FriendList from './../../../components/Friend/FriendList';
import '../../../styles/Friend.css'

const Friends = () => {
  const [friends, setFriend] = useState([]);
  const [disable, setDisabled] = useState(true);

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
          <button>친구목록</button>
          <button>받은신청</button>
          <button>보낸신청</button>
        </div>
        <FriendList friends={friends}/>
      

        {/* <div className="QuestList"></div>  */}
        {/* <QuestList questList={quests} /> */}
        {/* </div> */}
      </div>
    </>
  );
};

export default Friends;
