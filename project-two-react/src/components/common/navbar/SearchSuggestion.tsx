import React from 'react'
import IUser from '../../../models/IUser';

const SearchSuggestion = (props:IUser) => {
    return (
        <div>
            {props.uname}
        </div>
    )
}

export default SearchSuggestion
