import React, { useState, useEffect } from "react";
import { Form, Input, Button, Checkbox, Modal,Card, Upload, Image, message} from 'antd';
import { useNavigate} from 'react-router-dom';
import { PlusOutlined } from '@ant-design/icons';
import { useSelector } from "react-redux";
import { useAppDispatch } from "../../store/authHook";
import { handleLogin, checkAuthStatus } from "../../services/auth/authThunk";
import { RootState } from "../../store/store";

// import 'antd/dist/antd.css';
import "./index.css"
import axios from 'axios';
import { LoginCredentials } from "../../services/auth/authService";

async function hashPasswordSHA256(password) {
  const encoder = new TextEncoder();
  const data = encoder.encode(password);
  const hash = await crypto.subtle.digest('SHA-256', data);
  return Array.from(new Uint8Array(hash)).map(b => b.toString(16).padStart(2, '0')).join('');
}

const getBase64 = (file: any) =>
  new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = (error) => reject(error);
  });


const LoginPage = () => {
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [loginForm] = Form.useForm();
  const [registerForm] = Form.useForm();
  const [error, setError] = useState(false);
  const [previewOpen, setPreviewOpen] = useState(false);
  const [previewImage, setPreviewImage] = useState('');
  const [fileList, setFileList] = useState([]);
  const navigate = useNavigate();
  const isAuthenticated = useSelector((state: RootState) => state.auth.isAuthenticated);
  const dispatch = useAppDispatch();
  

  // Get logged in information
  useEffect(() => {
    const token =  localStorage.getItem("token");
    if (token) {
      dispatch(checkAuthStatus({token: token}))
    }
    
  }, [dispatch]);


  useEffect(() => {
    // Is logged in
    if (isAuthenticated) {
      navigate('/home');
    }
  }, [isAuthenticated, navigate]);


  const onLoginFinish = async (values: LoginCredentials) => {
      const credentials: LoginCredentials = values;
      dispatch(handleLogin(credentials)).then(
        response => {
          if (response.status = 200) {
            message.success("Welcome!")
            navigate("/home");
          }
        }
      ).catch(error => {
        setError(true);
      }
    )
    // loginForm.resetFields();
  };

  const onLoginFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };

  const onRegisterFinish = (values) => {
    hashPasswordSHA256(values["password"]).then(hashedPassword => {
      axios.post("http://127.0.0.1:8083/users/register", 
      {
        "passwordHash": hashedPassword
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

  const showRegisterModal = () => {
    setIsModalVisible(true);
  };

  const handleRegisterModalOk = (e) => {
    registerForm.resetFields();
    setIsModalVisible(false);
  };


  const showErrorModal = () => {
    setError(true);
  };
  const handleErrorOk = () => {
    setError(false);
  };

  const handleErrorCancel = () => {
    setError(false);
  };


  const handlePreview = async (file) => {
    if (!file.url && !file.preview) {
      file.preview = await getBase64(file.originFileObj);
    }
    setPreviewImage(file.url || file.preview);
    setPreviewOpen(true);
  };


  const handleChange = ({ fileList: newFileList }) => 
    setFileList(newFileList);

  const uploadButton = (
    <button
      style={{
        border: 0,
        background: 'none',
        marginTop: 15,
      }}
      type="button"
    >
      <PlusOutlined style={{
        // backgroundColor: "black",
        color: "black"
      }}/>
      <div
        style={{
          marginTop: 8,
          color: "black"
        }}
      >
        Upload
      </div>
      sss
    </button>
  );

  // When user click x or 遮罩层时触发
  const handleRegisterModalCancel = () => {
    registerForm.resetFields();
    setIsModalVisible(false);
  };

  // function handleUpload(): void {
    
  // }

  return (
    <div className="login-container">
        <div className="login-header-line">
          <h2>Login</h2>
        </div>
        <Modal
        open={error}
        title="Title"
        onOk={handleErrorOk}
        onCancel={handleErrorCancel}
        footer={(_, { OkBtn, CancelBtn }) => (
          <>
            <Button>Custom Button</Button>
            <CancelBtn />
            <OkBtn />
          </>
        )}
      >
        Username and password mismatch!
      </Modal>
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
          name="email"
          rules={[
            { required: true, message: 'Please input your email!' 
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
          <div className = "register-button" type="link" onClick={showRegisterModal}>
          <h6>Register</h6></div>
        </div>
      <Modal title="User Registration" open={isModalVisible} footer={null} onOk={handleRegisterModalOk} onCancel={handleRegisterModalCancel} >
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
          <>
            <Upload
                action={""}
                accept={"image/png, image/jpeg"}
                listType="picture-circle"
                fileList={fileList}
                onPreview={handlePreview}
                onChange={handleChange}
                // customRequest={handleUpload}
              >
              {uploadButton}
            </Upload>
            {previewImage && (
              <Image
                wrapperStyle={{
                  display: 'none',
                }}
                preview={{
                  visible: previewOpen,
                  onVisibleChange: (visible) => setPreviewOpen(visible),
                  afterOpenChange: (visible) => !visible && setPreviewImage(''),
                }}
                src={previewImage}
              />
            )}
            
            </>
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