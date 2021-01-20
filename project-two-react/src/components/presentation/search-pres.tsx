import React from 'react';
import '../styles/search-pres.css';

interface IProps {

}

export const SearchPres: React.FC<IProps> = (props:IProps) =>{
    return(
        <>
           <ul className="custom-search-list">
               <li>
                <div className="card-body custom-card-list shadow-lg p-3">
                        <div className="clearfix">
                            <img className='avatar img-thumbnail pull-left custom-img-list'/> 
                            <h5 className="card-title">Bob Burgers</h5>
                        </div>
                    </div>
               </li>
               <br />
               <li>
                <div className="card-body custom-card-list shadow-lg p-3">
                        <div className="clearfix">
                            <img className='avatar img-thumbnail pull-left custom-img-list'/> 
                            <h5 className="card-title">Bob Rodgers</h5>
                        </div>
                    </div>
               </li>
           </ul>
        </>
    );
}