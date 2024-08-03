import React, { useState } from 'react';
import { Avatar, Dropdown, Menu, Tooltip, message } from 'antd';
import { UserOutlined, SmileOutlined } from '@ant-design/icons';
import { useAppDispatch } from '../../../../store/authHook';
import { useNavigate} from 'react-router-dom';
import { handleLogout } from '../../../../services/auth/authThunk';
import { useSelector } from 'react-redux';
import { selectUser } from '../../../../store/slice/userSlice';
import { selectAuthentication } from '../../../../store/slice/authSlice';
import { getProfileImageUrl } from '../../../../apis/imageApi';

const InteractiveAvatar = () => {


  const [tooltipVisible, setTooltipVisible] = useState(false);
  const [dropdownVisible, setDropdownVisible] = useState(false);
  const dispatch = useAppDispatch();  
  const navigate = useNavigate();

  const userInfo: any = useSelector(selectUser)
  const isAuthenticated = useSelector(selectAuthentication)

  const imageUrl = userInfo.profileImageId;
  console.log(getProfileImageUrl(userInfo.profileImageId))

  console.log(userInfo);

  const handleMenuClick = (e) => {
    console.log(e.key, 'clicked');
    if (e.key === 'logout') {
      // handle logout
      dispatch(handleLogout());
      message.success('GoodBye!');
      
    }
    if (e.key === 'profile') {
      // handle profile
    }
    setDropdownVisible(false);
  };

  const handleVisibleChange = (flag) => {
    setTooltipVisible(!flag);
    setDropdownVisible(flag);
    if (!flag){
        setTooltipVisible(false);
    }
  };

  const menu = (
    <Menu onClick={handleMenuClick}>
      <Menu.Item key="profile">Profile</Menu.Item>
      <Menu.Item key="logout">Logout</Menu.Item>
    </Menu>
  );


  const displayName = (isAuthenticated: boolean, userInfo: any) => {
    if (isAuthenticated) {
      if (userInfo.length == 0) {
        return "Nobody"
      }
      return userInfo.firstName.charAt(0).toUpperCase();
    }
    return <UserOutlined></UserOutlined>
  }
  

  return (
    <>
    <Dropdown
      overlay={menu}
      trigger={['click']}
      onVisibleChange={handleVisibleChange}
      visible={dropdownVisible}
      placement="bottomCenter"
    >
      <Tooltip
        title="Account settings"
        visible={dropdownVisible? false: tooltipVisible}
        onVisibleChange={setTooltipVisible}
      >
        <Avatar
          size={48} // Adjust the size as needed
          style={{ cursor: 'pointer' }}
          // icon={<UserOutlined />}
          src = {getProfileImageUrl(userInfo.profileImageId)}
        >{
          !imageUrl && displayName(isAuthenticated, userInfo)
        }
        </Avatar>
      </Tooltip>
    </Dropdown></>
  );
};

export default InteractiveAvatar;
