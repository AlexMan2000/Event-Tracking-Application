import React, {useState} from 'react'
import {Tag} from 'antd'
import axios from 'axios';
import ProTable from '@ant-design/pro-table';
import {Button, Modal, Form, Switch, Select, Input, Table, Menu, Dropdown} from "antd";
import {PlusOutlined, DownOutlined} from "@ant-design/icons";
import "./ParameterTable.css"


export default function ParameterTable() {


    const columns = [
        {
            title: '参数ID',
            dataIndex: 'identifierCode',
            key: 'identifierCode',
            valueType: 'text',
        },
        {
          title: '参数名称',
          dataIndex: 'parameterName',
          key: 'parameterName',
          valueType: 'text',
        },
        {
          title: '参数值',
          dataIndex: 'parameterValue',
          key: 'parameterValue',
          valueType: 'text',
        },
        {
          title: '参数创建人',
          dataIndex: 'creator',
          key: 'creator',
          valueType: 'text',
        },
        {
          title: '参数描述',
          dataIndex: 'parameterDesc',
          key: 'parameterDesc',
          valueType: 'text',
        },
        {
          title: '参数创建时间',
          dataIndex: 'gmtCreate',
          key: 'gmtCreate',
          valueType: 'dateTime',
        },
        {
          title: '最近修改时间',
          dataIndex: 'gmtModify',
          key: 'gmtModify',
          valueType: 'dateTime',
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

  const handleRemove = async (fields, id) => {
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



    return (
        <>
        <ProTable
        columns={columns}
        request={async (params, sorter, filter) => {
          // localhost doesn't support https request, use http instead
              const res = await axios.get('http://127.0.0.1:8083/parameters/all', {
                params: {
                  ...params,
                  sorter: sorter && Object.keys(sorter).length ? sorter : null,
                  filter,
                },
              });
            //   console.log(res.data);
              return {
                data: res.data.length != 0 ? res.data: [],
                success: true,
                total: parseInt(res.headers['x-total-count'], 10),
              };
          }}
        rowKey="id"
        dateFormatter="string"
        headerTitle="参数管理"
        // toolBarRender={() => [
        //   <Button key="buttonEvent" icon={<PlusOutlined />} type="primary" onClick={() => { setModalVisible(true)}}>
        //     新增事件
        //   </Button>,
        // ]}
        editable={{
          onSave: handleUpdate,
          onDelete: handleRemove,
        }}
      />
    </>
    )
}