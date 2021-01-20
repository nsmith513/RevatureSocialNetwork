import React, {useState, useRef} from 'react';
import axios from 'axios';
import { SignUpPres } from '../presentation/sign-up-pres';
const S3 = require('react-aws-s3');

const config = {
    bucketName: process.env.REACT_APP_BUCKET_NAME,
    region: process.env.REACT_APP_REGION,
    accessKeyId: process.env.REACT_APP_ACCESS_ID,
    secretAccessKey: process.env.REACT_APP_ACCESS_KEY,
};

interface IProps {

}

export const SignUpCont: React.FC<IProps> = (props:IProps) => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [password2, setPassword2] = useState('');
    const [email, setEmail] = useState('');
    const [passwordCheck, setPasswordCheck] = useState('');

    const fileInput = useRef<HTMLInputElement>(null);

    const onContentChange = (evt:any) => {
        //setPostValue(evt.target.value)

        if(evt.target.name === 'username') {
            setUsername(evt.target.value)
        } else if (evt.target.name === 'email') {
            setEmail(evt.target.value)
        } else if (evt.target.name === 'password') {
            setPassword(evt.target.value)
        } else if (evt.target.name === 'password2') {
            setPassword2(evt.target.value)
        }
    }

    const handleSubmit = (evt:any) => {
        evt.preventDefault();

        if(password == password2) {
             // @ts-ignore
            let file = fileInput.current.files[0];
            // @ts-ignore
            let newFileName = fileInput.current.files[0].name.replace(/\..+$/, "");

            const ReactS3Client = new S3(config);
            ReactS3Client.uploadFile(file,newFileName).then((data:any) => {
            console.log(data);
            if (data.status === 204) {
                console.log("success");
                const register = {
                    uname: username,
                    pwd: password,
                    email: email,
                    pfp: data.location
                }
                axios.post(process.env.REACT_APP_API_URL + 'ProjectTwoSTS/api/user/register',register)
                .then(res => console.log(res))
                .catch(err => console.log(err)
                );
            } else {
                console.log("fail");
            }
        });

        } else {
            setPasswordCheck('* Make sure the password and confirm password are the same')
        }
    }

    return <SignUpPres 
        handleSubmit={handleSubmit}
        onContentChange={onContentChange}
        setUsername={setUsername}
        setPassword={setPassword}
        setPassword2={setPassword2}
        setEmail={setEmail}
        setPasswordCheck={setPasswordCheck}
        username={username}
        password={password}
        password2={password2}
        email={email}
        passwordCheck={passwordCheck}
        fileInput={fileInput}
    />;
}