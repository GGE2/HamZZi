import React from "react";
import axios from "axios";

const PetStatCtrl = ({ setIsModal, stat, setStat }) => {
  console.log(stat);

  const handleClose = () => {
    console.log("닫는다");
    setIsModal(false);
  };

  const upPhysical = async () => {
    setStat({
      ...stat,
      physical: stat.physical + 1,
    });
    await axios
    .put(`http://3.35.88.23:8080/api/pet/stat`,
    {
      artistic: stat.artistic,
      energetic: stat.energetic,
      etc: stat.etc,
      inactive: stat.inactive,
      intelligent: stat.intelligent,
      pet_id: stat.petStat_id,
      physical: stat.physical,
    })
    .then((res) => {
      // console.log(res);
      console.log(res);
      
    });
  };

  // const onIncreaseStat = (type) => {
  //   if (type === "physical") {
  //     setStat({
  //       ...stat,
  //       physical: stat.physical + 1,
  //     })

  //   } else if (type === "artistic") {
  //     setStat({
  //       ...stat,
  //       artistic: stat.artistic + 1,
  //     });
  //   } else if (type === "intelligent") {
  //     setStat({
  //       ...stat,
  //       intelligent: stat.intelligent + 1,
  //     });
  //   } else if (type === "inactive") {
  //     setStat({
  //       ...stat,
  //       inactive: stat.inactive + 1,
  //     });
  //   } else if (type === "active") {
  //     setStat({
  //       ...stat,
  //       active: stat.active + 1,
  //     });
  //   } else if (type === "etc") {
  //     setStat({
  //       ...stat,
  //       etc: stat.etc + 1,
  //     });
  //   }
  // };

  return (
    <>
      <div
        className="Modal"
        onClick={() => {
          setIsModal(false);
        }}
      ></div>

      <div className="modalBody">
        <div>
          "육체적"
          {stat.physical}
          <button onClick={upPhysical}>+</button>
        </div>
        <div>
          "예술적"
          {stat.artistic}
          <button>+</button>
        </div>
        <div>
          "지능적"
          {stat.intelligent}
          <button>+</button>
        </div>
        <div>
          "비활동적"
          {stat.inactive}
          <button>+</button>
        </div>
        <div>
          "활동적"
          {stat.active}
          <button>+</button>
        </div>
        <div>
          "기타"
          {stat.etc}
          <button>+</button>
        </div>

        <button onClick={handleClose}>취소</button>
      </div>
    </>
  );
};

export default PetStatCtrl;
