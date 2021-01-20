import React, { useEffect, useState } from 'react';
import axios  from 'axios';
import '../styles/comment-post-pres.css';

interface IProps {
    
}

export const CommentPost: React.FC<IProps> = (props:IProps) => {

    const [comment, setComment] = useState('');

    const onContentChange = (evt:any) => {
        setComment(evt.target.value);
    }

    const handleSubmit = (evt:any) => {
        evt.preventDefault();

        const commentObj = {
            content: comment,
            user: 1,
            post: 2
        
        }
        axios.post(process.env.REACT_APP_API_URL + 'ProjectTwoSTS/api/post/comment',commentObj)
        .then(res => console.log(res))
        .catch(err => console.log(err)
        );
    }

    return (
        <form onSubmit={handleSubmit}>
            <div className="d-flex justify-content-center">
            <div className="card register-card">
                <div className="card-header">
                    <h3>Comment</h3>
                </div>
                <div className="card-body">
                    <div className="mb-3">
                        <textarea  onChange={onContentChange} value={comment} rows={5} className="form-control" name="description" placeholder="Write a post..."></textarea>
                    </div>
                    
                    <div className="form-group">
                        
                    </div>

                    <button type="submit" className="btn btn-primary" id="input-creds">Submit</button>
                    </div>
                </div>
            </div>
        </form>
    );
}