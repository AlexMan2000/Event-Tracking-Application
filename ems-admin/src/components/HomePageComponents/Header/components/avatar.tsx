import React, { useState } from 'react';
import { Avatar, Dropdown, Menu, Tooltip } from 'antd';
import { UserOutlined } from '@ant-design/icons';
import { useAppDispatch } from '../../../../store/authHook';

const InteractiveAvatar = () => {
  const [tooltipVisible, setTooltipVisible] = useState(false);
  const [dropdownVisible, setDropdownVisible] = useState(false);
  const useDispatch = useAppDispatch();  

  const handleMenuClick = (e) => {
    console.log(e.key, 'clicked');
    if (e.key === 'logout') {
      // handle logout

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

  return (
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
          icon={<UserOutlined />}
        />
      </Tooltip>
    </Dropdown>
  );
};

export default InteractiveAvatar;
