import React from "react";
import { GiAchievement } from "react-icons/gi";

const FriendItem = ({ friend, length }) => {
     
    const { name, id } = friend;
    console.log(length)
    console.log(`id번호:${id}`)
  return (
    <>    {length === id ? (<div className="FriendItem" style={{borderBottom:'none'}}>
    
    <div className="FriendName"> {name}</div>
    <div className="FriendAchieve">
     
      <GiAchievement size={"40"} color={"orange"} />
      <b>999</b>
    </div>
    <div className="FriendButton">
      <button>my</button>
      <button>삭제</button>
    </div>

</div>) : (<div className="FriendItem" >
    
    <div className="FriendName"> {name}</div>
    <div className="FriendAchieve">
     
      <GiAchievement size={"40"} color={"orange"} />
      <b>999</b>
    </div>
    <div className="FriendButton">
      <button>my</button>
      <button>삭제</button>
    </div>

</div>)}
    {/* // <div className="FriendItem">
    
    //     <div className="FriendName"> {name}</div>
    //     <div className="FriendAchieve">
         
    //       <GiAchievement size={"40"} color={"orange"} />
    //       <b>999</b>
    //     </div>
    //     <div className="FriendButton">
    //       <button>my</button>
    //       <button>삭제</button>
    //     </div>

    // </div> */}
  
  </>
);
};

export default FriendItem;
