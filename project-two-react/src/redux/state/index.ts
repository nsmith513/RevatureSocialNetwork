import { combineReducers } from 'redux';
import IUser from '../../models/IUser';
import { profileReducer } from '../reducers/profile-reducer';
import IPost from '../../models/IPost';
import {searchSuggestionReducer} from '../reducers/suggestion-reducer'

import { feedReducer } from '../reducers/feed-reducer';


export interface IAppState {
    profileState: IUser;
    userSuggestions: IUser[];
    feedState: IPost[];
}

export const state = combineReducers<IAppState>({
    profileState: profileReducer,
    userSuggestions: searchSuggestionReducer,
    feedState: feedReducer,
})
