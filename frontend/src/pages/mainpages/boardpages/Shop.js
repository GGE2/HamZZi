import React, { useState, useEffect } from "react";
import "../../../styles/DressRoom.css";
import "../../../styles/ShopModal.css";
import "../../../styles/WearModal.css";
import HatItem from "../../../components/DressRoom/HatItem";
import ClothItem from "../../../components/DressRoom/ClothItem";
import { motion } from "framer-motion";
import api from "../../../components/api";
import LoadingModal from "../../../components/LoadingModal";
import MyItem from "./../../../components/DressRoom/MyItem";
import DecoItem from "./../../../components/DressRoom/DecoItem";
import {
  selectCurrentHamLevel,
  selectCurrentHamStat,
} from "../../../hamStatSlice";
import { selectCurrentPoint } from "../../../pointSlice";
import { useSelector, useDispatch } from "react-redux";
import {
  getCheckHat,
  getCheckCloth,
  getCheckDeco,
  getCheckMy,
  selectCurrentshopShow,
  selectCurrenthatShow,
  selectCurrentclothShow,
  selectCurrentdecoShow,
  selectCurrentmyShow,
} from "./../../../shopSlice";

const DressRoom = ({ getAllProfile, getShopUpdate, Wear }) => {
  const nickname = localStorage.getItem("nickname");
  const petLevel = useSelector(selectCurrentHamLevel);
  const point = useSelector(selectCurrentPoint);
  const hat = useSelector(selectCurrenthatShow);
  const cloth = useSelector(selectCurrentclothShow);
  const deco = useSelector(selectCurrentdecoShow);
  const my = useSelector(selectCurrentmyShow);
  // getCheckCloth

  const dispatch = useDispatch();

  const [loading, setLoading] = useState(true);

  // 전체 아이템
  const [items, setItem] = useState([]);
  const [myItems, setMyItem] = useState([]);

  const onCheckHat = () => {
    dispatch(getCheckHat());
  };

  const onCheckCloth = () => {
    dispatch(getCheckCloth());
  };

  const onCheckDeco = () => {
    dispatch(getCheckDeco());
  };

  const onCheckMy = () => {
    dispatch(getCheckMy());
  };

  // 아이템 전체 리스트 조회
  const onGetItemAllList = async () => {
    await api
      .get(`/api/item/itemAllList`)
      .then((res) => {
        setItem(res.data);
      })
      .catch((err) => {});
  };

  // 닉네임으로 아이템 유저 조회
  const onGetItemUserNickList = async () => {
    await api
      .get(`/api/item/itemUserList/${nickname}`)
      .then((res) => {
        setMyItem(res.data);
      })
      .catch((err) => {});
  };

  // 아이템 장착
  const onWearItem = async (id) => {
    await api
      .put(`/api/item?nickname=${nickname}&item_id=${id}`)
      .then((res) => {
        getShopUpdate();
      })
      .catch((err) => {});
  };

  // 아이템 구매하기
  const onBuyItem = async (id) => {
    await api
      .post(`/api/item/createItemUser/${id}`, {
        nickname: nickname,
      })
      .then((res) => {
        onGetItemAllList(); // 상점 전체 아이템
        onGetItemUserNickList(); // 내가 산 아이템 보기
        getShopUpdate();
      })
      .catch((err) => {});
  };

  useEffect(() => {
    setTimeout(() => {
      setLoading(false);
    }, 700);
  });

  useEffect(() => {
    onGetItemAllList(); // 상점 전체 아이템
    onGetItemUserNickList(); // 내가 산 아이템 보기
  }, []);

  useEffect(() => {
    onGetItemAllList(); // 상점 전체 아이템
    onGetItemUserNickList(); // 내가 산 아이템 보기
  }, [point]);

  const variants = {
    hidden: { opacity: 0 },
    visibie: { opacity: 1 },
  };

  return (
    <div className="DressRoom">
      <div className="postit4"></div>
      {loading ? (
        <LoadingModal />
      ) : (
        <motion.div
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          exit={{ y: -100, opacity: 0 }}
          transition={{ duration: 1 }}
          variants={variants}
          className="DressRoomMotion"
        >
          <div className="DressButton">
            <motion.button
              className={hat ? "DressButton_clicked" : "DressButton_Unclicked"}
              onClick={onCheckHat}
            >
              <div
                className={
                  hat
                    ? "DressButton_clicked_text"
                    : "DressButton_Unclicked_text"
                }
              >
                모자
              </div>
            </motion.button>
            <motion.button
              className={
                cloth ? "DressButton_clicked" : "DressButton_Unclicked"
              }
              onClick={onCheckCloth}
            >
              <div
                className={
                  cloth
                    ? "DressButton_clicked_text"
                    : "DressButton_Unclicked_text"
                }
              >
                의류
              </div>{" "}
            </motion.button>
            <motion.button
              className={deco ? "DressButton_clicked" : "DressButton_Unclicked"}
              onClick={onCheckDeco}
            >
              <div
                className={
                  deco
                    ? "DressButton_clicked_text"
                    : "DressButton_Unclicked_text"
                }
              >
                장식
              </div>{" "}
            </motion.button>
            <motion.button
              className={my ? "DressButton_clicked" : "DressButton_Unclicked"}
              onClick={onCheckMy}
            >
              <div
                className={
                  my ? "DressButton_clicked_text" : "DressButton_Unclicked_text"
                }
              >
                보유중
              </div>{" "}
            </motion.button>
          </div>
          {hat && (
            <div className="DressList">
              {items
                .filter((item) => item.type === "모자")
                .map((item, idx) => (
                  <HatItem
                    key={idx}
                    id={item.item_id}
                    onBuyItem={onBuyItem}
                    point={point}
                    cost={item.cost}
                    myItems={myItems}
                    type={item.type}
                    petLevel={petLevel}
                    itemlevel={item.level}
                    onWearItem={onWearItem}
                    onGetItemAllList={onGetItemAllList}
                    onGetItemUserNickList={onGetItemUserNickList}
                  />
                ))}
            </div>
          )}

          {cloth && (
            <div className="ClothList">
              {items
                .filter((item) => item.type === "옷")
                .map((item, idx) => (
                  <ClothItem
                    key={idx}
                    id={item.item_id}
                    onBuyItem={onBuyItem}
                    point={point}
                    cost={item.cost}
                    myItems={myItems}
                    type={item.type}
                    petLevel={petLevel}
                    itemlevel={item.level}
                    onWearItem={onWearItem}
                    onGetItemAllList={onGetItemAllList}
                    onGetItemUserNickList={onGetItemUserNickList}
                  />
                ))}
            </div>
          )}

          {deco && (
            <div className="ClothList">
              {items
                .filter((item) => item.type === "배경")
                .map((item, idx) => (
                  <DecoItem
                    key={idx}
                    id={item.item_id}
                    onBuyItem={onBuyItem}
                    point={point}
                    cost={item.cost}
                    myItems={myItems}
                    type={item.type}
                    petLevel={petLevel}
                    itemlevel={item.level}
                    onWearItem={onWearItem}
                    onGetItemAllList={onGetItemAllList}
                    onGetItemUserNickList={onGetItemUserNickList}
                  />
                ))}
            </div>
          )}

          {my && (
            <div className="ClothList">
              {myItems.map((item, idx) => (
                <MyItem
                  key={idx}
                  id={item.item.item_id}
                  type={item.item.type}
                  onWearItem={onWearItem}
                  Wear={Wear}
                  petLevel={petLevel}
                  itemlevel={item.item.level}
                />
              ))}
            </div>
          )}
        </motion.div>
      )}
    </div>
  );
};

export default DressRoom;
