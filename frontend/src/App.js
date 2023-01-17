import "./App.css";
import Login from "./features/auth/Login";
import { REST_API_KEY, REDIRECT_URI } from "./KakaoLoginData";

function App() {
  const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}`;

  const handleLogin = () => {
    window.location.href = KAKAO_AUTH_URL;
  };

  return (
    <div className="App">
      <div className="Board">
        <div className="Back">
          <div className="slide1">
            <img src="hamzzi.png" alt="" />
          </div>
          <Login className="slide2" />
        </div>
        <div className="ButtonList">
          <button className="btn"> WHO</button>
          <button className="btn"> THE</button>
          <button className="btn"> HELL</button>
          <button className="btn"> ARE</button>
          <button className="btn"> YOU</button>
          <button className="btn"> ???</button>
        </div>
      </div>
    </div>
  );
}

export default App;
