import React, { useState, useEffect } from "react";
import axios from "axios";
import QuestList from "./../../../components/Quest/QuestList";
import QuestTutorial from "../../../components/Quest/QuestTutorial";
import "../../../styles/Quest.css";
import { useRef } from "react";
import api from "./../../../components/api";

const Quests = () => {
  const [quests, setQuest] = useState([]);
  const [disable, setDisabled] = useState(true);
  const [tutorial, setTutorial] = useState(false);
  const outside = useRef();
  const nickname = localStorage.getItem("nickname");

  // 퀘스트 데이터 리스트 가져오기
  const getQuest = () => {
    api.get(`/api/quest/daily/${nickname}`).then((res) => {
      console.log(res.data);
      setQuest(res.data);
    });
  };

  const handleTutorial = () => {
    setTutorial(true);
  };

  useEffect(() => {
    getQuest();
  }, []);

  return (
    <>
      <div className="Quests">
        {/* 화면 상단 버튼 그룹 */}
        <div className="QuestHeaderButton">
          <div className="QuestHeaderButton_btn1">
            <img src="dailyB.png" alt="" />
          </div>
          <div className="QuestHeaderButton_btn2">
            <img src="weeklyB.png" alt="" />
          </div>
          <button className="TutorialButton" onClick={handleTutorial}>
            TUTORIAL
          </button>
        </div>

        {/* 퀘스트 목록 출력 */}
        <div className="QuestList">
          <QuestList questList={quests} />
        </div>
        {tutorial && (
          <div
            className="Modal"
            ref={outside}
            onClick={(e) => {
              if (e.target === outside.current) {
                setTutorial(false);
              }
            }}
          >
            <QuestTutorial />
          </div>
        )}
      </div>
    </>
  );
};

export default Quests;
