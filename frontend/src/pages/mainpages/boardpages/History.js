import React from "react";
import api from "../../../components/api";
import { useEffect, useState } from "react";
// import History from './History';
import HistoryItem from "./../../../components/HistoryItem";
import { motion } from "framer-motion";
import LoadingModal from "./../../../components/LoadingModal";

const History = () => {
  const nickname = localStorage.getItem("nickname");
  const [trophys, setTrophy] = useState([]);

  const onGetTrophy = () => {
    api.get(`/api/pet/trophy/${nickname}`).then((res) => {
      // console.log(res);
      setTrophy(res.data);
    });
  };

  useEffect(() => {
    onGetTrophy();
  }, []);

  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setTimeout(() => {
      setLoading(false);
    }, 700);
  });

  const variants = {
    hidden: { opacity: 0 },
    visibie: { opacity: 1 },
  };

  return (
    <>
      <div className="DressRoom">
        <div className="postit5"></div>
        {loading ? (
          <>
            {" "}
            <LoadingModal />
            {/* <div className="postit"></div> */}
          </>
        ) : (
          <motion.div
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ y: -100, opacity: 0 }}
            transition={{ duration: 1 }}
            variants={variants}
          >
            <div className="postit5"></div>
            <div className="HistoryHeader">
              <div> History</div>
            </div>
            <div className="History">
              {trophys.length === 0 ? (
                <div className="historyflex">
                  <div className="HistoryItem">
                    <img src={`shadowA.png`} alt="" />{" "}
                  </div>
                </div>
              ) : null}
              {trophys.map((trophy) => (
                <HistoryItem
                  key={trophy.petInfo_id}
                  type={trophy.type}
                  pet_name={trophy.pet.pet_name}
                  graduate_date={trophy.pet.graduate_date}
                  length={trophys.length}
                />
              ))}
            </div>
          </motion.div>
        )}
      </div>
    </>
  );
};

export default History;
