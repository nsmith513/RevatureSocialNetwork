import IPost from "../../models/IPost";

export const FeedActions = {
    SET_FEED: 'SET_FEED'
}

export interface IFeedAction {
    type: string;
    payload: IPost[];
}

export const FeedActionMapper = (feed: IPost[]): IFeedAction => {
    return {
        type: FeedActions.SET_FEED,
        payload: feed
    }
}
