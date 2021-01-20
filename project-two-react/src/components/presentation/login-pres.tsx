import React, { useState } from 'react';
import '../styles/login-pres.css';
import { Link } from 'react-router-dom';
import { useDispatch } from 'react-redux';

interface IProps {
    handleSubmit(event: React.FormEvent<HTMLFormElement>): void;
    setUsername: Function;
    setPassword: Function;
    username: string;
    password: string;
}

export const LoginPres: React.FC<IProps> = (props:IProps) =>{

    return (
        <div className="login">
            <div className="d-flex justify-content-center">
                <div className="card login-card">
                    <div className="card-header">
                        <h3 className="loginCardHeader">User Login</h3>
                    </div>
                    <div className="card-body">

                        <form onSubmit={props.handleSubmit}>
                            <div className="mb-3">

                                <label htmlFor="inputUsername1" className="form-label ">Username</label>
                                <input placeholder="Username" onChange={(evt) => props.setUsername(evt.target.value)} type="text" className="form-control form-control-lg" id="input-username" name="username" value={props.username} />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="inputPassword1" className="form-label">Password</label>
                                <input placeholder="Password" onChange={(evt) => props.setPassword(evt.target.value)} type="password" className="form-control form-control-lg" id="input-password" name="password" value={props.password} />
                            </div>
                            <div className="row">
                                <div className="col">

                                    <button type="submit" className="btn btn-lg btn-outline-light loginSubmitButton" id="input-creds">Submit</button>

                                </div>
                                <div className="col text-right">
                                    <Link to="/register"><button className="btn btn-lg btn-outline-warning registerButton goToRegisterButton">Register</button></Link>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    );

    

}
