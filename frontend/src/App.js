import "./App.css";
import Main from "./pages/Main";
import Login from "./pages/Login";
import { Route, Routes } from "react-router-dom";

import KakaoLogin from './pages/loginpages/KakaoLogin';

function App() {
  return (
    // KakaoLogin
    // <div>
    //   <Sendtest />
    // </div>
    <Routes>
      <Route path="/main" element={<Main />} />
      <Route path="/" element={<Login />} />
      <Route path="/kakaoLogin" element={<KakaoLogin />} />
    </Routes>
  );
}

export default App;
