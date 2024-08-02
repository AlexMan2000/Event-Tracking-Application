import UserManagementIndex from './UserManagement';
import EventManagementIndex from './EventTrackingManagement';
import ChattingRoomIndex from './ChattingRoom';
import PhotoSharingPage from './PhotoSharing';

import TestForm from './TestForm';
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
import ParameterManagementIndex from './ParameterManagement';
import ProjectManagementIndex from './ProjectManagement';
import ModuleManagementIndex from './ModuleManagement';
import PageManagementIndex from './PageManagement';
import HeaderComponent from './Header/header';


  const { Header, Content, Footer, Sider } = Layout;

function HomePage() {

    const {
        token: { colorBgContainer, borderRadiusLG },
    } = theme.useToken();

    const [collapsed, setCollapse] = useState(false);
    const [menuIndex, setMenuIndex] = useState("1");


    // Sub pages
    const pages = [<UserManagementIndex/>,
         <EventManagementIndex setMenuIndex={setMenuIndex} databaseName={"event"}/>,
          <ParameterManagementIndex setMenuIndex={setMenuIndex} databaseName={"parameter"}/>,
          <ProjectManagementIndex setMenuIndex={setMenuIndex} databaseName={"project"}/>,
          <ModuleManagementIndex setMenuIndex={setMenuIndex} databaseName={"module"}/>,
          <PageManagementIndex setMenuIndex={setMenuIndex} databaseName={"page"}/>,
          <ChattingRoomIndex/>,  
          <PhotoSharingPage/>,
          <TestForm/>
        ]
    const labels = ["User Management",
         "Event Management", 
         "Parameter Management",
         "Project Management",
         "Module Management",
         "Page Management",
        //   "Chatting Room",
        //   "Photo Sharing",
        //   "Test Form"
          ]

    const items = [
    UserOutlined,
    VideoCameraOutlined,
    UploadOutlined,
    BarChartOutlined,
    CloudOutlined,
    AppstoreOutlined,
    // TeamOutlined,
    // ShopOutlined,
    ].map((icon, index) => ({
    
    key: String(index + 1),
    icon: React.createElement(icon),
    label: labels[index]
    }));


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
            <Menu theme="dark" mode="inline" selectedKeys = {[menuIndex]} defaultSelectedKeys={[menuIndex]} items={items}
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
            >
                <HeaderComponent></HeaderComponent>
            </Header>
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