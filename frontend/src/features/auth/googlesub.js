import { useContext } from "react";
import AuthContext from "../../context/AuthContext";
import GoogleButton from "react-google-button";

const LoginTest = () => {
  const context = useContext(AuthContext);
  const { user, isLoggedIn, logIn, logOut } = context;

  return (
    <div>
      <h1>구글 소셜 로그인 구현</h1>
      <h2>유저 상태: {isLoggedIn ? "로그인" : "로그아웃"}</h2>
      <p>유저 이름: {user?.displayName}</p>
      {isLoggedIn ? (
        <button onClick={logOut}>로그아웃</button>
      ) : (
        <GoogleButton onClick={logIn} />
      )}
    </div>
  );
};

export default LoginTest;
