import React, {useEffect, useState} from 'react';
import PropTypes from 'prop-types';
import {SearchCont} from '../../container/search-cont';
import {NavSearch} from  './NavSearch';
import { useDispatch, useSelector } from 'react-redux';
import logo2 from './lin_crest2.png';
import {store} from '../../../redux/store';
import './header.css';
import { Link } from 'react-router-dom';

interface IProps {
    logo:any;
}


export const Header: React.FC<IProps> = (props:IProps) => {


    useEffect(() =>{
        if(localStorage.getItem("user")==""){
            setIsUserLoggedIn("no")
        }else{
            setIsUserLoggedIn("yes")
        }
    
    } , [])

    const [isUserLoggedIn, setIsUserLoggedIn] = useState("no");
    const [stateChanged, stateChangeIncrementor] = useState(0);


    const userLogout=function(){

        console.log("User logout function fireds")
        console.log(localStorage.getItem("user"))
        setIsUserLoggedIn("no")
        localStorage.removeItem("user");
        console.log("log out user")
        
    }
    
    const loginLogoutButton = function(){
    
        let loginLogoutButton:any;
    
        let user:any = JSON.parse(localStorage.getItem("user")||"{}");
     
        if(localStorage.getItem("user")){
           console.log("Is logged in")
           ////////
               loginLogoutButton = <div><Link to="/editprofile" ><button className="btn accountButton navButton btn-outline-warning">
                  {user.uname}'s Account</button></Link> <Link to="/login"><button className="btn navButton btn-lg btn-outline-light loginLogoutButton logoutButton" onClick={userLogout}> Logout </button></Link> </div>
           /////////

        } else{
            console.log("is not logged in")
        //    setIsUserLoggedIn("yes")
        loginLogoutButton = <Link to="/login"><button className="btn loginLogoutButton navButton btn-lg btn-outline-light logoutButton"> Login </button></Link>
        }
    
        return(<div className="navUserButtons">{loginLogoutButton}</div>)
    }

    



    return (
        <>
            <nav id="navbarContainer" className="navbar navbar-expand-lg navbar-dark bg-dark">
                <div id="navbar" className="container-fluid">
                    <Link to='/' >
                    
                    <div className="navbar-brand">
                        <img className="mainLogo" src={logo2} alt="logo" height="40rem"/>
                        <span id="navName" className="navName"><span className="firstWord">S</span><span className="firstWord">o</span><span className="firstWord">c</span><span className="firstWord">i</span><span className="firstWord">a</span><span className="firstWord">l</span>
                        <span className="firstWord">M</span>
                        <span className="firstWord">e</span>
                        <span className="firstWord">d</span>
                        <span className="firstWord">i</span>
                        <span className="firstWord">a</span></span>

                    </div>
                    </Link>
                    <Link to='/feed'> <button className="btn feedNavButton navButton btn-lg btn-outline-light">Feed</button> </Link>
                    <NavSearch />
                    <div >{loginLogoutButton()}</div>
                </div>
            </nav>
        </>
    );
}



