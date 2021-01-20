import IUser from '../../models/IUser'

export const UserSearchSuggestionActions = {
    SET_SUGGESTED_USERS: 'SET_SUGGESTED_USERS'
}

export const UserSearchSuggestionMapper = ((userSuggestions:IUser[]) =>{

    return{
        type: UserSearchSuggestionActions.SET_SUGGESTED_USERS,
        payload: userSuggestions
    }

})