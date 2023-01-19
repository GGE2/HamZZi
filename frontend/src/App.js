import "./App.css";
import { Routes, Route } from "react-router-dom";
import Login from "./Login";
import LayOut from "./components/Layout";
import Public from "./components/Public";

function App() {
  return (
    <div className="app">
      <Routes>
        <Route path="/" element={<LayOut />}>
          {/* public routes */}
          <Route index element={<Public />} />
          <Route path="login" element={<Login />} />

          {/* protected routes */}
          {/* <Route element={<RequireAuth />}>
              <Route path="welcome" element={<Welcome />} />
            </Route> */}
        </Route>
      </Routes>
    </div>
  );
}

export default App;
