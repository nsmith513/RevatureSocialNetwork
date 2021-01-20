import React from "react";
import { useParams } from "react-router-dom";
import { FeedCont } from "../container/feed-cont";
import { ProfileCont } from "../container/profile-cont";
import { AppContainer } from "./app-container";

export const ProfFeedCont: React.FC = () => {
    //@ts-ignore
    let { id } = useParams();
    return (
        <AppContainer>
            { id ? <>
                <ProfileCont userId={id} />
                <h1 className="feedHeader">My Posts:</h1>
                <FeedCont userId={id} />
            </> : <>
                <ProfileCont />
                <h1 className="feedHeader1">My Posts:</h1>
                {/* @ts-ignore */}
                <FeedCont userId={JSON.parse(localStorage.getItem('user')).id}/>
            </> }
        </AppContainer>
    );
}
