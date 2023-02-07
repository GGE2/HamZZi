import React from "react";
import { useState } from "react";
import { BiDotsHorizontalRounded } from "react-icons/bi";
import { useEffect } from "react";
import DropdownMenu from './GuestBook/DropdownMenu';

const Header = (props) => {
  const [menu, setMenu] = useState("");

  useEffect(() => {
    if (props.type === "profile") {
      setMenu(props.type);
    }
  }, []);

  return (
    <>
      {menu ? (
        <div className="Header">
          {props.data}
          {/* <button>
            <HiOutlineDotsCircleHorizontal size={"100%"} />
          </button> */}
          {/* <button>
              <BiDotsHorizontalRounded size={"100%"} />
            </button> */}
            <DropdownMenu />
        </div>
      ) : (
        <div className="Header">
          {props.data}
        </div>
      )}
    </>
  );
};

export default Header;
