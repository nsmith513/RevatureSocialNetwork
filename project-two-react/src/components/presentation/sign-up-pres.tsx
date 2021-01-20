import React, {useState, useRef} from 'react';
import axios from 'axios';
import "../styles/sign-up-pres.css";
const S3 = require('react-aws-s3');

interface IProps {
    handleSubmit(event: React.FormEvent<HTMLFormElement>): void;
    onContentChange(event: any): void;
    setUsername: Function;
    setPassword: Function;
    setPassword2: Function;
    setEmail: Function;
    setPasswordCheck: Function;
    username: string;
    password: string;
    password2:string;
    email: string;
    passwordCheck: string;
    fileInput:any;
}

const config = {
    bucketName: process.env.REACT_APP_BUCKET_NAME,
    region: process.env.REACT_APP_REGION,
    accessKeyId: process.env.REACT_APP_ACCESS_ID,
    secretAccessKey: process.env.REACT_APP_ACCESS_KEY,
};


export const SignUpPres: React.FC<IProps> = (props:IProps) => {
    return (
        <form onSubmit={props.handleSubmit} >
        <div className="d-flex justify-content-center">
            <div className="card register-card registerCard">
                <div className="card-header">
                    <h3 className="registerNewUserHeader">Register</h3>
                </div>
                    <div className="mb-3">
                        <label htmlFor="inputUsername1" className="form-label  ">Username</label>
                        <input placeholder="Username" value={props.username} onChange={(evt) => props.setUsername(evt.target.value)} type="text" className="form-control form-control-lg" id="input-username" name="username" required/>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="inputEmail1" className="form-label">Email</label>
                        <input placeholder="Email" value={props.email} onChange={(evt) => props.setEmail(evt.target.value)} type="email" className="form-control form-control-lg" id="input-email" name="email" required/>
                    </div>
                    <div className="mb-3" style={{color:"red"}}>
                        {props.passwordCheck}
                    </div>
                    <div className="mb-3">
                        <label htmlFor="inputPassword1" className="form-label">Password</label>
                        <input placeholder="Password" value={props.password} onChange={(evt) => props.setPassword(evt.target.value)} type="password" className="form-control form-control-lg" id="input-password" name="password" required/>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="inputConfirmPassword1" className="form-label">Confirm Password</label>
                        <input placeholder="Confirm Password" value={props.password2} onChange={(evt) => props.setPassword2(evt.target.value)}type="password" className="form-control form-control-lg" id="input-confirm-password" name="password2" required/>
                    </div>

                    <br/>
                    <div className="form-group">
                        <label htmlFor="profilePicture">Add a Profile Picture</label>
                        <input ref={props.fileInput} type="file" className="form-control-file" id="exampleFormControlFile1" required/>
                    </div>
                    <br/>


                    <button type="submit" className="btn btn-lg btn-outline-warning" id="input-creds">Create Account</button>
                    </div>
                </div>
            </form>
    );
}