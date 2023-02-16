// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyDu4fgNENe7TEn-4Q8aLtmQ-BYmZWmA3KI",
  authDomain: "teamrestructuring-8b91c.firebaseapp.com",
  projectId: "teamrestructuring-8b91c",
  storageBucket: "teamrestructuring-8b91c.appspot.com",
  messagingSenderId: "616408032515",
  appId: "1:616408032515:web:c778e4b645c57e17783536",
  measurementId: "G-MHGNPF4VQF"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);

export default app