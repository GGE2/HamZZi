import React from 'react';
import QuestItem from './QuestItem';

const QuestList = ({questList}) => {
    return (
        <div className='QuestList'>
            {questList.map((quest)=><QuestItem key={quest.id} content={quest.body} isCheck/>)}
        </div>
    );
};

QuestList.defaultProps = {
    questList: [],
}

export default QuestList;