import React, { useRef, useState, useEffect, useLayoutEffect } from "react";
import TodoList from "../../../components/Todos/TodoList";
import TodoInput from "../../../components/Todos/TodoInput";
import { FcCalendar } from "react-icons/fc";

import { AiFillCaretRight, AiFillCaretLeft } from "react-icons/ai";
import DatePicker from "react-datepicker";
import Header from "./../../../components/Header";
import axios from "axios";

import "react-datepicker/dist/react-datepicker.css";
import "../../../styles/Todos.css";

const Todos = () => {
  const nickname = localStorage.getItem("nickname");
  const [todo_menu, setTodoMenu] = useState([true, false]);
  const [searchword, setSearchWord] = useState(null);

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
    await axios
      .get(`http://3.35.88.23:8080/api/todo/${nickname}/${calDate}`)
      .then((res) => {
        // console.log(res);
        console.log("db에서 todolist 가져오기");
        console.log(nickname);
        console.log(res.data);
        setTodos(res.data);
      });
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
  const onSearchTodo = async () => {
    await axios
      .get(
        `http://3.35.88.23:8080/api/todo/list/search?nickname=${nickname}&content=${searchword}`
      )
      .then((res) => {
        console.log("특정 투두 검색 api");
        console.log(res);
      });
  };

  const onAdd = async (text) => {
    setCaldate(new Date(+d + TIME_ZONE).toISOString().split("T")[0]);
    const newTodos = {
      content: text,
      datetime: calDate,
      user_nickname: nickname,
    };
    await axios
      .post("http://3.35.88.23:8080/api/todo", newTodos)
      .then((res) => {
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
    axios.delete(`http://3.35.88.23:8080/api/todo/${id}`).then((res) => {
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
    axios.put(`http://3.35.88.23:8080/api/todo/check/${nickname}/${id}`);
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
    axios
      .put(`http://3.35.88.23:8080/api/todo/${id}`, {
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

  return (
    <>
      <Header data={"Todo"} type={"Todo"} />
      <div className="MyBody">
        <div className="FriendHeaderButton">
          <button
            onClick={CreateFlagfunc}
            className={todo_menu[0] ? "TodoButton--active" : null}
          >
            투두 생성
          </button>
          <button
            onClick={SearchFlagfunc}
            className={todo_menu[1] ? "TodoButton--active" : ""}
          >
            투두 검색
          </button>
          
          {/* {todo_menu[0] && (
          <button onClick={onCreateGuild}>생성하기</button>
        )} */}
          {todo_menu[1] &&<><form onSubmit="return false;">
            <input
              type="text"
              placeholder="투두 이름을 입력하세요"
              onChange={onKeyword}
            />
          </form> <button onClick={onSearchTodo}>검색하기</button></>}
          {todo_menu[0] && (
          <div className="DateControl">
            {/* 달력 아이콘을 누르면 isOpen을 true */}
            <button onClick={handleClick} className="Calbutton">
              <FcCalendar size={"100%"} />
            </button>
            <div className="DateButton" onClick={decreaseMonth}>
              <AiFillCaretLeft className="DateButton_left" size={"55%"} />
            </div>

            {calDate}
            <div className="DateButton" onClick={increaseMonth}>
              <AiFillCaretRight className="DateButton_right" size={"55%"} />
            </div>
            {/* <p class="arrow_box">내asdasdasd일</p> */}
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
        />
        <TodoInput onAdd={onAdd} />
      </div>
    </>
  );
};

export default Todos;
