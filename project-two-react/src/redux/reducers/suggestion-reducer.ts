import { UserSearchSuggestionActions } from "../actions/search-suggestion-action";
import IPost from "../../models/IPost";
import IUser from "../../models/IUser";


const initialSuggestionState:IUser[] = [];

export const searchSuggestionReducer = (state = initialSuggestionState, action:any): IUser[] => {

    const {type, payload} = action;

    switch(type) {
        case UserSearchSuggestionActions.SET_SUGGESTED_USERS:
            return payload ? payload : state;
        default:
            return state;    
    }
}