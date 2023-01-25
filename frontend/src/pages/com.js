import React from 'react';
import axios from 'axios';
import { async } from '@firebase/util';

const com = () => {
    


    async function send() {
        try {
            // get 요청은 param에 실어 보냄
            const res = await axios.get('/user',{
                params: {
                    id: 12345
                }
            })

            // 응답 결과를 변수에 저장 등
            await axios.get('/user?id=12345')
            console.log(res)

        }
        catch (e) {
            // 실패 시 처리
            console.log(e)
        }
    }    

    return (
        <div>
            
        </div>
    );
};

export default com;
