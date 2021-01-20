import { InstanceProfiles } from "aws-sdk/clients/devicefarm";
import IUser from "../../models/IUser";
import { ProfileActions } from "../actions/profile-action";

const initialState:IUser = {
    id:-1,
    uname:"",
    email:"",
    pfp:""
}

export const profileReducer = (state = initialState, action:any): IUser => {

    const {type, payload} = action;

    switch(type) {
        case ProfileActions.SET_PROFILE:
            return {
                ...state,
                ...payload
            }
        default:
            return state;    
    }
}
