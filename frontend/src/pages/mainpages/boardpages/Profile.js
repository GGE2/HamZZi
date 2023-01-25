import React from "react";
import { useSelector } from "react-redux";
import { selectCurrentUser } from "../../../authSlice";

const Profile = () => {
  const user = useSelector(selectCurrentUser);
  return <div>{user}'s Profile</div>;
};

export default Profile;
