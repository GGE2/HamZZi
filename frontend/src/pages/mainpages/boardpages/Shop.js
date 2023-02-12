import React, { useState, useEffect } from "react";
import "../../../styles/DressRoom.css";
import '../../../styles/ShopModal.css';
import '../../../styles/WearModal.css';
import HatItem from "../../../components/DressRoom/HatItem";
import ClothItem from "../../../components/DressRoom/ClothItem";
import { motion } from "framer-motion";
import api from "../../../components/api";
import LoadingModal from "../../../components/LoadingModal";
import MyItem from "./../../../components/DressRoom/MyItem";
import DecoItem from "./../../../components/DressRoom/DecoItem";

const DressRoom = () => {
  const nickname = localStorage.getItem("nickname");

  const [show, setShow] = useState({
    hatShow: true,
    clothShow: false,
    decoShow: false,
    myShow: false,
  });

  const [loading, setLoading] = useState(true);
  // 전체 아이템
  const [items, setItem] = useState([]);
  const [myItems, setMyItem] = useState([]);
  // 버튼 눌림 css
  const [menu, setMenu] = useState([true, false, false, false]);

  const onCheckHat = () => {
    setShow({
      hatShow: true,
      clothShow: false,
      decoShow: false,
      myShow: false,
    });
    setMenu([true, false, false, false]);
  };

  

  const onCheckCloth = () => {
    setShow({
      hatShow: false,
      clothShow: true,
      decoShow: false,
      myShow: false,
    });
    setMenu([false, true, false, false]);
  };

  const onCheckDeco = () => {
    setShow({
      hatShow: false,
      clothShow: false,
      decoShow: true,
      myShow: false,
    });
    setMenu([false, false, true, false]);
  };

  const onCheckMy = () => {
    setShow({
      hatShow: false,
      clothShow: false,
      decoShow: false,
      myShow: true,
    });
    setMenu([false, false, false, true]);
  };

  // 아이템 전체 리스트 조회
  const onGetItemAllList = async () => {
    // setLoading(true);
    await api
      .get(`/api/item/itemAllList`)
      .then((res) => {
        console.log(`전체 아이템`);
        console.log(res);
        setItem(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // 닉네임으로 아이템 유저 조회
  const onGetItemUserNickList = async () => {
    await api
      .get(`/api/item/itemUserList/${nickname}`)
      .then((res) => {
        console.log(`보유아이템`);
        console.log(res.data);
        setMyItem(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // 아이템 추가 이건 db용
  const onAddItem = async () => {
    await api
      .post(`/api/item/createItem/4`, {
        cost: 2,
        item_id: 4,
        level: 1,
      })
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const onBuyItem = async (id) => {
    await api
      .post(`/api/item/createItemUser/${id}`, {
        nickname: nickname,
      })
      .then((res) => {
        console.log(`아이템을 구매`);
        console.log(res);
        // setMyItem(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    onGetItemAllList(); // 상점 전체 아이템
    // onGetItemUserAllList();
    onGetItemUserNickList(); // 내가 산 아이템 보기
    // onAddItem()
  }, []);

  return (
    <div className="DressRoom">
      {loading ? (
        <LoadingModal />
      ) : (
        <>
          <div className="DressButton">
            <motion.button
              className={
                menu[0] ? "DressButton_clicked" : "DressButton_Unclicked"
              }
              onClick={onCheckHat}
            >
              <div
                className={
                  menu[0]
                    ? "DressButton_clicked_text"
                    : "DressButton_Unclicked_text"
                }
              >
                모자
              </div>
            </motion.button>
            <motion.button
              className={
                menu[1] ? "DressButton_clicked" : "DressButton_Unclicked"
              }
              onClick={onCheckCloth}
            >
              <div
                className={
                  menu[1]
                    ? "DressButton_clicked_text"
                    : "DressButton_Unclicked_text"
                }
              >
                의류
              </div>{" "}
            </motion.button>
            <motion.button
              className={
                menu[2] ? "DressButton_clicked" : "DressButton_Unclicked"
              }
              onClick={onCheckDeco}
            >
              <div
                className={
                  menu[2]
                    ? "DressButton_clicked_text"
                    : "DressButton_Unclicked_text"
                }
              >
                장식
              </div>{" "}
            </motion.button>
            <motion.button
              className={
                menu[3] ? "DressButton_clicked" : "DressButton_Unclicked"
              }
              onClick={onCheckMy}
            >
              <div
                className={
                  menu[3]
                    ? "DressButton_clicked_text"
                    : "DressButton_Unclicked_text"
                }
              >
                보유중
              </div>{" "}
            </motion.button>
          </div>
          {show.hatShow && (
            <div className="DressList">
              {items
                .filter((item) => item.type === "모자")
                .map((item, idx) => (
                  <HatItem key={idx} id={item.item_id} onBuyItem={onBuyItem} cost={item.cost} myItems={myItems}/>
                ))}
            </div>
          )}

          {show.clothShow && (
            <div className="ClothList">
              {items
                .filter((item) => item.type === "옷")
                .map((item, idx) => (
                  <ClothItem key={idx} id={item.item_id} onBuyItem={onBuyItem} cost={item.cost} myItems={myItems}/>
                ))}
            </div>
          )}

          {show.decoShow && (
            <div className="ClothList">
              {items
                .filter((item) => item.type === "장식")
                .map((item, idx) => (
                  <DecoItem key={idx} id={item.item_id} onBuyItem={onBuyItem} cost={item.cost} myItems={myItems}/>
                ))}
            </div>
          )}

          {show.myShow && (
            <div className="ClothList">
              {myItems.map((item, idx) => (
                <MyItem key={idx} id={item.item.item_id} />
              ))}
            </div>
          )}
        </>
      )}
    </div>
  );
};

export default DressRoom;
