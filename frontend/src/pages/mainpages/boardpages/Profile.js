import React, { useState, useEffect, useRef } from "react";
import { useSelector } from "react-redux";
import { selectCurrentUser } from "../../../authSlice";
import Header from "./../../../components/Header";
import { GiAchievement } from "react-icons/gi";
import DiaryEditor from "./../../../components/GuestBook/DiaryEditor";
import DiaryList from "./../../../components/GuestBook/DiaryList";
import "../../../styles/Profile.css";
// import PetCarousel from './../../../components/PetCarousel';
import axios from "axios";
import Draggable from "react-draggable";

const Profile = () => {
  const nickname = localStorage.getItem("nickname");
  const email = localStorage.getItem("user");

  // 방명록 리스트
  const [data, setData] = useState([]);

  // 방명록 id
  const dataId = useRef(1);

  // 방명록 생성
  const onCreate = (author, content) => {
    // const created_date = new Date().getTime()

    let today = new Date();
    console.log(today.getMonth());
    let created_date =
      today.getFullYear() +
      "." +
      (today.getMonth() + 1 < 9
        ? "0" + (today.getMonth() + 1)
        : today.getMonth() + 1) +
      "." +
      (today.getDate() < 9 ? "0" + today.getDate() : today.getDate());
    const newItem = {
      author,
      content,
      created_date,
      id: dataId.current,
    };
    dataId.current += 1;
    setData([newItem, ...data]);
  };

  // 방명록 삭제
  const onDelete = (targetId) => {
    const newDiaryList = data.filter((it) => it.id !== targetId);
    setData(newDiaryList);
  };

  // 방명록 수정
  const onEdit = (targetId, newContent) => {
    setData(
      data.map((it) =>
        it.id === targetId ? { ...it, content: newContent } : it
      )
    );
  };

  // 회원 정보 조회 api
  // const getMypage = async () => {
  //   const email2 = JSON.parse(email);
  //   // console.log(typeof(email), email)
  //   await axios.get(`http://3.35.88.23:8080/api/user/mypage?email=${email2}`)
  //   .then((res)=>{
  //     console.log(res.data)
  //   })
  // }

  // UID 체크 api
  const getUID = async () => {
    const email2 = JSON.parse(email);
    await axios
      .get(`http://3.35.88.23:8080/api/user/uid/${email2}?email=${email2}`)
      .then((res) => {
        console.log("uid체크 api");
        console.log(res.data);
      });
  };

  useEffect(() => {
    // getMypage()
    getUID();
  }, []);

  const [position, setPosition] = useState({ x: 0, y: 0 }); // box의 포지션 값
  const [Opacity, setOpacity] = useState(false);

  const handleStart = () => {
    setOpacity(true);
  };
  const handleEnd = () => {
    setOpacity(false);
  };
  // 업데이트 되는 값을 set 해줌
  const trackPos = (data) => {
    setPosition({ x: data.x, y: data.y });
  };
  console.log(position.x);

  const test = (e, data) => {
    trackPos(data);
    console.log(e);
    console.log(data);

    // if(data.x > 330) {
    //   setPosition({ x: 330, y: data.y })
    // }
    // if (data.y > 330) {
    //   setPosition({ x: data.x, y: 330 })
    // }
  };

  return (
    <>
      <Header data={nickname} type={"profile"} />
      <div className="MyBody2">
        <Draggable
          bounds={{ left: 0, top: 0, right: 615, bottom: 340 }}
          onDrag={test}
          onStart={handleStart}
          onStop={handleEnd}
          defaultPosition={{ x: 260, y: 150 }}
        >
          <div className="box">
            <img src="hamzzi.png" alt="" />
          </div>
          {/* <div className="box" style={{ opacity: Opacity ? "0.6" : "1" }}>
            <img src="hamzzi.png" alt="" />
            <div>
              x: {position.x.toFixed(0)}, y: {position.y.toFixed(0)}
            </div>
          </div> */}
        </Draggable>
        {/* <PetCarousel/> */}

        {/* <div className="UserName">
          {user}'s Profile(USERNAME)
     
        </div> */}
        {/* <div className="MyBody2"> */}
        {/* <div className="HamGrad">
            <div className="HamSlot">
              
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
          </div> */}

        {/* 방명록방명록방명록 */}
        {/* <div className="GuestBook">
            <DiaryEditor onCreate={onCreate} />
            <DiaryList diaryList={data} onDelete={onDelete} onEdit={onEdit} />
          </div> */}
      </div>
      {/* </div> */}
    </>
  );
};

export default Profile;
