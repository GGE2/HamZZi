import React, { useState } from "react";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";
import Header from './../../../components/Header';

const Quests = () => {
  const [startDate, setStartDate] = useState(new Date());
  return (
    <Header data={'Quests'} />
    // <DatePicker selected={startDate} onChange={(date:Date) => setStartDate(date)} />
  );
};

export default Quests;
