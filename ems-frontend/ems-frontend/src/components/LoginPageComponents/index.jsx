import React, { useState } from "react";
import { Form, Input, Button, Checkbox, Modal,Card} from 'antd';
import { useNavigate} from 'react-router-dom';

// import 'antd/dist/antd.css';
import "./index.css"
import axios from 'axios';

async function hashPasswordSHA256(password) {
  const encoder = new TextEncoder();
  const data = encoder.encode(password);
  const hash = await crypto.subtle.digest('SHA-256', data);
  return Array.from(new Uint8Array(hash)).map(b => b.toString(16).padStart(2, '0')).join('');
}




const LoginPage = () => {
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [loginForm] = Form.useForm();
  const [registerForm] = Form.useForm();
  const navigate = useNavigate();


  const onLoginFinish = (values) => {
    hashPasswordSHA256(values["password"]).then(hashedPassword => {
      axios.get("http://127.0.0.1:8083/login/user", 
      {
        email: values["email"],
        passwordHash: hashedPassword
      }
      , 
      {
        timeout: 5000
      }).then(response => {
        if (response.status === 200) {
          // 跳转到主界面
          navigate("/home")
        } else {
          console.log(response);
        }
      })
    })
    loginForm.resetFields();
  };

  const onLoginFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };

  const onRegisterFinish = (values) => {
    hashPasswordSHA256(values["password"]).then(hashedPassword => {
      axios.post("http://127.0.0.1:8083/users/register", 
      {"passwordHash": hashedPassword
        ,...values
      }
      , 
      {
        timeout: 5000
      })
    })
    registerForm.resetFields();
    setIsModalVisible(false);
  };

  const onRegisterFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };

  const showModal = () => {
    setIsModalVisible(true);
  };

  const handleOk = (e) => {
    registerForm.resetFields();
    setIsModalVisible(false);
  };


  // When user click x or 遮罩层时触发
  const handleCancel = () => {
    registerForm.resetFields();
    setIsModalVisible(false);
  };

  return (
    <div className="login-container">
        <div className="login-header-line">
          <h2>Login</h2>
        </div>
      <Form
        className="login-form"
        form={loginForm}
        name="login"
        initialValues={{ remember: true }}
        onFinish={onLoginFinish}
        onFinishFailed={onLoginFinishFailed}
        autoComplete="off"
      >
        <Form.Item
          label="Email"
          name="username"
          rules={[
            { required: true, message: 'Please input your username!' 
            }
          ]}
        >
          <Input className="input-username" placeholder="Type in your email"/>
        </Form.Item>

        <Form.Item
          label="Password"
          name="password"
          rules={[{ required: true, message: 'Please input your password!' }]}
        >
          <Input.Password className="input-password" placeholder="Type in your password"/>
        </Form.Item>

        <Form.Item name="remember" valuePropName="checked">
          <Checkbox>Remember me</Checkbox>
        </Form.Item>

        <div className = "login-button-container">
          <button className = "login-button" type="submit">
          <h3>LOGIN</h3></button>
        </div>

      </Form>
      <div className = "register-button-container">
          <div className = "register-button" type="link" onClick={showModal}>
          <h6>Register</h6></div>
        </div>
      <Modal title="User Registration" open={isModalVisible} footer={null} onOk={handleOk} onCancel={handleCancel} >
        <Form
          name="register"
          form={registerForm}
          onFinish={onRegisterFinish}  // Pass the validation
          onFinishFailed={onRegisterFinishFailed}  // Didn't pass the validation
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
          <Form.Item label="Email" name="email" 
            rules={
              [ 
                 { validator: (_, value) => value ? 
                   axios.get(`http://127.0.0.1:8083/users/search/${value}`).then(response=>{
                    console.log(response);
                      if (response.data === "") {
                        return Promise.resolve();
                      } else {
                        return Promise.reject("This email has been registered");
                      }
                   })
                 : Promise.reject("Email field cannot be empty")
                 }
              ]
            }>
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
          <div className = "login-button-container">
          <button className = "login-button" type="submit">
          <h3>REGISTER</h3></button>
        </div>
            
        </Form>
      </Modal>
    </div>
  );
};

export default LoginPage;