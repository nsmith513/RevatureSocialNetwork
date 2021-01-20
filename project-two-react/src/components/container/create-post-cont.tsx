import axios from 'axios';
import React, { useRef, useState } from 'react';
import { CreatePost } from '../presentation/create-post-pres';
const S3 = require('react-aws-s3');

interface IProps {

}

const config = {
    bucketName: process.env.REACT_APP_BUCKET_NAME,
    region: process.env.REACT_APP_REGION,
    accessKeyId: process.env.REACT_APP_ACCESS_ID,
    secretAccessKey: process.env.REACT_APP_ACCESS_KEY,
};

export const CreatePostCont: React.FC<IProps> = (props: IProps) => {

    const [postContent, setPostValue] = useState('');
    const fileInput = useRef<HTMLInputElement>(null);

    const handleSubmit = (evt: any) => {
        evt.preventDefault();

        // @ts-ignore
        let file = fileInput.current.files[0];
        
        if(file) {
            const ReactS3Client = new S3(config);
            ReactS3Client.uploadFile(file).then((data: any) => {
            if (data.status === 204) {
                console.log("success");
                const userPost = {
                    content: postContent,
                    images: [
                        { link: data.location }
                    ],
                    video: null,
                    //@ts-ignore
                    user: JSON.parse(localStorage.getItem('user')).id
                }

                axios.post(process.env.REACT_APP_API_URL + 'ProjectTwoSTS/api/post/create', userPost)
                    .then(res => console.log(res))
                    .catch(err => console.log(err)
                    );
            } else {
                console.log("fail");
            }
        });
        } else {
            const userPost = {
                content: postContent,
                images: null,
                video: null,
                //@ts-ignore
                user: JSON.parse(localStorage.getItem('user')).id
            }

            axios.post(process.env.REACT_APP_API_URL + 'ProjectTwoSTS/api/post/create', userPost)
                .then(res => console.log(res))
                .catch(err => console.log(err)
                );
        }
        
    }

    return <CreatePost
        handleSubmit={handleSubmit}
        postContent={postContent}
        setPostValue={setPostValue}
        fileInput={fileInput}
    />;
}