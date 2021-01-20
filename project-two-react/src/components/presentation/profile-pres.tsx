import React from 'react';
import '../styles/profile-pres.css';

interface IProps {
    user : {
        uname:string,
        email:string,
        pfp:string
    }
}

export const ProfilePres : React.FC<IProps> = (props:IProps) => {
    return props.user ?
        <>
            <div className="container container-profile">
                <h3 className="profileHeader">Profile</h3>
                <div className="row">
                    <div className="col-sm leftProfileColumn">
                        <img className='avatar' src={props.user.pfp} alt="PFP" height="100rem"/>
                        < hr className="horizontalRule" />

                    </div>
                    <div className="col-sm rightProfileColumn">
                    <span className="profileUserName" >Username: <br/><span className="userProfileInfo">{props.user.uname}</span></span>
                        <br/>
                        Email: <br/><span className="userProfileInfo">
                        {props.user.email} </span>
                    </div>
                </div>
            </div>
        </> :
        <>
            <p>No user</p>
        </>;
}
