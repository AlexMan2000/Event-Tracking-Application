import React, { useState } from "react";
import { Form, Input, Button, Checkbox, Modal,Card} from 'antd';
// import 'antd/dist/antd.css';
import "./index.css"

const LoginPage = () => {
  const [isModalVisible, setIsModalVisible] = useState(false);

  const onFinish = (values) => {
    console.log('Success:', values);
  };

  const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };

  const showModal = () => {
    setIsModalVisible(true);
  };

  const handleOk = () => {
    setIsModalVisible(false);
  };

  const handleCancel = () => {
    setIsModalVisible(false);
  };

  return (
    <Card 
        hoverable
        className="loginForm">
      <Form
        name="basic"
        initialValues={{ remember: true }}
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        autoComplete="off"
      >
        <Form.Item
          label="Username"
          name="username"
          rules={[{ required: true, message: 'Please input your username!' }]}
        >
          <Input />
        </Form.Item>

        <Form.Item
          label="Password"
          name="password"
          rules={[{ required: true, message: 'Please input your password!' }]}
        >
          <Input.Password />
        </Form.Item>

        <Form.Item name="remember" valuePropName="checked">
          <Checkbox>Remember me</Checkbox>
        </Form.Item>

        <Form.Item>
          <Button type="primary" htmlType="submit">
            Log in
          </Button>
          {' '}
          <Button type="link" onClick={showModal}>
            Register
          </Button>
        </Form.Item>
      </Form>

      <Modal title="User Registration" visible={isModalVisible} onOk={handleOk} onCancel={handleCancel}>
        <Form
          name="register"
          onFinish={onFinish}  // Assume the same onFinish function, adjust as necessary
          layout="vertical"
        >
          <Form.Item label="First Name" name="firstName" rules={[{ required: true }]}>
            <Input />
          </Form.Item>
          <Form.Item label="Middle Name" name="middleName">
            <Input />
          </Form.Item>
          <Form.Item label="Last Name" name="lastName" rules={[{ required: true }]}>
            <Input />
          </Form.Item>
          <Form.Item label="Mobile" name="mobile" rules={[{ required: true }]}>
            <Input />
          </Form.Item>
          <Form.Item label="Email" name="email" rules={[{ required: true, type: 'email' }]}>
            <Input />
          </Form.Item>
          <Form.Item label="Password" name="password" rules={[{ required: true }]}>
            <Input.Password />
          </Form.Item>
          <Form.Item label="Intro" name="intro">
            <Input.TextArea />
          </Form.Item>
          <Form.Item label="Profile" name="profile">
            <Input.TextArea />
          </Form.Item>
        </Form>
      </Modal>
    </Card>
  );
};

export default LoginPage;