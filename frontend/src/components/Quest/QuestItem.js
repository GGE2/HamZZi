import React from 'react';
import { RiCheckboxBlankCircleLine, RiCheckboxCircleLine} from "react-icons/ri";

const QuestItem = ({content, isCheck}) => {
    return (
        <div>

            <RiCheckboxBlankCircleLine size={"10%"} />
            <RiCheckboxCircleLine size={"10%"} />
            {content}
            <hr />
        </div>
    );
};

export default QuestItem;