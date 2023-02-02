import React from "react";
import styled, { css } from "styled-components";
import useDetectClose from "../useDetectClose";
import { BiDotsVerticalRounded } from "react-icons/bi";

const DropdownTodoMenu = ({onDel, toggleIsEdit, todo_id}) => {
  const [myPageIsOpen, myPageRef, myPageHandler] = useDetectClose(false);

  return (
    <>
      <DropdownContainer>
        <DropdownButton onClick={myPageHandler} ref={myPageRef}>
          <BiDotsVerticalRounded size={"100%"}/>
        </DropdownButton>
        <Menu isDropped={myPageIsOpen}>
          <Ul>
           
              <LinkWrapper onClick={toggleIsEdit}>수정</LinkWrapper>
              <div className="todoline"></div>
              <LinkWrapper onClick={() => onDel(todo_id)}>삭제</LinkWrapper>
         
          </Ul>
        </Menu>
      </DropdownContainer>
    </>
  );
};

export default DropdownTodoMenu;

const DropdownContainer = styled.div`
  position: relative;
  text-align: center;
  display:flex;
  align-items: center;
  // padding-top:10px
`;

const DropdownButton = styled.div`
  cursor: pointer;
`;

const Menu = styled.div`
  background: gray;
  position: absolute;
  top: 50px;
  left: 50%;
  width: 110px;
  text-align: center;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.2);
  border-radius: 3px;
  opacity: 0;
  visibility: hidden;
  transform: translate(-50%, -20px);
  transition: opacity 0.4s ease, transform 0.4s ease, visibility 0.4s;
  z-index: 9;

  &:after {
    content: "";
    height: 0;
    width: 0;
    position: absolute;
    top: -3px;
    left: 50%;
    transform: translate(-50%, -50%);
    border: 12px solid transparent;
    border-top-width: 0;
    border-bottom-color: gray;
  }

  ${({ isDropped }) =>
    isDropped &&
    css`
      opacity: 1;
      visibility: visible;
      transform: translate(-50%, 0);
      left: 50%;
    `};
`;

const Ul = styled.div`
  & > li {
    margin-bottom: 5px;
  }

  & > li:first-of-type {
    margin-top: 5px;
  }
  width: 100%;
  list-style-type: none;
  padding: 0;
  margin: 0;
  display: flex;
  // flex-direction: column;
  justify-content: space-around;
  align-items: center;
`;
const Li = styled.li``;

const LinkWrapper = styled.div`
  margin:5px;
  // padding: 5-
  width: 100%;
  font-size: 16px;
  text-decoration: none;
  color: white;
  cursor: pointer;
  

  &:hover {
    background-color: red;
  }
`;
