import React from 'react';
import IPost from '../../models/IPost';
import '../styles/post-pres.css';

interface IProps {
    likeButtonClick(event: any): void;
    post: IPost;
    onOff: Boolean;
    likeCount: number;
}

interface IImages{
    id: number,
    link: string
}

export const PostPres: React.FC<IProps> = (props: IProps) => {
    return props.post.user ?
        <>
            <div className="card post-custom-card ">
                <div className="card-body">
                    <div className="clearfix">
                        <img className='avatar pull-left custom-img' src={props.post.user.pfp} />
                        <h5 className="card-title">{props.post.user.uname}</h5>
                    </div>
                    <p className="card-text">{props.post.content}</p>

                    <div>{props.post.images.map((image) => <img className="img-fluid img-thumbnail" key={image.id} src={image.link}/>)}</div>
                    
                    <hr />
                    <button onClick={props.likeButtonClick}
                        style={props.onOff ? { borderColor: 'blue' } : { borderColor: 'grey' }}
                        className="btn btn-link">
                        Like {props.likeCount > 0 ? props.likeCount : ""}
                    </button>
                    {/* <a href="#" className="card-link">Comment</a> */}
                </div>
            </div>
        </> :
        <>
            <h2>No posts</h2>
        </>
}
