import axios from 'axios';
import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import IPost from '../../models/IPost';
import { FeedActionMapper } from '../../redux/actions/feed-action';
import { IAppState } from '../../redux/state';
import { FeedPres } from '../presentation/feed-pres';
import { PostCont } from './post-cont';

interface IProps {
    userId?: number
}

export const FeedCont: React.FC<IProps> = (props:IProps) => {

    const dispatch = useDispatch();
    
    const feedData = useSelector<IAppState, IPost[]>(state => state.feedState);
    
    // get by user if userId is a prop, get all if userId was not set as an attribute
    const reqUri = props.userId ?
        `ProjectTwoSTS/api/post/getByUser/${props.userId}` :
        'ProjectTwoSTS/api/post/getAll'
    useEffect(() => {
        axios.get(process.env.REACT_APP_API_URL + reqUri)
        .then(response => {
            dispatch(FeedActionMapper(response.data))
        })
        .catch(err => console.error(err));
    }, []);

    return (
        <FeedPres>
            {feedData.map(post => <PostCont key={post.id} post={post}/>)}
        </FeedPres>
    );
}
