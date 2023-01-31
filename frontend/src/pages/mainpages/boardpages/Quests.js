import React, { useState } from "react";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";
<<<<<<< HEAD
import Header from "./../../../components/Header";
=======
import Header from './../../../components/Header';
>>>>>>> 862d795ade2ac4e38d20670b3253c2f34ecca8e8

const Quests = () => {
  const [startDate, setStartDate] = useState(new Date());
  return (
<<<<<<< HEAD
    <>
      <Header data={"Quests"} type={"Quests"} />
      <div className="MyBody"></div>
    </>
=======
    <Header data={'Quests'} />
    // <DatePicker selected={startDate} onChange={(date:Date) => setStartDate(date)} />
>>>>>>> 862d795ade2ac4e38d20670b3253c2f34ecca8e8
  );
};

export default Quests;
