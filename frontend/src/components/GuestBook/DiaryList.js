import React from 'react';
import DiaryItem from './DiaryItem';

const DiaryList = ({diaryList, onDelete, onEdit}) => {
    console.log(diaryList)
    return (
        <div className='DiaryList'>
            <h2>일기 리스트</h2>
            <h4>{diaryList.length}개의 일기가 있다.</h4>
            <div>
                {diaryList.map((it)=> (
                    <DiaryItem key={it.id} {...it} onDelete={onDelete} onEdit={onEdit}/>
                ))}
            </div>
        </div>
    );
};

DiaryList.defaultProps = {
    diaryList: [],
}

export default DiaryList;