import React, { useState, useEffect } from "react";
import axios from "axios";
import QuestList from "./../../../components/Quest/QuestList";
import QuestTutorial from "../../../components/Quest/QuestTutorial";
import "../../../styles/Quest.css";
import { useRef } from "react";
import api from "./../../../components/api";

const Quests = () => {
  const nickname = localStorage.getItem("nickname");
  const [dailyQuests, setDailyQuest] = useState([]);
  const [weeklyQuests, setWeeklyQuest] = useState([]);
  const [show, setShow] = useState({
    dailyShow: true,
    weeklyShow: false,
  });
  const [tutorial, setTutorial] = useState(false);
  const outside = useRef();

  const handleDaily = () => {
    setShow({
      dailyShow: true,
      weeklyShow: false,
    });
  };

  const handleWeekly = () => {
    setShow({
      dailyShow: false,
      weeklyShow: true,
    });
  };

  // 퀘스트 데이터 리스트 가져오기
  const getDailyQuest = () => {
    api.get(`/api/quest/daily/${nickname}`).then((res) => {
      console.log(res.data);
      setDailyQuest(res.data);
    });
  };

  const getWeeklyQuest = () => {
    api.get(`/api/quest/weekly/${nickname}`).then((res) => {
      console.log(res.data);
      setWeeklyQuest(res.data);
    });
  };

  const handleTutorial = () => {
    setTutorial(true);
  };

  useEffect(() => {
    getDailyQuest();
    getWeeklyQuest();
  }, []);

  return (
    <>
      <div className="Quests">
        {/* 화면 상단 버튼 그룹 */}
        <div className="QuestHeaderButton">
          <div className="QuestHeaderButton_btn1" onClick={handleDaily}>
            <img src="dailyB.png" alt="" />
          </div>
          <div className="QuestHeaderButton_btn2" onClick={handleWeekly}>
            <img src="weeklyB.png" alt="" />
          </div>
          <button className="TutorialButton" onClick={handleTutorial}>
            TUTORIAL
          </button>
        </div>

        {/* 퀘스트 목록 출력 */}
        {show.dailyShow && (
          <div className="QuestList">
            <QuestList questList={dailyQuests} />
          </div>
        )}

        {show.weeklyShow && (
          <div className="QuestList">
            <QuestList questList={weeklyQuests} />
          </div>
        )}

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
