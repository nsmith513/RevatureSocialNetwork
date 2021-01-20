import React from 'react';
import {Header} from './navbar/Header';
import './app-container.css';

interface IProps {
    children:any;
}

export const AppContainer: React.FC<IProps> = (props:IProps) => {
    return (
        <>
            <div className='container app-container'>
                {props.children} {/* all elements contained within this element */}
            </div>
        </>
    );
}


