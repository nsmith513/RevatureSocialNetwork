import React from 'react';
import {SearchPres} from '../presentation/search-pres';

interface IProps {
    
}

var formWidth = {
    width: '25rem',
    margin: '0 auto'
};

export const SearchCont: React.FC<IProps> = (props:IProps) =>{
    return (
        <>
            <form className="d-flex" style={formWidth}>
                <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                <button className="btn btn-outline-primary" type="submit">Search</button>
            </form>
            <br />
            <SearchPres />
        </>
    );
}