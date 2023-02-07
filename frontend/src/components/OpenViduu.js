import React from 'react';
import { OpenVidu } from "openvidu-browser";
// import { getIdToken } from 'firebase/auth';

const OpenViduu = () => {
    // 1. 객체 생성
    var OV = new OpenVidu()
    // 2. 소켓 통신과정에서 log를 띄우지 않게 하는 모드
    OV.enableProdMode()
    // 3. initsession 생성
    var session = OV.initSession();

    // 4. session에 connect하는 과정
    const connection = () => {
        // 4-a token 생성
        getToken(init)
        
    }
    return (
        <div>
            
        </div>
    );
};

export default OpenViduu;