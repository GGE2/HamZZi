import React from 'react';
import { FaCrown } from "react-icons/fa";

const GuildPerson = ({person, length}) => {
    return (
        <div className='GuildPerson'>
            <FaCrown />
            {person.name}
        </div>
    );
};

export default GuildPerson;