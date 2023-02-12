import React, { useRef, useState, useEffect, useLayoutEffect } from "react";
import TodoList from "../../../components/Todos/TodoList";

import TodoInput from "../../../components/Todos/TodoInput";
import { FcCalendar } from "react-icons/fc";

import { AiFillCaretRight, AiFillCaretLeft } from "react-icons/ai";
import DatePicker from "react-datepicker";
// import Header from "./../../../components/Header";
import axios from "axios";

import "react-datepicker/dist/react-datepicker.css";
import "../../../styles/Todos.css";
import api from "./../../../components/api";
import { useDispatch } from "react-redux";
import { receivePoint } from "../../../pointSlice";
import { motion } from "framer-motion";

const Todos = () => {
  const nickname = localStorage.getItem("nickname");
  const user = JSON.parse(localStorage.getItem("user"));
  const [todo_menu, setTodoMenu] = useState([true, false]);
  const [searchword, setSearchWord] = useState(null);
  const dispatch = useDispatch();

  const getStringDate = (date) => {
    return date.toISOString().slice(0, 10);
  };
  // 달력 열기
  const [startDate, setStartDate] = useState(new Date());
  const [isOpen, setIsOpen] = useState(false);

  // 날짜 설정
  const TIME_ZONE = 3240 * 10000;
  const d = new Date(startDate);

  const no = useRef();
  const [todos, setTodos] = useState([]);
  const [calDate, setCaldate] = useState(
    new Date(+d + TIME_ZONE).toISOString().split("T")[0]
  );

  // 검색을 했냐 안했냐~!
  const [isSearch, setIsSearch] = useState(false);

  // const { current: todotodos } = useRef(todos);
  const [addTodo, setAddTodo] = useState({
    todo_id: no,
    content: "",
    datetime: calDate,
    ischeck: false,
    nickname: nickname,
  });

  // 달력 선택한 날짜로
  const handleChange = (e) => {
    setIsOpen(!isOpen);
    console.log(e);
    setStartDate(e);
  };
  // 달력 열기
  const handleClick = (e) => {
    e.preventDefault();
    setIsOpen(!isOpen);
  };

  // db에서 todolist 가져오기
  const getTodo = async () => {
    await api.get(`/api/todo/${nickname}/${calDate}`).then((res) => {
      // console.log(res);
      console.log("db에서 todolist 가져오기");
      console.log(nickname);
      console.log(res.data);
      setTodos(res.data);
    });
  };

  const handleToday = () => {
    setCaldate(new Date().toISOString().split("T")[0]);
    getTodo();
  };

  // 처음 렌더링
  useEffect(() => {
    setCaldate(new Date(+d + TIME_ZONE).toISOString().split("T")[0]);
    getTodo();
    console.log(calDate);
    console.log(todos);
    console.log(nickname);
    // setDate(new Date())
  }, []);

  // 달력 날짜가 바뀔 때마다 실행
  useEffect(() => {
    setCaldate(new Date(+d + TIME_ZONE).toISOString().split("T")[0]);
  }, [startDate]);

  useEffect(() => {
    getTodo();
    console.log("날짜가 변해서 데이터 가져옴");
    // setCaldate(new Date(+d + TIME_ZONE).toISOString().split("T")[0]);
  }, [calDate, addTodo]);

  // useEffect(() => {
  //   getTodo();
  //   console.log("todo가 변해서 데이터 가져옴");
  //   // setCaldate(new Date(+d + TIME_ZONE).toISOString().split("T")[0]);
  // }, [todotodos]);

  // 특정 투두 검색 api
  const onSearchTodo = async (text) => {
    await api
      .get(`/api/todo/list/search?nickname=${nickname}&content=${text}`)
      .then((res) => {
        console.log("특정 투두 검색 api");
        console.log(res);
        setTodos(res.data);
      });
  };

  const onAdd = async (text) => {
    setCaldate(new Date(+d + TIME_ZONE).toISOString().split("T")[0]);
    const newTodos = {
      content: text,
      datetime: calDate,
      user_nickname: nickname,
    };
    await api.post("/api/todo", newTodos).then((res) => {
      console.log(res.data);
      console.log(parseInt(res.data.slice(4, 7)));
      // no = parseInt(res.data.slice(4,7))
      setAddTodo({
        todo_id: no,
        content: text,
        datetime: calDate,
        ischeck: false,
        nickname: nickname,
      });
    });
    console.log(addTodo);
    setTodos([addTodo, ...todos]);
    console.log(todos);
    // getTodo();
    console.log("투두를 추가했다!");
  };

  const onDel = (id) => {
    api.delete(`/api/todo/${id}`).then((res) => {
      setAddTodo({
        todo_id: no,
        content: "text",
        datetime: calDate,
        ischeck: false,
        nickname: nickname,
      });
    });
    // getTodo();
    setTodos(todos.filter((todo) => todo.id !== id));
    console.log("투두를 삭제했다!");
  };

  const onToggle = (id) => {
    api.put(`/api/todo/check/${nickname}/${id}`).then(() => {
      // 포인트 받아온 뒤 redux에 반영
      api.get(`/api/user/mypage?email=${user}`).then((res) => {
        console.log(res.data.point);
        dispatch(receivePoint(res.data.point));
      });
    });
    // getTodo();
    setTodos(
      todos.map((todo) =>
        todo.todo_id === id
          ? {
              ...todo,
              ischeck: !todo.ischeck,
            }
          : todo
      )
    );
    // getTodo();
    console.log("투두를 완료했다!");
  };

  // 방명록 수정
  const onEdit = (id, newContent, datetime) => {
    api
      .put(`/api/todo/${id}`, {
        content: newContent,
        datetime: datetime,
        user_nickname: nickname,
      })
      .then((res) => res);
    setTodos(
      todos.map((todo) =>
        todo.todo_id === id ? { ...todo, content: newContent } : todo
      )
    );
    // getTodo();
    console.log("투두를 수정했다!");
  };

  const increaseMonth = () => {
    console.log(startDate);
    let tomorrow = new Date(startDate.setDate(startDate.getDate() + 1));
    setStartDate(tomorrow);
  };

  const decreaseMonth = () => {
    let yesterday = new Date(startDate.setDate(startDate.getDate() - 1));
    setStartDate(yesterday);
  };

  const CreateFlagfunc = () => {
    setTodoMenu([true, false]);
  };

  const SearchFlagfunc = () => {
    setTodoMenu([false, true]);
  };

  const onKeyword = (e) => {
    console.log(e.target.value);
    setSearchWord(e.target.value);
  };

  // 검색했을 때
  const onSearchFunc = (text) => {
    onSearchTodo(text);
    setIsSearch(true);
  };

  // 검색 취소
  const onSearchCancelFunc = () => {
    getTodo();
    setIsSearch(false);
  };

  const variants = {
    hidden: { opacity: 0 },
    visibie: { opacity: 1 },
  };

  return (
    <motion.div
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      exit={{ y: -100, opacity: 0 }}
      transition={{ duration: 1 }}
      variants={variants}
    >
      {/* <Header data={"Todo"} type={"Todo"} /> */}
      <div className="MyBody">
        <TodoInput onAdd={onAdd} onSearchFunc={onSearchFunc} />
        <div className="TodoHeaderButton">
          {/* <button
            onClick={CreateFlagfunc}
            className={todo_menu[0] ? "TodoButton--active" : null}
          >
            날짜 선택
          </button>
          <button
            onClick={SearchFlagfunc}
            className={todo_menu[1] ? "TodoButton--active" : ""}
          >
            투두 검색
          </button> */}

          {/* {todo_menu[0] && (
          <button onClick={onCreateGuild}>생성하기</button>
        )} */}
          {todo_menu[1] && (
            <>
              <form onSubmit="return false;">
                <input
                  type="text"
                  placeholder="투두 이름을 입력하세요"
                  onChange={onKeyword}
                />
              </form>
              {isSearch ? (
                <button onClick={onSearchCancelFunc}>취소</button>
              ) : (
                <button onClick={onSearchFunc}>검색하기</button>
              )}
            </>
          )}
          {todo_menu[0] && (
            <div className="DateControl">
              {/* 달력 아이콘을 누르면 isOpen을 true */}
              {/* <button onClick={handleClick} className="Calbutton"> */}
              <div onClick={handleClick} className="HamCalendar">
                <img src="assets/calendar.png" alt="" />
              </div>
              {/* </button> */}
              {/* <div className="DateContainer"> */}
              <div className="DateButton" onClick={decreaseMonth}>
                <AiFillCaretLeft className="DateButton_left" size={"55%"} />
              </div>

              <p>{calDate}</p>
              <div className="DateButton" onClick={increaseMonth}>
                <AiFillCaretRight className="DateButton_right" size={"55%"} />
              </div>
              {/* </div> */}
              {/* <p class="arrow_box">내asdasdasd일</p> */}
              <div className="ToHomeBtn" onClick={handleToday}>
                오늘 할 일
              </div>
            </div>
          )}
        </div>

        {isOpen && (
          <DatePicker selected={startDate} onChange={handleChange} inline />
        )}

        <TodoList
          Caldate={calDate}
          todos={todos}
          onDel={onDel}
          onToggle={onToggle}
          onEdit={onEdit}
          setTodos={setTodos}
        />
      </div>
    </motion.div>
  );
};

export default Todos;
