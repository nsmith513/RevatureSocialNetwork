import axios from 'axios';
import React, { useEffect, useReducer } from 'react';
import { ProfilePres } from '../presentation/profile-pres';
import { useDispatch, useSelector } from 'react-redux';
import { IAppState } from '../../redux/state/index';
import { ProfileActionMapper } from '../../redux/actions/profile-action';
import IUser from '../../models/IUser';

interface IProps {
    userId?: number
}

export const  ProfileCont: React.FC<IProps> = (props:IProps) =>{

    const dispatch = useDispatch();

    const profData = useSelector<IAppState, IUser>(state => state.profileState);

    useEffect(() => {
        // get by user if userId is a prop, use logged in user if no userId is passed
        if (props.userId) {
            axios.get(process.env.REACT_APP_API_URL + `ProjectTwoSTS/api/user/getById/${props.userId}`)
            .then(response => dispatch(ProfileActionMapper(response.data)))
            .catch(err => console.error(err));
        } else {
            //@ts-ignore
            dispatch(ProfileActionMapper(JSON.parse(localStorage.getItem('user'))));
        }
    }, []);

    return (<>
        <ProfilePres user={profData} />
    </>);
}
