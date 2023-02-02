import React from 'react';

const GuildPerson = ({person, length}) => {
    return (
        <div className='GuildPerson'>
            {person.name}
        </div>
    );
};

export default GuildPerson;