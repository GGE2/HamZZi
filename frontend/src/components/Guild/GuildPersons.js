import React from 'react';
import GuildPerson from './GuildPerson';

const GuildPersons = ({persons}) => {
    return (
        <div className='GuildPersons'>
            {persons.map((person) => <GuildPerson key={person.id} person={person} length={person.length} />)}
        </div>
    );
};

export default GuildPersons;