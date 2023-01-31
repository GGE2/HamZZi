import React from 'react';
import {FaRegTrashAlt} from "react-icons/fa";
import { MdOutlineCheckBox, MdOutlineCheckBoxOutlineBlank } from 'react-icons/md';
import { BiDotsVerticalRounded } from "react-icons/bi";



const TodoItem = ({todos,onDel,onToggle}) => {
 
    const {id, text, done, created_date} = todos
 
    return (
        <div>
            <li className={done ? 'on' : ''}>
                <span onClick={()=>onToggle(id)}>
                    {done ? <MdOutlineCheckBox/> : <MdOutlineCheckBoxOutlineBlank/>}
                </span>
                {created_date}
                
                <em onClick={()=>onToggle(id)}>{text}</em>
               
              <BiDotsVerticalRounded />
          
                <button onClick={()=>onDel(id)}><FaRegTrashAlt color='rgb(175,169,169)' size='20'/></button>
            </li>
        </div>
    );
};

export default TodoItem;