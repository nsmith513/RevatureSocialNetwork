import React from 'react';
import Enzyme, { shallow } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import {CreatePost} from '../components/presentation/create-post-pres';

Enzyme.configure({ adapter: new Adapter() })

const handleSubmit = (evt: any) => {}
const fileInput = null;
const postContent = '';
const setPostValue = () => {}

describe('<CreatePost />', () => {
    const container = shallow(<CreatePost 
        handleSubmit={handleSubmit}
        postContent={postContent}
        setPostValue={setPostValue}
        fileInput={fileInput}
    />);

    it('should match the snapshot', () => {
        expect(container.html()).toMatchSnapshot();
    });
});