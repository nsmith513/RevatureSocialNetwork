import React, { FormEvent, RefObject, useEffect, useState } from 'react';
import axios  from 'axios';
import '../styles/create-post-pres.css';

interface IProps {
    fileInput: any;
    handleSubmit: ((event: FormEvent<HTMLFormElement>) => void) | undefined;
    setPostValue(value: string): void;
    postContent: string;

}

export const CreatePost: React.FC<IProps> = (props:IProps) => {



    return (
        <form onSubmit={props.handleSubmit}>
            <div className="container">
                <div className="card create-post">

                    <div id="inputsContainer1" className="col-md-8 col-md-offset-2">

                        <h1 className="createPostHeader">Create a post</h1>

                        <div id="create-post-card">
                            <div className="form-group">
                                <textarea value={props.postContent} onChange={(evt) => props.setPostValue(evt.target.value)} rows={5} className="form-control" name="description" placeholder="Write a post..."></textarea>
                            </div>

                            <div>
                            <label htmlFor="formFileSm" className="form-label">Add an image</label>
                            <input ref={props.fileInput} className="form-control postInputForm form-control-sm" id="formFileSm" type="file" />
                            </div>
                            
                            <br/>

                            <div className="form-group">
                            <button className="btn btn-outline-danger cancleCreateButton">
                                    Cancel
                                </button>
                                <button type="submit" className="btn btn-outline-success createPostButton">
                                    Create
                                </button>

                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </form>
    );
}