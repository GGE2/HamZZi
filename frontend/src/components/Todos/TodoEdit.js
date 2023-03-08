import React, {useRef, useState} from 'react';

const TodoEdit = ({onCreate}) => {
    const [content, setContent] = useState("") // 본문

    const contentInput = useRef();

    const handleChangeState= (e) => {
        setContent(e.target.value)
    }

    const handleSubmit = () => {
        if (content.length < 1) {
            // focus
            contentInput.current.focus()
            return
        }
        onCreate(content)
        setContent("")
    }
    

    return (
        <div className='DiaryEditor'>
            <h2>방명록</h2>
            
            <form onSubmit={handleSubmit}>
                <textarea name="content" ref={contentInput} value={content} onChange={handleChangeState}></textarea>
            </form>
            <div>
                <button onClick={handleSubmit}>작성 완료</button>
            </div>
        </div>
    );
};

export default TodoEdit;