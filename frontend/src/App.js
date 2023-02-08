import "./App.css";
import Main from "./pages/Main";
import Login from "./pages/Login";
import { Route, Routes } from "react-router-dom";
import KakaoLogin from "./pages/loginpages/KakaoLogin";
import RequireAuth from "./pages/loginpages/RequireAuth";
import Loginned from "./pages/loginpages/Loginned";
import SetNickName from "./pages/loginpages/SetNickName";
import Prologue from "./pages/mainpages/Prologue";
import PetName from "./pages/loginpages/PetName";

function App() {
  return (
    // KakaoLogin
    // <div>
    //   <Sendtest />
    // </div>
    <Routes>
      <Route element={<Loginned />}>
        <Route index element={<Login />} />
        <Route path="/kakaoLogin" element={<KakaoLogin />} />
      </Route>

      <Route element={<RequireAuth />}>
        <Route path="/main" element={<Main />} />
        <Route path="/prologue" element={<Prologue />} />
        <Route path="/nickname" element={<SetNickName />} />
        <Route path="/petname" element={<PetName />} />
      </Route>
    </Routes>
  );
}

export default App;
