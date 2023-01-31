import React from 'react';
import QuestItem from './QuestItem';

const QuestList = ({questList}) => {
    return (
        <div className='QuestList'>
            {questList.map((quest)=><QuestItem content={quest.body} isCheck/>)}
        </div>
    );
};

export default QuestList;