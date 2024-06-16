import React, {useState} from 'react'
import {Tag} from 'antd'
import axios from 'axios';
import ProTable from '@ant-design/pro-table';
import {Button, Modal, Form, Switch, Select, Input, Table, Menu, Dropdown} from "antd";
import {PlusOutlined, DownOutlined} from "@ant-design/icons";
import "./EventTable.css"



const EditableCell = ({
  editing,
  dataIndex,
  title,
  inputType,
  record,
  index,
  children,
  ...restProps
}) => {
  const inputNode = inputType === 'checkbox' ? <Checkbox /> : <Input />;
  return (
    <td {...restProps}>
      {editing ? (
        <Form.Item
          name={dataIndex}
          style={{ margin: 0 }}
          valuePropName={inputType === 'checkbox' ? 'checked' : 'value'}
          rules={[
            {
              required: true,
              message: `Please Input ${title}!`,
            },
          ]}
        >
          {inputNode}
        </Form.Item>
      ) : (
        children
      )}
    </td>
  );
};



export default function EventTable() {


  const [modalVisible, setModalVisible] = useState(false);


  // const [form] = Form.useForm();
  const [data, setData] = useState([]);
  const [editingKey, setEditingKey] = useState('');


  // Add Parameters

  const edit = () => {
    form.setFieldsValue({ ...record });
    setEditingKey(record.key);
  };

  const cancel = () => {
    setEditingKey('');
  };

  const save = async (key) => {
    try {
      const row = await form.validateFields();
      const newData = [...data];
      const index = newData.findIndex((item) => key === item.key);

      if (index > -1) {
        const item = newData[index];
        newData.splice(index, 1, { ...item, ...row });
        setData(newData);
        setEditingKey('');
      } else {
        newData.push(row);
        setData(newData);
        setEditingKey('');
      }
    } catch (errInfo) {
      console.log('Validate Failed:', errInfo);
    }
  };

  const columnsParameter = [
    {
      title: '属性ID',
      dataIndex: 'key',
      editable: true,
    },
    {
      title: '属性名称',
      dataIndex: 'name',
      editable: true,
    },
    {
      title: '属性值类型',
      dataIndex: 'valueType',
      editable: true,
    },
    {
      title: '是否必填',
      dataIndex: 'isRequired',
      editable: true,
      inputType: 'checkbox',
    },
    {
      title: '操作',
      dataIndex: 'operation',
      render: (_, record) => {
        const editable = isEditing(record);
        return editable ? (
          <span>
            <a onClick={() => save(record.key)} style={{ marginRight: 8 }}>
              保存
            </a>
            <a onClick={cancel}>取消</a>
          </span>
        ) : (
          <a disabled={editingKey !== ''} onClick={() => edit(record)}>
            编辑
          </a>
        );
      },
    },
  ];


  const mergedColumns = columnsParameter.map((col) => {
    if (!col.editable) {
      return col;
    }
    return {
      ...col,
      onCell: (record) => ({
        record,
        inputType: col.inputType || 'text',
        dataIndex: col.dataIndex,
        title: col.title,
        editing: isEditing(record),
      }),
    };
  });



  // Add Events
  const textToColorStatus = {
    "In Use": "green",
    "Idle": "red",
    "Deleted": "grey",
  }

  const columns = [
    {
      title: '事件ID',
      dataIndex: 'identifierCode',
      key: 'id',
      valueType: 'text',
    },
    {
      title: '事件名称',
      dataIndex: 'eventName',
      key: 'eventName',
      valueType: 'text',
    },
    {
      title: '事件类型',
      dataIndex: 'eventType',
      key: 'eventType',
      valueType: 'text',
    },
    {
      title: '事件创建时间',
      dataIndex: 'gmtCreate',
      key: 'gmeCreate',
      valueType: 'dateTime',
    },
    {
      title: '最近修改时间',
      dataIndex: 'gmtModify',
      key: 'gmtModify',
      valueType: 'dateTime',
    },
    {
      title: '事件上线时间',
      dataIndex: 'eventOnlineTime',
      key: 'eventOnlineTime',
      valueType: 'dateTime',
    },
    {
      title: '事件下线时间',
      dataIndex: 'eventOfflineTime',
      key: 'eventOfflineTime',
      valueType: 'dateTime',
    },
    {
      title: '事件描述',
      dataIndex: 'eventDesc',
      key: 'eventDesc',
      valueType: 'text',
    },
    {
      title: '事件状态',
      dataIndex: 'eventStatus',
      key: 'eventStatus',
      render: (_, { tags }) => (
        <>
          {tags.map((tag) => {
            return (
              <Tag color={textToColorStatus[tag]} key={tag}>
                {tag}
              </Tag>
            );
          })}
        </>
      ),
    },
    {
      title: '事件图片',
      dataIndex: 'sampleImages',
      key: 'sampleImages',
      valueType: 'image',
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


  const hideModal = () => { setModalVisible(false)};

  const onModalFinish = (values) => {
    console.log(values);
  }



  // Dropdown menu


  const [parameters, setParameters] = useState([
    { id: 'param1', label: 'Parameter 1' },
    { id: 'param2', label: 'Parameter 2' },
    { id: 'param3', label: 'Parameter 3' },
  ]);

  const menu = (
    <Menu>
      {parameters.map((param) => (
        <Menu.Item key={param.id} onClick={()=>{console.log(`haha+${param.id}`)}}>
          {param.label}
        </Menu.Item>
      ))}
    </Menu>
  );


  const handleDropdownContent = () => {
    axios.get("'http://127.0.0.1:8083/events/all'").then(response=>{
      console.log(response.data);
      const temp = response.data.map(elem => {return {"id":elem.id, "label":elem.parameterName}});
      console.log(temp);
      setParameters(temp);
    })
  }

  return (
    <>
      <ProTable
        columns={columns}
        request={async (params, sorter, filter) => {
          // localhost doesn't support https request, use http instead
              const res = await axios.get('http://127.0.0.1:8083/users/all', {
                params: {
                  ...params,
                  sorter: sorter && Object.keys(sorter).length ? sorter : null,
                  filter,
                },
              });
              console.log(res.data);
              return {
                data: res.data.length != 0 ? res.data: [],
                success: true,
                total: parseInt(res.headers['x-total-count'], 10),
              };
          }}
        rowKey="id"
        dateFormatter="string"
        headerTitle="Event Management"
        toolBarRender={() => [
          <Button key="buttonEvent" icon={<PlusOutlined />} type="primary" onClick={() => { setModalVisible(true)}}>
            新增事件
          </Button>,
        ]}
        editable={{
          onSave: handleUpdate,
          onDelete: handleRemove,
        }}
      />
      <Modal
        title="新增页面事件"
        visible={modalVisible}
        onOk={hideModal}
        onCancel={hideModal}
        footer={[
          <Button key="back" onClick={hideModal}>
            取消
          </Button>,
          <Button key="submit" type="primary" onClick={() => form.submit()}>
            保存
          </Button>,
        ]}
      >
        <Form
          layout="vertical"
          onFinish={onModalFinish}
          initialValues={{ active: true }}
        >
        <div className={"section-divider"}>
          1.请设置基本信息
        </div>
          <Form.Item 
            name="事件名称" 
            label="事件名称" 
            rules={[{ required: true, message: '请输入事件名称'}]}>
             <Input />
          </Form.Item>
          <Form.Item 
            name="事件类型" 
            label="事件类型"
            rules={[{ required: true, message: '请输入事件类型'}]}>
            <Input/>
          </Form.Item>
          <Form.Item 
            name="事件描述" 
            label="事件描述">
            <Input/>
          </Form.Item>
        </Form>
        <div className={"section-divider"}>
          2.请设置事件属性
        </div>
        <Form  component={false}>
          <Dropdown overlay = {menu} placement="bottomLeft" trigger={["click"]}>
            <Button
              onClick={handleDropdownContent}
              type="primary"
              style={{ marginBottom: 16 }}
            >
              <PlusOutlined /> 添加新属性
            </Button>
          </Dropdown>
          <Table
            components={{
              body: {
                cell: EditableCell,
              },
            }}
            bordered
            dataSource={data}
            columns={mergedColumns}
            rowClassName="editable-row"
            pagination={{
              onChange: cancel,
            }}
          />
        </Form>

      </Modal>
    </>
  );
}
