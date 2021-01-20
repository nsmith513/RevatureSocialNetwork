import React, { useState } from 'react';
import {LoginPres} from '../presentation/login-pres';
import axios from 'axios';
import { Redirect } from 'react-router-dom';
 
export const LoginCont: React.FC = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        axios.post(process.env.REACT_APP_API_URL + `ProjectTwoSTS/api/user/login?username=${username}&password=${password}`)
            .then(user => {
                localStorage.setItem('user', JSON.stringify(user.data));
                setUsername('');
                setPassword('');
            })
            .catch(err => console.log(err));
    }
    
    return localStorage.getItem('user') ?
        <Redirect to="/" /> :
        <LoginPres
            handleSubmit={handleSubmit}
            setUsername={setUsername}
            setPassword={setPassword}
            username={username}
            password={password}
        />;
}
