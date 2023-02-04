import React from "react";
import { useState } from "react";
import { BiDotsHorizontalRounded } from "react-icons/bi";
import { useEffect } from "react";
import DropdownMenu from './GuestBook/DropdownMenu';

const Header = ({ data, type }) => {
  const [menu, setMenu] = useState("");

  useEffect(() => {
    if (type === "profile") {
      setMenu(type);
    }
  }, []);

  return (
    <>
      {menu ? (
        <div className="Header">
          {data}
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
          {data}
        </div>
      )}
    </>
  );
};

export default Header;
