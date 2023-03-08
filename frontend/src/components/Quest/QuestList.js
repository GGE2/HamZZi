import React from "react";
import QuestItem from "./QuestItem";

const QuestList = ({ questList }) => {
  return (
    <div className="QuestList">
      {questList.map((quest) => (
        <QuestItem
          key={quest.quest.quest_id}
          questId={quest.quest.quest_id}
          point={quest.quest.point}
          content={quest.quest.content}
          completed={true}
        />
      ))}
    </div>
  );
};

QuestList.defaultProps = {
  questList: [],
};

export default QuestList;
