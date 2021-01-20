import { compose, createStore, Store } from "redux";
import {state} from "./state/index";

const a:any = window;

const composeEnhancers = a.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose

const enhancer = composeEnhancers();


export const store: Store<any> = createStore(state, enhancer);