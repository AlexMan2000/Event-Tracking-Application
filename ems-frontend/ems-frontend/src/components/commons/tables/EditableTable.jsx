import React, {useState, useRef, useEffect} from 'react'
import axios from 'axios';
import ProTable from '@ant-design/pro-table';
import {Tag, notification, Space, Button, Modal, Form, Upload, Switch, Select, Input, Table, Menu, Dropdown} from "antd";
import {PlusOutlined, DownOutlined} from "@ant-design/icons";
import styles from "./EditableTable.module.less"


export default function EventTable(props) {

  const {entityBase} = props;


  // Event related
  const [modalVisible, setModalVisible] = useState(false);
  const [modalMode, setModalMode] = useState(0);
  const {setMenuIndex} = props;
  const [eventTypes, setEventTypes] = useState([]);
  const [eventStatus, setEventStatus] = useState([]);


  const [formCreateEvent] = Form.useForm();
  const [selectedEventType, setSelectedEventType] = useState(null);
  
  const [formCreateParameter] = Form.useForm();
  const [createEventParameterTableData, setCreateEventParameterTableData] = useState([]);


  const proTableRef = useRef();
  const hideModal = () => { setModalVisible(false)};

  const handleEventTypeDropdownRender = async () => {
    const res = await axios.get(`${requestBase}/allTypes`);
    const tempData = res.data.length !=0 ? res.data.map((elem)=> ({"label":elem, "value":elem})):[];
    const finalData = [{"label": "+ 添加新事件类型", "value": "newValue"}, ...tempData]
    setEventTypes(finalData);
  }

  const handleEventTypeDropDownClick = (value) => {
     if (value === "newValue") {
        
     }
  }




  const handleEventStatusDropdownRender = async () => {
    const res = await axios.get(`${requestBase}/allStatus`);
    const tempData = res.data.length !=0 ? res.data.map((elem)=> ({"label":elem, "value":elem})):[];
    const finalData = [{"label": "+ 新建事件状态", "value": "newValue"}, ...tempData]
    setEventStatus(finalData);
  }

  const handleEventStatusDropDownClick = (value) => {
    console.log(value);
  }


  // Form submission
  const onFormCreateEventFinish = async (values) => {
    const postData = {
      ...values,
      "parameterIds": createEventParameterTableData.map(elem=>elem.identifierCode)
    };
     console.log(postData);

     let res = null;
     if (modalMode === 1) {
      // Update
      res = await axios.put(`${requestBase}/update`, postData)
     } else {
      // Create
      res = await axios.post(`${requestBase}/add`, postData)
     }


     if (res.status == 200) {
      setModalVisible(false);
      proTableRef.current.reload();
     } else {
     }
  }


  const onFormCreateEventOk = () => {
    formCreateEvent.resetFields();
    setCreateEventParameterTableData([]);
    hideModal();
  }

  const onFormCreateEventCancel = () => {
    formCreateEvent.resetFields();
    setCreateEventParameterTableData([]);
    hideModal();
  }




  const modalColumnsParameter = [
    {
      title: '属性ID',
      dataIndex: 'identifierCode',
      key: 'identifierCode',
    },
    {
      title: '属性名称',
      dataIndex: 'parameterName',
      key: 'parameterName',
    },
    {
      title: '属性值类型',
      dataIndex: 'parameterType',
      key: 'parameterType',
    },
    {
      title: '操作',
      dataIndex: 'operation',
      render: (_, record) => {
        return (
          <span>
            <a onClick={() => removeFromList(record.identifierCode)} style={{ marginRight: 8 }}>
              移除
            </a>
          </span>
        )
      },
    },
  ];


  // ProTable Add Events
  const textToColorStatus = {
    "已上线": "green",
    "未上线": "red",
    "已删除": "grey",
  }

  const proTableColumnsEvent = [
    {
      title: '事件编码',
      dataIndex: 'id',
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
      title: '事件创建者',
      dataIndex: 'creator',
      key: 'creator',
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
      title: '事件触发次数',
      dataIndex: 'triggerTimes',
      key: 'triggerTimes',
      valueType: 'text',
    },
    {
      title: '事件状态',
      dataIndex: 'eventStatus',
      key: 'eventStatus',
      render: (_, {eventStatus}) => {
        return (
            <Tag color={textToColorStatus[eventStatus]} key={eventStatus}>
              {eventStatus}
            </Tag>
      )},
    },
    {
      title: '事件图片',
      dataIndex: 'sampleImages',
      key: 'sampleImages',
      valueType: 'image',
    },
    {
      title: '操作',
      key: 'eventOperation',
      render: (_, record) => {
        return (<Space size="middle">
          <a key ={`eventEditing+${record.identifierCode}`} onClick={() => {handleEditableRowModal(record.identifierCode)}}>
            编辑
          </a>
        </Space>)
      }
    }
  ];


  // Table Edit
  const handleEditableRowModal = async (id) => {
    

    const eventRes = await axios.get(`http://127.0.0.1:8083/events/${id}`);
  
    const eventFieldData = eventRes.data
    console.log(eventFieldData)
    const eventParameterFieldData = eventFieldData.parameterObjs;
    formCreateEvent.setFieldsValue(
      {
        "id":eventFieldData["id"],
        "eventName":eventFieldData["eventName"],
        "eventType":eventFieldData["eventType"],
        "eventDesc":eventFieldData["eventDesc"],
        "eventStatus":eventFieldData["eventStatus"],
        "gmtCreate":eventFieldData["gmtCreate"],
        "gmtModify":eventFieldData["gmtCreate"],
        "triggerTimes":eventFieldData["triggerTimes"],
        "creator":eventFieldData["creator"],
        "eventOnlineTime":eventFieldData["eventOnlineTime"],
        "eventOfflineTime": eventFieldData["eventOfflineTime"],
        "identifierCode":eventFieldData["identifierCode"],
        "sampleImages":eventFieldData["sampleImages"]
      }
    );
    setCreateEventParameterTableData(eventParameterFieldData);
    setModalMode(1); // Indicating editing
    setModalVisible(true);
  }



  // Dropdown menu
  const [parameters, setParameters] = useState([]);
  const [api, contextHolder] =  notification.useNotification();

  const menu = (
    <Menu>
      {parameters.map((param) => (
        <Menu.Item key={param.id} onClick={() => {handleDropDownParameterClick(param.id)}}>
          {param.label}
        </Menu.Item>
      ))}
    </Menu>
  );


  const handleDropDownParameterClick = async (id) => {
    const res = await axios.get(`http://127.0.0.1:8083/parameters/${id}`);
    const elem = res.data;
    const tempData = res.data !== null ? {"identifierCode": elem.identifierCode
      , "parameterName": elem.parameterName
      , "parameterType":elem.parameterType, }: null;


    if (createEventParameterTableData.filter(elem => elem.identifierCode===id).length != 0) {
      notification.open({
        message: 'Notification Title',
        description:
          'Have added it before, cannot add the same parameter twice',
        duration: 0,
        placement: "topLeft"
      })
      return;
    }
    let finalData = [...createEventParameterTableData];
    if (tempData != null) {
      finalData = [...finalData, tempData];
    }
    setCreateEventParameterTableData(finalData);
  }


  // 已被管理的属性列表
  const handleDropdownContent = async () => {
    const response = await axios.get("http://127.0.0.1:8083/parameters/allname");
    const temp = response.data.map(elem => {return {"id":elem, "label":elem}});
    setParameters(temp);
  }
  
  const removeFromList = (id) => {
    const finalData = createEventParameterTableData.filter(elem => elem.identifierCode!==id);
    setCreateEventParameterTableData(finalData);
  }



  return (
    <>
      <ProTable
        className={styles.temp}
        columns={proTableColumnsEvent}
        request={async (params, sorter, filter) => {
          // localhost doesn't support https request, use http instead
              const res = await axios.get('http://127.0.0.1:8083/events/all', {
                params: {
                  ...params,
                  sorter: sorter && Object.keys(sorter).length ? sorter : null,
                  filter,
                },
              });           
              const finalData = res.data.length != 0 ? res.data.filter(elem=>elem.eventStatus !== "Deleted") :[]; 

              return {
                data: finalData,
                success: true,
                total: parseInt(res.headers['x-total-count'], 10),
              };
          }}
        actionRef = {proTableRef}
        rowKey="id"
        dateFormatter="string"
        headerTitle="事件管理"
        toolBarRender={() => [
          <Button key="buttonEvent" icon={<PlusOutlined />} type="primary" onClick={() => { setModalMode(0);setModalVisible(true)}}>
            新增事件
          </Button>,
        ]}
      />
      <Modal
        title={modalMode === 1? "编辑页面事件": "新增页面事件"}
        visible={modalVisible}
        onOk={onFormCreateEventOk}
        onCancel={onFormCreateEventCancel}
        footer={[
          <Button key="back" onClick={onFormCreateEventCancel}>
            取消
          </Button>,
          <Button key="submit" type="primary" onClick={() => formCreateEvent.submit()}>
            {modalMode === 1? "提交更改": "创建"}
          </Button>,
        ]}
      >
        <Form
          form= {formCreateEvent}
          // ref = {formCreateEventRef}
          layout="vertical"
          onFinish={onFormCreateEventFinish}
          initialValues={{ active: true }}
        >
        <div className={"section-divider"}>
          1.请设置基本信息
        </div>
          <Form.Item 
            name="eventName" 
            label="事件名称" 
            rules={[{ required: true, message: '请输入事件名称'}]}>
             <Input />
          </Form.Item>
          {modalMode === 1 ? 
          
            (
              <>
                <Form.Item 
                  name="identifierCode" 
                  label="事件ID" 
                  >
                  <Input disabled={true}></Input>
                </Form.Item>
                <Form.Item 
                  name="id" 
                  label="事件PK" 
                  style={{display:'none'}}
                  >
                  <Input disabled={true}></Input>
                </Form.Item>
                <Form.Item 
                  name="gmtCreate" 
                  label="事件创建时间" 
                  style={{display:'none'}}
                  >
                  <Input></Input>
                </Form.Item>
                <Form.Item
                  name="gmtModify" 
                  label="最近修改时间" 
                  style={{display:'none'}}
                  >
                  <Input></Input>
                </Form.Item>
                <Form.Item 
                  name="eventOnlineTime" 
                  label="事件上线时间" 
                  style={{display:'none'}}
                  >
                  <Input></Input>
                </Form.Item>
                <Form.Item 
                  name="eventOfflineTime" 
                  label="事件下线时间" 
                  style={{display:'none'}}
                  >
                  <Input></Input>
                </Form.Item>
                <Form.Item 
                  name="triggerTimes" 
                  label="事件触发次数" 
                  style={{display:'none'}}
                  >
                  <Input></Input>
                </Form.Item>
                <Form.Item 
                  name="creator" 
                  label="事件创建人" 
                  style={{display:'none'}}
                  >
                  <Input></Input>
                </Form.Item>
                <Form.Item 
                  name="sampleImages" 
                  label="事件图片" 
                  style={{display:'none'}}
                  >
                  <Input></Input>
                </Form.Item>
              </>
            )
          :null}
          <Form.Item 
            name="eventType" 
            label="事件类型"
            rules={[{ required: true, message: '请输入事件类型'}]}>
            <Select
               showSearch
               onClick={handleEventTypeDropdownRender}
               onChange={handleEventTypeDropDownClick}
               placeholder={"Search to Select"}
               options={eventTypes}
               value={selectedEventType}
            />
          </Form.Item>
          <Form.Item 
            name="eventStatus" 
            label="事件状态"
            rules={[{ required: true, message: '请选择事件状态'}]}>
            <Select
               showSearch
               onClick={handleEventStatusDropdownRender}
               onChange={handleEventStatusDropDownClick}
               placeholder={"Search to Select"}
               options={eventStatus}
            />
          </Form.Item>
          <Form.Item 
            name="eventDesc" 
            label="事件描述">
            <Input.TextArea/>
          </Form.Item>
          {/* <Form.Item 
            name="sampleImages" 
            label="事件图片">
            <Upload
              name="avatar"
              listType="picture-card"
              className="avatar-uploader"
              // showUploadList={false}
            >
              <Button
                style={{
                  border: 0,
                  background: 'none',
                }}
                // type="button"
              >
                <PlusOutlined />
                <div
                  style={{
                    marginTop: 8,
                  }}
                >
                  Upload
                </div>
              </Button>
            </Upload>
          </Form.Item> */}
          {/* {modalMode == 1? 
            <>
                <Form.Item 
                  name="gmt" 
                  label="事件描述">
                  <Input.TextArea/>
                </Form.Item>
                <Form.Item 
                  name="eventDesc" 
                  label="事件描述">
                  <Input.TextArea/>
                </Form.Item>
                <Form.Item 
                  name="eventDesc" 
                  label="事件描述">
                  <Input.TextArea/>
                </Form.Item>
            </>: null
          } */}
        </Form>
        <div className={"section-divider"}>
          2.请设置事件属性
        </div>
        <Form 
          form={formCreateParameter} 
          // ref={formCreateParameterRef}
          component={false}>
          <Dropdown overlay = {menu} placement="bottomLeft" trigger={["click"]}>
            <Button
              onClick={handleDropdownContent}
              type="primary"
              style={{ marginBottom: 16 }}
            >
              <PlusOutlined /> 添加新属性
            </Button>
          </Dropdown>
          <Button
              onClick={()=>{setMenuIndex('3')}}
              type="primary"
              style={{ marginBottom: 16, marginLeft:5}}
            >
              <PlusOutlined /> 属性管理
            </Button>
          <Table
            bordered
            dataSource={createEventParameterTableData}
            columns={modalColumnsParameter}
          />
        </Form>

      </Modal>
    </>
  );
}
