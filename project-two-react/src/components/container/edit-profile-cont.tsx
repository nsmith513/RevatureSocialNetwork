import React, { useEffect, useState, useRef } from 'react';
import axios from 'axios';
const S3 = require('react-aws-s3');
import {EditProfilePres} from '../presentation/edit-profile-pres';
import { IAppState } from '../../redux/state';

interface IUserModel {
    user: {
        id:number,
        uname:string,
        pwd:string,
        email:string,
        pfp:string
    }
}

const config = {
    bucketName: process.env.REACT_APP_BUCKET_NAME,
    region: process.env.REACT_APP_REGION,
    accessKeyId: process.env.REACT_APP_ACCESS_ID,
    secretAccessKey: process.env.REACT_APP_ACCESS_KEY,
};

interface IProps {
  
}

export const EditProfileCont: React.FC<IProps> = (props:IProps): JSX.Element => {
    const [userData, setUserData] = useState<IUserModel['user']>({id:0,uname:'',pwd:'',email:'',pfp:''});
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');
    const fileInput = useRef<HTMLInputElement>(null);


    const handleSubmit = (evt:React.FormEvent<HTMLFormElement>) => {
        evt.preventDefault();
        // @ts-ignore
        let file = fileInput.current.files[0];
    
        if(file) {
            const ReactS3Client = new S3(config);
            ReactS3Client.uploadFile(file).then((data:any) => {
            console.log(data);
                if (data.status === 204) {
                    console.log("success");
                    const update = {
                        id: userData?.id,
                        uname: username,
                        pwd: password,
                        email: email,
                        pfp: data.location
                    }
                    axios.put(process.env.REACT_APP_API_URL + 'ProjectTwoSTS/api/user/update',update)
                    .then(user => {
                        //@ts-ignore
                        let userObject = JSON.parse(localStorage.getItem('user'));
                        userObject.uname = update.uname;
                        userObject.pwd = update.pwd;
                        userObject.email = update.email;
                        userObject.pfp = update.pfp;
                        localStorage.setItem('user', JSON.stringify(userObject));
                    })
                    .catch(err => console.log(err)
                    );
                } else {
                    console.log("fail");
                }
            });
        } else {
                const update = {
                    id: userData?.id,
                    uname: username,
                    pwd: password,
                    email: email,
                    pfp: userData?.pfp
                }
                axios.put(process.env.REACT_APP_API_URL + 'ProjectTwoSTS/api/user/update',update)
                .then(user => {
                    //@ts-ignore
                    let userObject = JSON.parse(localStorage.getItem('user'));
                    userObject.uname = update.uname;
                    userObject.pwd = update.pwd;
                    userObject.email = update.email;
                    localStorage.setItem('user', JSON.stringify(userObject));
                })
                .catch(err => console.log(err)
                );
        }
    }

    useEffect(() => {
        //@ts-ignore
        setUserData(JSON.parse(localStorage.getItem('user')));
   }, []);

    return <EditProfilePres
             handleSubmit={handleSubmit}
             setUsername={setUsername}
             setPassword={setPassword}
             setEmail={setEmail}
             username={username}
             password={password}
             email={email}
             userData={userData}
             fileInput={fileInput}
            />;
}
