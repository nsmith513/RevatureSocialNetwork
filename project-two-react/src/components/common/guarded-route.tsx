import React from 'react';
import { Route, Redirect } from "react-router-dom";

interface IProps {
    children?: React.ReactNode;
    auth: any;
    path: string;
}

export const GuardedRoute: React.FC<IProps> = (props:IProps) => {
    return props.auth ?
        <Route exact path={props.path}>
            {props.children}
        </Route> :
        <Redirect to='/login' />;
}
