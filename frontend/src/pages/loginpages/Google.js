import { auth } from "../../Firebase";
import { GoogleAuthProvider, signInWithPopup, signOut } from "firebase/auth";
import { useState, useEffect } from "react";
import axios from "axios";

function Google() {
  const [userData, setUserData] = useState(null);
  // const [realData, setrealData] = useState({
  //   email: "",
  //   password: "",
  //   telephone: "",
  //   name: "",
  //   gender: "",
  // });

  const [users, setUsers] = useState(null);

  async function handleGoogleLogin() {
    try {
      const provider = new GoogleAuthProvider(); // provider 구글 설정
      const data = await signInWithPopup(auth, provider); // 팝업창 띄워서 로그인

      setUserData(data.user); // user data 설정
      console.log(data); // console에 UserCredentialImpl 출력

      const dummy = await axios.post("http://3.35.88.23:8080/api/user", {
        email: data.user.email,
        name: data.user.displayName,
      });
      console.log(dummy)
    } catch (err) {
      console.log(err);
    }
  }

  // useEffect(() => {
  //   const fetchUsers = async () => {
  //     try {
  //       // 요청이 시작 할 때에는 error 와 users 를 초기화하고
  //       // setError(null);
  //       console.log('google')
  //       setUsers(null);
  //       // loading 상태를 true 로 바꿉니다.
  //       // setLoading(true);
  //       const response = await axios.get(
  //         'https://jsonplaceholder.typicode.com/users'
  //       );
  //       console.log(response.data)
  //       setUsers(response.data); // 데이터는 response.data 안에 들어있습니다.
  //     } catch (e) {
  //       // setError(e);
  //     }
  //     // setLoading(false);
  //   };

  //   fetchUsers();
  // }, []);

  function handleGoogleLogout() {
    signOut(auth)
      .then(() => {
        console.log(userData);
        console.log("로그아웃");
      })
      .catch((err) => {
        console.log(err);
      });
  }

  return (
    <>
      <img
        className="login_btn"
        onClick={handleGoogleLogin}
        src="google.png"
        alt=""
      />
      <div>{userData ? "당신의 이름은 : " + userData.displayName : ""}</div>
      {/* <button onClick={handleGoogleLogout}>로그아웃</button> */}
    </>
  );
}

export default Google;
