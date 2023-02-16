import React from "react";
import styled, { css } from "styled-components";
import useDetectClose from "../useDetectClose";
import { BiDotsHorizontalRounded } from "react-icons/bi";
import { useNavigate } from "react-router";
import { motion } from "framer-motion";
import { useCookies } from "react-cookie";

const DropdownMenu = ({ onDeleteUser }) => {
  const navigate = useNavigate();
  const [myPageIsOpen, myPageRef, myPageHandler] = useDetectClose(false);

  const COOKIE_KEY = window.LOGIN_KEY; // 상수화시킨 쿠키 값을 넣어줬다.

  const [, , removeCookie] = useCookies([COOKIE_KEY]); // 쓰지 않는 변수는 (공백),처리해주고 removeCookie 옵션만 사용한다

  const LogoutAuth = () => {
    localStorage.clear();
    removeCookie(COOKIE_KEY, { path: "/" });
    navigate("/");
  };

  const DeleteUser = () => {
    onDeleteUser();
    localStorage.clear();
    navigate("/");
  };
  return (
    <>
      <DropdownContainer>
        <DropdownButton onClick={myPageHandler} ref={myPageRef}>
          <motion.div
            whileHover={{
              scale: 1.3,
              transition: { duration: 0.2 },
            }}
            whileTap={{ scale: 0.9 }}
          >
            <BiDotsHorizontalRounded size={"100%"} />
          </motion.div>
        </DropdownButton>
        <Menu isDropped={myPageIsOpen}>
          <Ul>
            <Li>
              <LinkWrapper onClick={LogoutAuth}>로그아웃</LinkWrapper>
            </Li>
            {/* <Li>
              <LinkWrapper2 href="#1-3">정보수정</LinkWrapper2>
            </Li> */}
            <Li>
              <LinkWrapper onClick={DeleteUser}>회원탈퇴</LinkWrapper>
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
  top: 45px;
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
    text-decoration: underline;
  }
`;

const LinkWrapper2 = styled.div`
  width: 100%;
  font-size: 16px;
  text-decoration: none;
  color: white;
  cursor: pointer;

  &:hover {
    text-decoration: underline;
  }
  &:after {
    border-top: 3px solid 803a21;
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
