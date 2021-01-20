import React from 'react';
import Enzyme, { shallow } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import {SignUpPres} from '../components/presentation/sign-up-pres';

Enzyme.configure({ adapter: new Adapter() })


const fileInput = null;
const handleSubmit = (evt: any) => {}
const onContentChange = (event: any) => {}
const setUsername = () => {}
const setPassword = () => {}
const setPassword2 = () => {}
const setEmail = () => {}
const setPasswordCheck = () => {}
const username = ''
const password = ''
const password2 =''
const email =''
const passwordCheck = ''

describe('<SignUpPres/>', () => {
    const container = shallow(<SignUpPres 
        handleSubmit={handleSubmit}
        onContentChange={onContentChange}
        setUsername={setUsername}
        setPassword={setPassword}
        setPassword2={setPassword2}
        setEmail={setEmail}
        setPasswordCheck={setPasswordCheck}
        username={username}
        password={password}
        password2={password2}
        email={email}
        passwordCheck={passwordCheck}
        fileInput={fileInput}
    />);

    it('should match the snapshot', () => {
        expect(container.html()).toMatchSnapshot();
    });
});