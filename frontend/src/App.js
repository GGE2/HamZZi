import "./App.css";
import Main from "./pages/Main";
import Login from "./pages/Login";
import { Route, Routes } from "react-router-dom";
<<<<<<< HEAD
import KakaoLogin from './pages/loginpages/KakaoLogin';

function App() {
  return (
    // KakaoLogin
=======
import Sendtest from './pages/Sendtest';

function App() {
  return (
>>>>>>> feature/mobile/homepage
    // <div>
    //   <Sendtest />
    // </div>
    <Routes>
      <Route path="/main" element={<Main />} />
      <Route path="/" element={<Login />} />
<<<<<<< HEAD
      <Route path="/kakaoLogin" element={<KakaoLogin />} />
=======
>>>>>>> feature/mobile/homepage
    </Routes>
  );
}

export default App;
