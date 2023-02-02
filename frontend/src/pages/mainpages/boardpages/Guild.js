import React, { useState, useEffect } from "react";
import Header from "./../../../components/Header";
import axios from "axios";
import GuildPersons from "./../../../components/Guild/GuildPersons";
import '../../../styles/Guild.css'

const Guild = () => {
  const [persons, setPerson] = useState([]);
  const [isGuild, setIsGuild] = useState(false);

  // 퀘스트 데이터 리스트 가져오기
  const getPerson = () => {
    axios.get("https://jsonplaceholder.typicode.com/users").then((res) => {
      setPerson(res.data);
      console.log(persons);
    });
  };

  useEffect(() => {
    getPerson();
  }, []);

  return (
    <>
      <Header data={"Guild"} type={"Guild"} />
      <div className="MyBody">
        <div className="GuildHeader">레벨: 길드 이름
        <button>rtc</button></div>
        <GuildPersons persons={persons} />
      </div>
    </>
  );
};

export default Guild;
