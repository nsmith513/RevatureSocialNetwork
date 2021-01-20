import axios from 'axios';
import React, { useEffect, useState } from 'react';
import IPost from '../../models/IPost';
import {PostPres} from '../presentation/post-pres';

interface IProps {
    post:IPost;
}

export const PostCont: React.FC<IProps> = (props:IProps) =>{

    const [onOff, setonOff] = useState<Boolean>(false);
    const [likeCount, setlikeCount] = useState(props.post.likes.length);
    const [image, setImage] = useState(props.post.images[0]||'');

    // @ts-ignore
    const user = JSON.parse(localStorage.getItem('user'))

    const likeButtonClick = (evt:any) => {
        evt.preventDefault();
       
        if(onOff === false) {     
            axios.put(process.env.REACT_APP_API_URL + `ProjectTwoSTS/api/post/like?user=${user.id}&post=${props.post.id}`)
            .then((res) => {
                console.log(res)
                setonOff(true);
                setlikeCount(likeCount + 1);
            })
            .catch(err => console.log(err));
        } else {
                axios.put(process.env.REACT_APP_API_URL + `ProjectTwoSTS/api/post/unlike?user=${user.id}&post=${props.post.id}`)
                .then((res) => {
                    console.log(res)
                    setonOff(false);
                    setlikeCount(likeCount -1);
                })
                .catch(err => console.log(err));
            }
        }

    useEffect(() => {
        props.post.likes.forEach((index) => {
            if(user.id === index.id) {
                setonOff(true);
            }
        })
    },[])

    return (
           <PostPres post={props.post} likeButtonClick={likeButtonClick} onOff={onOff} likeCount={likeCount} />
    );
}