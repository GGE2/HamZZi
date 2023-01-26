import "./App.css";
import Main from "./pages/Main";
import Login from "./pages/Login";
import { Route, Routes } from "react-router-dom";
import Sendtest from './pages/Sendtest';

function App() {
  return (
    // <div>
    //   <Sendtesasdsadt />
    // </div>
    <Routes>
      <Route path="/main" element={<Main />} />
      <Route path="/" element={<Login />} />
    </Routes>
  );
}

export default App;
