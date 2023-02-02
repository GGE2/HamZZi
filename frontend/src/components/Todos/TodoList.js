import React from "react";
import TodoItem from "./TodoItem";
// import './TodoList.css';

const TodoList = ({ Caldate, todos, onDel, onEdit, onToggle }) => {
  // console.log(todos)
  return (
    <>
      {Caldate}
      <div className="TodoList">
        {todos.filter((todos)=> todos.datetime === Caldate).map((todos) => (
          <TodoItem
            key={todos.todo_id}
            todos={todos}
            onDel={onDel}
            onToggle={onToggle}
            onEdit={onEdit}
          />
        ))}
        {/* {todos.map((todos) => (
          <TodoItem
            key={todos.todo_id}
            todos={todos}
            onDel={onDel}
            onToggle={onToggle}
          />))} */}
      </div>
    </>
  );
};

export default TodoList;
