import React from 'react';
import Enzyme, { shallow } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import {EditProfilePres} from '../components/presentation/edit-profile-pres';

Enzyme.configure({ adapter: new Adapter() })


const fileInput = null;
const handleSubmit = (evt: any) => {}
const setUsername = () => {}
const setPassword = () => {}
const setEmail = () => {}
const username = ''
const password = ''
const email =''
const userData = {id:0,uname:'',email:'',pfp:'',pwd:''};

describe('<EditProfilePres />', () => {
    const container = shallow(<EditProfilePres
        handleSubmit={handleSubmit}
        setUsername={setUsername}
        setPassword={setPassword}
        setEmail={setEmail}
        username={username}
        password={password}
        email={email}
        userData={userData}
        fileInput={fileInput}
       />);

    it('should match the snapshot', () => {
        expect(container.html()).toMatchSnapshot();
    });
});