import { Navigate, Outlet } from "react-router-dom";

const Loginned = () => {
  const token = localStorage.getItem("accessToken");

  return token ? <Navigate to="/main" /> : <Outlet />;
};

export default Loginned;
