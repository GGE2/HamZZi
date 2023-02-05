import "./App.css";
import Main from "./pages/Main";
import Login from "./pages/Login";
import { Route, Routes } from "react-router-dom";
import KakaoLogin from "./pages/loginpages/KakaoLogin";
<<<<<<< HEAD
import RequireAuth from "./pages/loginpages/RequireAuth";
import SetNickName from "./pages/loginpages/SetNickName";
import Prologue from "./pages/mainpages/Prologue";
import PetName from "./pages/loginpages/PetName";
=======
import StatCtrl from "./pages/mainpages/hampages/statuspages/StatCtrl";
>>>>>>> d71fbd50837b97bd096f25692dba0e251672d352

function App() {
  return (
    <Routes>
      <Route index element={<Login />} />
      <Route path="/kakaoLogin" element={<KakaoLogin />} />

<<<<<<< HEAD
      <Route element={<RequireAuth />}>
        <Route path="/main" element={<Main />} />
        <Route path="/prologue" element={<Prologue />} />
        <Route path="/nickname" element={<SetNickName />} />
        <Route path="/petname" element={<PetName />} />
      </Route>
=======
      <Route path="/main" element={<Main />} />
      <Route path="/statctrl" element={<StatCtrl />} />
>>>>>>> d71fbd50837b97bd096f25692dba0e251672d352
    </Routes>
  );
}

export default App;
