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

<<<<<<< HEAD
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
=======
  function handleGoogleLogin() {
    const provider = new GoogleAuthProvider(); // provider 구글 설정
    signInWithPopup(auth, provider) // 팝업창 띄워서 로그인
      .then((data) => {
        setUserData(data.user); // user data 설정
        console.log(data); // console에 UserCredentialImpl 출력
        // axios({
        //   method: "post",
        //   url: "/api/user",
        //   data: {
        //     firstName: "Fred",
        //     lastName: "Flintstone",
        //   },
        // });

        // axios.post("api/user").then((res) => {
        //   console.log("api연결");
        //   console.log(res);
        // });

        // setUsers(null);
        axios.get("https://jsonplaceholder.typicode.com/users").then((res) => {
          console.log(res.data);
          console.log(res);
        });

        // console.log(res.data)
      })
      .catch((err) => {
        console.log(err);
      });
>>>>>>> feature/mobile/homepage
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
<<<<<<< HEAD
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
=======
    <div>
      <button onClick={handleGoogleLogin}>
        <img src="btn_google_signin_dark_normal_web.png" alt="" />
      </button>
      <div>
        {userData
          ? "당신의 이름은 : " + userData.displayName
          : "로그인 버튼을 눌러주세요 :)"}
      </div>
      <button onClick={handleGoogleLogout}>로그아웃</button>
    </div>
>>>>>>> feature/mobile/homepage
  );
}

export default Google;
