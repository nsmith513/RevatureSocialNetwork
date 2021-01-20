import React ,{useEffect, useState} from 'react';
import axios from 'axios';
import { useDispatch, useSelector } from 'react-redux';
import IUser from '../../../models/IUser';
import {UserSearchSuggestionMapper} from '../../../redux/actions/search-suggestion-action';

import SearchSuggestion from './SearchSuggestion';
interface IProps {
    searchQuery?: any
}

import {store} from '../../../redux/store';
import { Link,  Redirect } from 'react-router-dom';


export const NavSearch: React.FC<IProps> = (props:IProps) =>{

    const dispatch = useDispatch();

    const [searchValue, setSearchValue] = useState("");
    const [userSuggestions, setUserSuggestions] = useState([])

    useEffect(()=>{
        let t1:any;    
        window.clearTimeout(t1)
        if(searchValue !== "" ){
            t1= window.setTimeout(suggestionSearch, 1250);
        }
        return () =>clearTimeout(t1)

    },[searchValue]);




///////////On change search input
const searchChange = function(searchValues:any) {

    setUserSuggestions([])
    dispatch(UserSearchSuggestionMapper([]))
    setSearchValue(searchValues.nativeEvent.srcElement.value)

}

/////Suggestion search that triggers after timeout
//axios call to get a list of users from backend. 
const suggestionSearch = function(searchValues:any){
    
    console.log("suggestion search")
    console.log(searchValue)

    axios.get(`${process.env.REACT_APP_API_URL}/ProjectTwoSTS/api/user/search/`+searchValue).then(res=>{
        const returnedUser = res.data;
        if(returnedUser ==""){
            console.log("No user by that username")
        }else{
            console.log(returnedUser);
                        

            dispatch(UserSearchSuggestionMapper(returnedUser))
            setUserSuggestions(returnedUser);
            // console.log("------")
            // console.log(store.getState().userSuggestions)
            returnedUser.forEach((elem: any)=>{
                //store each user to redux
                console.log(elem);
            })
        
        }
    })
    

}

/////////Click Submit Button
const onSearchSubmit = function(event:any){
    
    let searchValue = event.nativeEvent.srcElement.form[0].value

    event.preventDefault();
    
    console.log("Submitting!");
    console.log("Search Query: " +event.nativeEvent.srcElement.form[0].value);

    axios.get(`${process.env.REACT_APP_API_URL}/ProjectTwoSTS/api/user/getByUname/`+searchValue).then(res=>{
        const returnedUser = res.data;
        if(returnedUser ==""){
            console.log("No user by that username")
        }else{

            console.log(returnedUser);
        }
    })

}

/////Suggestion reveal, displays divs below search with links to suggested profiles

const  suggestionReveal = function(){


    let returnedUsersArray:Array<any> = [];

    userSuggestions.forEach((elem:IUser)=>{

        console.log(elem)
        returnedUsersArray.push(elem);
        
    })

    console.log(returnedUsersArray)
    console.log(returnedUsersArray.map(user =><Link key={user.id} to={`/user/${user.id}`}>{user.uname}</Link>))
    //  return(<div className="suggestionsContainer">{returnedUsers}</div>)
    return returnedUsersArray;

}


    return (
        <>
            <form className="d-flex" id="navSearchForm">
                <div > 

                <input id="navSearch" className="form-control  me-2 form-control-lg" type="search" placeholder="Search Profiles" aria-label="Search" onChange={searchChange} />
                
                <div id="searchContainerDiv" className="searchContainerDiv">
                { userSuggestions.length !=0 ? suggestionReveal().map(user =><Link className="suggestionsContainer" key={user.id} to={`/user/${user.id}`}><img className="suggestionAvatar" src={user.pfp}></img>{user.uname}</Link>):<></>}

                </div>

                </div>

            </form>
        </>
    );
}