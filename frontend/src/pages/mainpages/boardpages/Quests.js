import React, { useState, useEffect } from "react";
import Header from "./../../../components/Header";
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
      <Header data={"Quests"} type={"Quests"} />
      <div className="MyBody">
        {/* <div className="Quests"> */}
<<<<<<< HEAD
        
          <div className="QuestHeaderButton">
=======
          <div className="QuestButton">
>>>>>>> d71fbd50837b97bd096f25692dba0e251672d352
            <button >DAILY</button>
            <button>WEEKLY</button>
          </div>

          {/* <div className="QuestList"></div>  */}
          <QuestList questList={quests} />
        {/* </div> */}
      </div>
    </>
  );
};

export default Quests;
