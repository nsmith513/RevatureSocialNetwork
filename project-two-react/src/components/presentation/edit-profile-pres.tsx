import React from 'react';
import IUser from '../../models/IUser';
import '../styles/edit-profile-pres.css';

interface IUserModel {
        id:number,
        uname:string,
        pwd:string,
        email:string,
        pfp:string
}

interface IProps {
    handleSubmit(event: React.FormEvent<HTMLFormElement>): void;
    setUsername: Function;
    setPassword: Function;
    setEmail: Function;
    username: string;
    password: string;
    email: string,
    userData: IUserModel;
    fileInput:any;
}

export const EditProfilePres: React.FC<IProps> = (props:IProps) => {
    return(
        <>
         <form onSubmit={props.handleSubmit}>
            <div className="card edit-profile-card">
                    <div className="card-body userProfile">

                    <h1>Update Profile</h1>
                        
                            <img className='avatar updateProfileAvatarImage  edit-custom-img' src={props.userData.pfp}/> 

                            <div className="">
                                <label htmlFor="formFileSm" className="form-label">Change your profile picture</label>
                                <input ref={props.fileInput} className="updateProfileImage form-control form-control-sm" id="formFileSm" type="file" />
                            </div>

                            <div className="mb-3">
                                <label htmlFor="exampleFormControlInput1" className="form-label">Change Username</label>
                                <input value={props.username} onChange={(evt) => props.setUsername(evt.target.value)} type="text"  name="username" className="form-control" id="exampleFormControlInput1" placeholder={props.userData.uname} />
                            </div>

                            <div className="mb-3">
                                <label htmlFor="exampleFormControlInput2" className="form-label">Change Email</label>
                                <input value={props.email} onChange={(evt) => props.setEmail(evt.target.value)} type="email"  name="email" className="form-control" id="exampleFormControlInput2" placeholder={props.userData.email} />
                            </div>

                            <div className="mb-3">
                                <label htmlFor="exampleFormControlInput2" className="form-label">Change Password</label>
                                <input value={props.password} onChange={(evt) => props.setPassword(evt.target.value)} type="password"  name="password" className="form-control" id="exampleFormControlInput3" placeholder='password' />
                            </div>

                            <div className="mb-3 profileSubmitButtonContainer">
                                <button type="submit" className="btn btn-outline-light mb-3 updateProfileSubmitButton">Update Info</button>
                            </div>
                                    
                            <br/>
                        
                    </div>
                </div>
            </form>
        </>
    )
}