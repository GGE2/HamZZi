import React, {useRef, useState} from 'react';

const DiaryEditor = ({onCreate}) => {
    // const [author, setAuthor] = useState("") // 작성자
    // const [content, setContent] = useState("") // 본문

    const authorInput = useRef();
    const contentInput = useRef();

    const [state, setState] = useState({
        author: "",
        content: "",
    })

    const handleChangeState= (e) => {
        setState({
            ...state,
            [e.target.name] : e.target.value
        })
    }

    const handleSubmit = () => {
        if (state.author.length < 1) {
            // focus
            authorInput.current.focus()
            return
        }

        if (state.content.length < 1) {
            // focus
            contentInput.current.focus()
            return
        }
        onCreate(state.author, state.content)
        setState({
            author: "",
        content: "",
        })
    }
    

    return (
        <div className='DiaryEditor'>
            <h2>방명록</h2>
            <div> 
                {/* 작성자 */}
                <input name="author" ref={authorInput} value={state.author} onChange={handleChangeState}></input>
            </div>
            <div>
                {/* 본문 */}
                <textarea name="content" ref={contentInput} value={state.content} onChange={handleChangeState}></textarea>
            </div>
            <div>
                <button onClick={handleSubmit}>작성 완료</button>
            </div>
        </div>
    );
};

export default DiaryEditor;