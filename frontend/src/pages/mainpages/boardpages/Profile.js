import React, {useState, useRef} from "react";
import { useSelector } from "react-redux";
import { selectCurrentUser } from "../../../authSlice";
import Header from "./../../../components/Header";
import { GiAchievement } from "react-icons/gi";
import DiaryEditor from './../../../components/GuestBook/DiaryEditor';
import DiaryList from './../../../components/GuestBook/DiaryList';

const Profile = () => {
  const user = useSelector(selectCurrentUser);

  // 방명록 리스트
  const [data, setData] = useState([]);

  // 방명록 id
  const dataId = useRef(0)

  // 방명록 생성
  const onCreate = (author, content) => {
    const created_date = new Date().getTime()
    const newItem = {
      author,
      content,
      created_date,
      id: dataId.current
    };
    dataId.current += 1
    setData([newItem, ...data])
  }

  // 방명록 삭제
  const onDelete = (targetId) => {
    const newDiaryList = data.filter(
      (it) => it.id !== targetId
    )
    setData(newDiaryList)
  }

  // 방명록 수정
  const onEdit = (targetId, newContent) => {
    setData(
      data.map((it) =>
        it.id === targetId ? { ...it, content: newContent } : it
      )
    );
  };

  return (
    <>
      <Header data={"MYPAGE"} type={"profile"} />
      <div className="MyBody">
        <div className="UserName">
          {user}'s Profile(USERNAME)
          <div className="HamAchievement">
            <GiAchievement size={"40"} color={"orange"} />
            <b>500000</b>
          </div>
        </div>
        <div className="MyBody2">
          <div className="HamGrad">
            <div className="HamSlot">
              <div className="HamSlotSlot">
                <img src="hamzzi.png" style={{ width: "100%" }} alt="" />
              </div>
              <div className="HamSlotSlot">
                <img src="hamzzi.png" style={{ width: "100%" }} alt="" />
              </div>
              <div className="HamSlotSlot">
                <img src="hamzzi.png" style={{ width: "100%" }} alt="" />
              </div>
            </div>
            <div className="HamSlot">
              <div className="HamSlotSlot">
                <img src="hamzzi.png" style={{ width: "100%" }} alt="" />
              </div>
              <div className="HamSlotSlot">
                <img src="hamzzi.png" style={{ width: "100%" }} alt="" />
              </div>
              <div className="HamSlotSlot">
                <img src="hamzzi.png" style={{ width: "100%" }} alt="" />
              </div>
            </div>
            <div className="HamSlot2">
              <div className="HamSlotSlot">
                <img src="hamzzi.png" style={{ width: "100%" }} alt="" />
              </div>
              <div className="HamSlotSlot">
                <img src="hamzzi.png" style={{ width: "100%" }} alt="" />
              </div>
              <div className="HamSlotSlot">
                <img src="hamzzi.png" style={{ width: "100%" }} alt="" />
              </div>
            </div>
          </div>
          <div className="GuestBook"><DiaryEditor onCreate={onCreate}/><DiaryList diaryList={data} onDelete={onDelete} onEdit={onEdit}/></div>
        </div>
      </div>
    </>
  );
};

export default Profile;
