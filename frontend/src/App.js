import "./App.css";
import Main from "./pages/Main";
import Login from "./pages/Login";
import { Route, Routes } from "react-router-dom";
import KakaoLogin from "./pages/loginpages/KakaoLogin";
import RequireAuth from "./pages/loginpages/RequireAuth";

function App() {
  return (
    // KakaoLogin
    // <div>
    //   <Sendtest />
    // </div>
    <Routes>
      <Route index element={<Login />} />
      <Route path="/kakaoLogin" element={<KakaoLogin />} />

      {/* <Route element={<RequireAuth />}> */}
      <Route path="/main" element={<Main />} />
      {/* </Route> */}
    </Routes>
  );
}

export default App;
