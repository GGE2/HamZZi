import React from "react";
import styled, { css } from "styled-components";
import useDetectClose from "../useDetectClose";
import { BiDotsHorizontalRounded } from "react-icons/bi";
import { useNavigate } from "react-router";

const DropdownMenu = () => {
  const navigate = useNavigate();
  const [myPageIsOpen, myPageRef, myPageHandler] = useDetectClose(false);
  const LogoutAuth = () => {
    localStorage.clear();
    navigate("/");
  };
  return (
    <>
      <DropdownContainer>
        <DropdownButton onClick={myPageHandler} ref={myPageRef}>
          <BiDotsHorizontalRounded size={'100%'} />
        </DropdownButton>
        <Menu isDropped={myPageIsOpen}>
          <Ul>
            <Li>
              <LinkWrapper onClick={LogoutAuth} href="#1-2">
                로그아웃
              </LinkWrapper>
            </Li>
            <Li>
              <LinkWrapper2 href="#1-3">정보수정</LinkWrapper2>
            </Li>
            <Li>
              <LinkWrapper href="#1-3">회원탈퇴</LinkWrapper>
            </Li>
          </Ul>
        </Menu>
      </DropdownContainer>
    </>
  );
};

export default DropdownMenu;

const DropdownContainer = styled.div`
  position: relative;
  text-align: center;
  display: flex;
  align-items: center;
  // padding-top: 10px;
`;

const DropdownButton = styled.div`
  cursor: pointer;
`;

const Menu = styled.div`
  background: gray;
  position: absolute;
  top: 55px;
  left: 50%;
  width: 100px;
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

const Ul = styled.ul`
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
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
`;
const Li = styled.li``;

const LinkWrapper = styled.div`
  width: 100%;
  font-size: 16px;
  text-decoration: none;
  color: white;
  cursor: pointer;

  &:hover {
    background-color: red;
  }
`;

const LinkWrapper2 = styled.div`
  width: 100%;
  font-size: 16px;
  text-decoration: none;
  color: white;
  cursor: pointer;

  &:hover {
    background-color: red;
  }
  &:after {
    border-top: 3px solid black;
    border: 12px solid transparent;
    border-top-width: 0;
    border-bottom-color: gray;
  }

  &:before {
    border: 12px solid transparent;
    border-top-width: 0;
    border-bottom-color: gray;
  }
`;
