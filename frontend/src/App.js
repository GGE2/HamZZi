import "./App.css";
import Main from "./pages/Main";
import Login from "./pages/Login";
import { Route, Routes } from "react-router-dom";
import KakaoLogin from "./pages/loginpages/KakaoLogin";
import StatCtrl from "./pages/mainpages/hampages/statuspages/StatCtrl";

function App() {
  return (
    <Routes>
      <Route index element={<Login />} />
      <Route path="/kakaoLogin" element={<KakaoLogin />} />

      <Route path="/main" element={<Main />} />
      <Route path="/statctrl" element={<StatCtrl />} />
    </Routes>
  );
}

export default App;
