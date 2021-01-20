import IUser from "../../models/IUser"

export const ProfileActions = {
    SET_PROFILE: 'SET_PROFILE'
}

export const ProfileActionMapper = (profile: IUser) => {
    return {
        type: ProfileActions.SET_PROFILE,
        payload: profile
    }
}
