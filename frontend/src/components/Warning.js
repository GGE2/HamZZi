import React from 'react';

const Warning = ({setIsModal, content, content2}) => {

    const onCloseModal = () => {
        setIsModal(false)
    }
    return (
        // <div className='warning'>
            <div className='warningbody'>
            <div>
            {content}
            </div>
            <div>
            {content2}
            </div>
            
            <div className="warningbodybtn" onClick={onCloseModal}>
                <img src="shop/cancelbtn.png" alt="" />
                </div>
            </div>
        // </div>
    );
};

export default Warning;