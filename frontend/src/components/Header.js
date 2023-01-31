<<<<<<< HEAD
import React from "react";
import { useState } from "react";
import { HiOutlineDotsCircleHorizontal } from "react-icons/hi";
import { useEffect } from "react";

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
          <button>
            <HiOutlineDotsCircleHorizontal size={"100%"} />
          </button>
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
=======
import React from 'react';

const Header = ({data}) => {
    return (
        <div className='Header'>
            <h1>{data}</h1>
        </div>
    );
};

export default Header;
>>>>>>> 862d795ade2ac4e38d20670b3253c2f34ecca8e8
