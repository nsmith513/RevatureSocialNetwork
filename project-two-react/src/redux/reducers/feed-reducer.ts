import { FeedActions, IFeedAction } from "../actions/feed-action";
import IPost from "../../models/IPost";
import IUser from "../../models/IUser";

// const initialUserState:IUser = {
//     id: -1,
//     uname:"",
//     email:"",
//     pfp:""
// }

const initialPostState:IPost[] = [];

export const feedReducer = (state = initialPostState, action:IFeedAction): IPost[] => {

    const {type, payload} = action;

    switch(type) {
        case FeedActions.SET_FEED:
            return (payload || payload === []) ? payload : state;
        default:
            return state;    
    }
}
