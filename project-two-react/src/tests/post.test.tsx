import React from 'react';
import Enzyme, { shallow } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import {PostPres} from '../components/presentation/post-pres';

Enzyme.configure({ adapter: new Adapter() })

const post = {
    id:0, 
    content:'',
    images:[],
    user:{id:0,uname:'',email:'',pfp:''},
    likes:[]
};
const likeButtonClick = (evt:any) => {}
const onOff = true
const likeCount = 0


describe('<PostPres />', () => {
    const container = shallow(<PostPres post={post} likeButtonClick={likeButtonClick} onOff={onOff} likeCount={likeCount} />);

    it('should match the snapshot', () => {
        expect(container.html()).toMatchSnapshot();
    });
});