<<<<<<< HEAD
import React, { useState } from "react";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";
import Header from './../../../components/Header';

const Quests = () => {
  const [startDate, setStartDate] = useState(new Date());
  return (
    <><Header data={'Quests'} type={'Quests'} />
    <div className="MyBody"></div></>
    
  );
=======
import React from "react";

const Quests = () => {
  return <div>Quests</div>;
>>>>>>> feature/mobile/homepage
};

export default Quests;
