import React from "react";
import { useSelector } from "react-redux";
import { selectCurrentUser } from "../../../authSlice";
import Header from './../../../components/Header';

const Profile = () => {
  const user = useSelector(selectCurrentUser);
  return <div>
    <Header data={'User'} />
    {user}'s Profile</div>;
};

export default Profile;
