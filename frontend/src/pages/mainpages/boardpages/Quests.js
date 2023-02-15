import React, { useState, useEffect } from "react";
import axios from "axios";
import QuestList from "./../../../components/Quest/QuestList";
import "../../../styles/Quest.css";
import { useRef } from "react";
import api from "./../../../components/api";

import { motion } from "framer-motion";
import LoadingModal from "./../../../components/LoadingModal";

const Quests = () => {
  const nickname = localStorage.getItem("nickname");
  const [dailyQuests, setDailyQuest] = useState([]);
  const [weeklyQuests, setWeeklyQuest] = useState([]);
  const [show, setShow] = useState({
    dailyShow: true,
    weeklyShow: false,
  });
  const [loading, setLoading] = useState(true);
  const variants = {
    hidden: { opacity: 0 },
    visibie: { opacity: 1 },
  };
  useEffect(() => {
    setTimeout(() => {
      setLoading(false);
    }, 700);
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
      {loading ? (
        <LoadingModal />
      ) :
      <motion.div
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        exit={{ y: -100, opacity: 0 }}
        transition={{ duration: 1 }}
        variants={variants}
      >
      <div className="Quests">
        {/* 화면 상단 버튼 그룹 */}
        <div className="QuestHeaderButton">
          <div className="QuestHeaderButton_btn1" onClick={handleDaily}>
            <img src="dailyB.png" alt="" />
          </div>
          <div className="QuestHeaderButton_btn2" onClick={handleWeekly}>
            <img src="weeklyB.png" alt="" />
          </div>
         
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
          </div>
        </motion.div>
      }
    </>
  );
};

export default Quests;
