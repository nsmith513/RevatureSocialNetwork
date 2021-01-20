import React from 'react';
import './App.css';
import { BrowserRouter, Route } from 'react-router-dom';
import { AppContainer } from './components/common/app-container';
import { LoginCont } from './components/container/login-cont';
import { SignUpCont } from './components/container/sign-up-cont';
import { CreatePostCont } from './components/container/create-post-cont';
import { FeedCont } from './components/container/feed-cont';
import { ProfileCont } from './components/container/profile-cont';  
import { EditProfileCont } from './components/container/edit-profile-cont';
import { SearchCont } from './components/container/search-cont';
import { Header } from './components/common/navbar/Header';
import { CommentPostCont } from './components/container/comment-post-cont';
import { Provider } from "react-redux";
import { store } from "./redux/store";
import { ProfFeedCont } from './components/common/prof-feed-cont';
import { GuardedRoute } from './components/common/guarded-route';

function App() {
  return (
    <div className="App">
      <Provider store={store}>
        <BrowserRouter>
          <Route path='/'>
            <Header logo={""}/>
          </Route>
          
          <GuardedRoute auth={localStorage.getItem('user')} path='/'>
            <ProfFeedCont/>
          </GuardedRoute>
          <GuardedRoute auth={localStorage.getItem('user')} path="/editprofile">
            <EditProfileCont />
          </GuardedRoute>
          <GuardedRoute auth={localStorage.getItem('user')} path='/feed'>
            <AppContainer>
              <CreatePostCont/>
              <h1 className="feedHeader">Global Feed:</h1>
              <FeedCont/>
            </AppContainer>
          </GuardedRoute>
          <GuardedRoute auth={localStorage.getItem('user')} path='/user/:id'>
            <ProfFeedCont/>
          </GuardedRoute>

          <Route exact path='/login' component={LoginCont}/>
          <Route exact path='/register' component={SignUpCont}/>
        </BrowserRouter>
      </Provider>
    </div>
  );
}

export default App;
