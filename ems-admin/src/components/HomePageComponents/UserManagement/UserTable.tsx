import React from 'react';
import ProTable from '@ant-design/pro-table';
import { Button, message } from 'antd';
import axios from 'axios';
import { EllipsisOutlined, PlusOutlined } from '@ant-design/icons';
import { getAllUsers } from '../../../services/users/userService';


const UserTable = () => {
  const columns = [
    {
      title: 'ID',
      dataIndex: 'id',
      key: 'id',
      valueType: 'text',
      hideInForm: true, // hide in forms because it's auto-generated
    },
    {
      title: 'Role ID',
      dataIndex: 'roleId',
      key: 'roleId',
      valueType: 'text',
    },
    {
      title: 'First Name',
      dataIndex: 'firstName',
      key: 'firstName',
      valueType: 'text',
    },
    {
      title: 'Middle Name',
      dataIndex: 'middleName',
      key: 'middleName',
      valueType: 'text',
    },
    {
      title: 'Last Name',
      dataIndex: 'lastName',
      key: 'lastName',
      valueType: 'text',
    },
    {
      title: 'Mobile',
      dataIndex: 'mobile',
      key: 'mobile',
      valueType: 'text',
    },
    {
      title: 'Email',
      dataIndex: 'email',
      key: 'email',
      valueType: 'text',
    },
    {
      title: 'Registered At',
      dataIndex: 'registeredAt',
      key: 'registeredAt',
      valueType: 'dateTime',
      hideInForm: true,
      editable: false,
    },
    {
      title: 'Last Login',
      dataIndex: 'lastLogin',
      key: 'lastLogin',
      valueType: 'dateTime',
      hideInForm: true,
      editable: false,
    },
    {
      title: 'Introduction',
      dataIndex: 'intro',
      key: 'intro',
      valueType: 'textarea',
    },
    {
      title: 'Profile',
      dataIndex: 'profile',
      key: 'profile',
      valueType: 'textarea',
    },
  ];

  const handleAdd = async (fields) => {
    try {
      const result = await axios.post('/api/users', fields);
      if (result.status === 200) {
        message.success('Added successfully');
        return true;
      }
      throw new Error('Error');
    } catch (error) {
      message.error('Adding failed, please try again!');
      return false;
    }
  };

  const handleUpdate = async (fields, id) => {
    try {
      const result = await axios.put(`/api/users/${id}`, fields);
      if (result.status === 200) {
        message.success('Update successful');
        return true;
      }
      throw new Error('Error');
    } catch (error) {
      message.error('Update failed, please try again!');
      return false;
    }
  };

  const handleRemove = async (id) => {
    try {
      const result = await axios.delete(`/api/users/${id}`);
      if (result.status === 200) {
        message.success('Deleted successfully');
        return true;
      }
      throw new Error('Error');
    } catch (error) {
      message.error('Delete failed, please try again!');
      return false;
    }
  };

  return (
    <ProTable
      columns={columns}
      request={getAllUsers}
      rowKey="id"
      dateFormatter="string"
      headerTitle="用户管理"
      toolBarRender={() => [
        <Button key="button" icon={<PlusOutlined />} type="primary" onClick={() => handleAdd()}>
          New User
        </Button>,
      ]}
      editable={{
        onSave: handleUpdate,
        onDelete: handleRemove,
      }}
    />
  );
};

export default UserTable;