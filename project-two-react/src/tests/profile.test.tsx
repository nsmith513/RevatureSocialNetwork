import React from 'react';
import Enzyme, { shallow } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import {ProfilePres} from '../components/presentation/profile-pres';

Enzyme.configure({ adapter: new Adapter() })

const profData = {uname:'', email:'', pfp:''};

describe('<SignUpPres/>', () => {
    const container = shallow(<ProfilePres 
        user={profData}
    />);

    it('should match the snapshot', () => {
        expect(container.html()).toMatchSnapshot();
    });
});