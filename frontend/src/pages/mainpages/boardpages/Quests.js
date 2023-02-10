import React, { useState, useEffect } from "react";
import axios from "axios";
import QuestList from "./../../../components/Quest/QuestList";
import "../../../styles/Quest.css";

const Quests = () => {
  const [quests, setQuest] = useState([]);
  const [disable, setDisabled] = useState(true)

  // 퀘스트 데이터 리스트 가져오기
  const getQuest = () => {
    axios
      .get("https://jsonplaceholder.typicode.com/posts")
      .then((res) => setQuest(res.data));
  };

  useEffect(() => {
    getQuest();
  }, []);

  return (
    <>
   
 
        <div className="Quests">
        
          <div className="QuestHeaderButton">
            <div className="QuestHeaderButton_btn1"><img src="dailyB.png" alt="" /></div>
            <div className="QuestHeaderButton_btn2"><img src="weeklyB.png" alt="" /></div>
          </div>

          <div className="QuestList"> 
          <QuestList questList={quests} />
          </div>
    
      </div>
    </>
  );
};

export default Quests;
