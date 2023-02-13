import React from "react";
import QuestItem from "./QuestItem";

const QuestList = ({ questList }) => {
  return (
    <div className="QuestList">
      {questList.map((quest) => (
        <QuestItem
          key={quest.quest.quest_id}
          content={quest.quest.content}
          completed={quest.ischeck}
        />
      ))}
    </div>
  );
};

QuestList.defaultProps = {
  questList: [],
};

export default QuestList;
