import EventTrackingIndex from './EventTrackingManagement';
import UserManagementIndex from './UserManagement';
import ChattingRoomIndex from './ChattingRoom';
import React, { useState } from 'react';

import {
    AppstoreOutlined,
    BarChartOutlined,
    CloudOutlined,
    ShopOutlined,
    TeamOutlined,
    UploadOutlined,
    UserOutlined,
    VideoCameraOutlined,
  } from '@ant-design/icons';
  import { Layout, Menu, theme } from 'antd';
  const { Header, Content, Footer, Sider } = Layout;


// Sub pages
const pages = [<UserManagementIndex/>, <EventTrackingIndex/>, <ChattingRoomIndex/>, ...Array.from([1,2,3,4,5,6]).map((val, index, array) => `nav ${index}`)]
const labels = ["User Management", "Event Tracking", "Chatting Room", ...Array.from([1,2,3,4,5,6]).map((val, index, array) => `nav ${index}`)]

const items = [
  UserOutlined,
  VideoCameraOutlined,
  UploadOutlined,
  BarChartOutlined,
  CloudOutlined,
  AppstoreOutlined,
  TeamOutlined,
  ShopOutlined,
].map((icon, index) => ({
  key: String(index + 1),
  icon: React.createElement(icon),
  label: labels[index]
}));


function HomePage() {

    const {
        token: { colorBgContainer, borderRadiusLG },
      } = theme.useToken();
    
      const [collapsed, setCollapse] = useState(false);
      const [menuIndex, setMenuIndex] = useState("1");
    

    return (<Layout hasSider>
        <Sider
            style={{
            overflow: 'auto',
            height: '100vh',
            position: 'fixed',
            left: 0,
            top: 0,
            bottom: 0,
            }}
            collapsed = {collapsed}
            collapsible
            onCollapse={setCollapse.bind(this)}
        >
            <Menu theme="dark" mode="inline" defaultSelectedKeys={[menuIndex]} items={items}
            onSelect={({key, keyPath, selectedKeys, domEvent}) => {setMenuIndex(key)}}
            />
        </Sider>
        <Layout
            className={"main-layout"}
            style={{
            marginLeft: collapsed ? 80 : 200,
            marginRight: 0
            }}
        >
            <Header
            style={{
                width: "auto",
                padding: 0,
                background: colorBgContainer,
            }}
            />
            <Content
            style={{
                margin: '24px 16px 0',
                overflow: 'initial',
            }}
            >
            <div
                style={{
                padding: 24,
                textAlign: 'center',
                background: colorBgContainer,
                borderRadius: borderRadiusLG,
                }}
            >
                {pages[parseInt(menuIndex) - 1]}
            </div>
            </Content>
            <Footer
            style={{
                textAlign: 'center',
            }}
            >
            EventTracking System ©{new Date().getFullYear()} Created by AlexMan
            </Footer>
        </Layout>
        </Layout>
    )}


export default HomePage;