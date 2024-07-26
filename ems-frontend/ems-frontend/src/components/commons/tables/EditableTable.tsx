import React, {useState, useRef, useEffect} from 'react'
import axios from 'axios';
import ProTable from '@ant-design/pro-table';
import {Tag, notification, Space, Button, Modal, Form, Upload, Switch, Select, Input, Table, Menu, Dropdown} from "antd";
import {PlusOutlined, DownOutlined} from "@ant-design/icons";
import { states, entityNameToName, entityToMenuIndex } from '../statics/renderMappings';
import DynamicSelect from '../utils/DynamicSelect';
import { getAllMetadataApi, getByIdApi } from '../../../apis/commonApi';
import { entityNameInterface } from '../../../interfaces/commonInterface';
import { truncateObjectByExcluding, truncateObjectByIncluding, sortForeignRelationBy } from '../utils/objectHandlers';


interface MetadataItem {
  id: number;
  identifierCode: string
}


type MetadataMap = {
  [key: string]: MetadataItem[]
}


type EntityMap = {
  [key: string]: any[]
}

export default function EditableTable(props) {

  const {
    setMenuIndex
    , requestBase
    , entityName
    , entityTableColumns
    , entityCreateFields
    , entityEditFields
    , addprosConfig} = props;


  const displayEntityName = entityName.charAt(0).toUpperCase() + entityName.slice(1);
  const relationTableDataInitialState = addprosConfig.foreignRelations.reduce((acc: any, relation: entityNameInterface) => {
    acc[`${relation}s`] = [];
    return acc;
  }, {});
  const dropdownMetaDataInitialState = addprosConfig.foreignRelations.reduce((acc: any, relation: entityNameInterface) => {
    acc[`${relation}s`] = [];
    return acc;
  }, {});
  const foreignRelationNames = addprosConfig?.foreignRelations;
   

  // Output:  {parameters: [], modules: []}
  const [foreignRelationTableData, setForeignRelationTableData] = useState<EntityMap>(relationTableDataInitialState);
  const [foreignRelationMetadata, setForeignRelationMetadata] = useState<MetadataMap>(dropdownMetaDataInitialState);
  
  // ProTable Columns
  const proTableColumns = [
    ...entityTableColumns,
    {
      title: '操作',
      key: 'entityOperation',
      render: (_, record) => {
        // console.log(record);
        return (<Space size="middle">
          <a key ={`entityEditing+${record.id}`} onClick={() => {handleEditableRowModal(record.id)}}>
            Edit
          </a>
        </Space>)
      }
    }
  ];
  const proTableRef = useRef<any>();
  




  // Modal related
  const [modalVisible, setModalVisible] = useState(false);
  const [modalMode, setModalMode] = useState(0);  // 0 for create, 1 for edit
  const modalShowUpFields = modalMode == 0 ? entityCreateFields : entityEditFields;
  const hideModal = () => { setModalVisible(false)};


  // Modal Table Column
  const getModalTableColumn = (propName: entityNameInterface) => {
    return [
      ...addprosConfig[propName], 
      {
        title: '操作',
        dataIndex: 'operation',
        render: (_: any, record: any) => {
          return (
            <span>
              <a onClick={() => handleModalTableRemoveRow(record.id, propName)} style={{ marginRight: 8 }}>
                移除
              </a>
            </span>
          )
        },
      }
    ]
  }


  // Form Related
  const [formCreateUpdateEntity] = Form.useForm();

  // Form submission
  const onFormCreateUpdateEntityFinish = async (values: any) => {
    let postData:any = {
      ...values,
    }
    const postDataObj = addprosConfig.foreignRelations.reduce((prev: any, propName: entityNameInterface, index: number) => {
      const propIds = foreignRelationTableData[`${propName}s`].map((metadata: any) => metadata.id);
      
      prev = {
        ...prev,
        [`${propName}Ids`]: propIds
      }
      return prev;

    }, postData)


    console.log(postDataObj)
    let res:any = null;
    if (modalMode === 1) {
      // Update
      res = await axios.put(`${requestBase}/update`, postDataObj)
    } else {
      // Create 
      res = await axios.post(`${requestBase}/create`, postDataObj)
    }
    
    if (res.status == 200) {
      setModalVisible(false);
      proTableRef.current.reload();
    }

  }


  const onModalFormOk = () => {
    formCreateUpdateEntity.resetFields();
    setForeignRelationTableData(relationTableDataInitialState);
    setForeignRelationMetadata(dropdownMetaDataInitialState);
    hideModal();
  }

  const onModalFormCancel = () => {
    formCreateUpdateEntity.resetFields();
    setForeignRelationTableData(relationTableDataInitialState);
    setForeignRelationMetadata(dropdownMetaDataInitialState);
    hideModal();
  }


  
  const handleTableRowClick = (record: any) => {
    // console.log(record);
  }


  // Table Edit
  const handleEditableRowModal = async (id: number) => {
    
    const url = `${requestBase}/${id}`
    const res = await axios.get(url);
  
    const entityObjectData = res.data

    let foreignRelationTableDataObj = truncateObjectByIncluding(entityObjectData, addprosConfig.foreignRelations.map((elem:string)=> `${elem}s`));
    const fieldsToExclude: string[] = addprosConfig.foreignRelations.map((propName:entityNameInterface) => (
      `${propName}s`
    ));
    const fieldsToSet = truncateObjectByExcluding(entityObjectData, fieldsToExclude);
    formCreateUpdateEntity.setFieldsValue(
      fieldsToSet
    );

    // Sort
    sortForeignRelationBy(foreignRelationTableDataObj, "id", true);
    
    setForeignRelationTableData(foreignRelationTableDataObj);
    setModalMode(1); // Indicating editing
    setModalVisible(true);
  }

  // 新参数下拉菜单
  const dropdownMenu = 
    addprosConfig.foreignRelations.reduce((acc, propName, index: number) => {

      acc[`${propName}s`] = (
        <Menu>
          {foreignRelationMetadata[`${propName}s`].map((metadata: MetadataItem, index) => {
              console.log(metadata);
              return (
                <Menu.Item key={metadata.id} onClick={() => {handleDropDownPropsClick(metadata.id, propName)}}>
                  {metadata.identifierCode}
                </Menu.Item>
                )
          })}
        </Menu>
      )
      return acc;
  }, {});

  console.log(dropdownMenu)

  // 点击新参数项目按钮
  const handleDropDownPropsClick = async (id: number, propName: any) => {
    const res = await axios.get(getByIdApi(id, propName));

    const tempData = 
    (res.data !== null) ? 
      truncateObjectByIncluding(res.data, addprosConfig[propName].map((elem: any) => elem.dataIndex))
      : null;

      console.log(tempData)

    const prevDataList = foreignRelationTableData[`${propName}s`];
    console.log(prevDataList)
    if (prevDataList?.filter((elem:any) => elem.id===id).length != 0) {
      notification.open({
        message: 'Notification Title',
        description:
          'Have added it before, cannot add the same parameter twice',
        duration: 0,
        placement: "topLeft"
      })
      return;
    }
    let finalDataList = [...prevDataList];
    if (tempData != null) {
      finalDataList = [...finalDataList, tempData];
    }
    setForeignRelationTableData((prevTableData) => {

      const finalObj = {...prevTableData,[`${propName}s`]: finalDataList }
      sortForeignRelationBy(finalObj, "id", true);
      return finalObj;
  });
  }


  // 点击新参数按钮，获取参数列表
  const handleDropdownContent = async (propName: entityNameInterface) => {
    // console.log()
    const response = await axios.get(getAllMetadataApi(propName));
    const temp = response.data.map((elem: MetadataItem) => {return {"id":elem.id, "identifierCode":elem.identifierCode}});
    setForeignRelationMetadata((prevMetadata) => {
      return {...prevMetadata, [`${propName}s`]: temp}
    });
  }
  
  // 移除添加的参数按钮
  const handleModalTableRemoveRow = (id: number, propName: entityNameInterface) => {
    
    setForeignRelationTableData((prevTableData) => {
      const prevDataList = prevTableData[`${propName}s`];
      const finalDataList = prevDataList?.filter(elem => elem.id!==id);
      return {...prevTableData, [`${propName}s`]: finalDataList}
    });
  }



  const handleSelectSearch = () => {

  }

 

  return (
    <>
      <ProTable
        columns={proTableColumns}
        request={async (params, sorter, filter) => {
          // localhost doesn't support https request, use http instead
              const res = await axios.get(`${requestBase}/allfiltered`, {
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
        headerTitle={`${displayEntityName} Management`}
        toolBarRender={() => [
          <Button key="buttonEvent" icon={<PlusOutlined />} type="primary" onClick={() => { setModalMode(0);setModalVisible(true)}}>
            {`Add new ${entityName}`}
          </Button>,
        ]}
        search={{
          labelWidth: 'auto',
          optionRender: (searchConfig, formProps, dom) => [...dom.reverse()],
        }}
        onRow={(record) => ({
          onClick: () => handleTableRowClick(record),
        })}
      />
      <Modal
        title={modalMode === 1? `Edit ${displayEntityName}`: `Add ${displayEntityName}`}
        open={modalVisible}
        onOk={onModalFormOk}
        onCancel={onModalFormCancel}
        width={"650px"}
        footer={[
          <Button key="back" onClick={onModalFormOk}>
            Cancel
          </Button>,
          <Button key="submit" type="primary" onClick={() => formCreateUpdateEntity.submit()}>
            {modalMode === 1? "Submit": "Create"}
          </Button>,
        ]}
      >
        <Form
          form= {formCreateUpdateEntity}
          layout="vertical"
          onFinish={onFormCreateUpdateEntityFinish}
          initialValues={{ active: true }}
        >
        <div className={"section-divider"}>
          1. Please set basic information
        </div>
          {
             modalShowUpFields.map(elem => {
              return (
                <Form.Item
                  name={elem.dataIndex}
                  label={elem.title}
                  rules={elem.rules}
                  style = {{ "display" : elem.display ? "block" : "none"}} >
                    {
                      elem.componentType === "text" ? (
                        <Input 
                          disabled={!elem.editable} 
                        />
                      ) : elem.componentType === "textArea" ? (
                        <Input.TextArea 
                          disabled={!elem.editable} 
                        />
                      ) : elem.componentType === "selectDynamic" ? (
                        <DynamicSelect
                          placeholder = {"Please search or type"}
                          display={elem.display ? "block" : "none"} 
                          editable={elem.editable} 
                          fetchOptions={
                            {
                              requestBase: `${requestBase}`,
                              queryField: elem.dataIndex
                            }
                          }
                        ></DynamicSelect>
                      ) : elem.componentType === "selectStatic" ? (
                        <Select
                          placeholder = {"Please select"}
                          disabled={!elem.editable} 
                          options = {elem.options}
                        >
                        </Select>
                      ): (
                        <div>Unknown component type</div>
                      )
                    }
                </Form.Item>
                )
            }) 
          }
        </Form> 

        {
          addprosConfig?.foreignRelations?.map((addPropName: entityNameInterface, index: number) => 
          {

            return (
              <>
                <div className={"section-divider"}>
                  {`${index + 2}.请设置${entityNameToName[entityName]}${entityNameToName[addPropName]}`}
                </div>
                <Form 
                component={false}>
                <Dropdown 
                  key={`dropdown-${index}`}
                  overlay = {dropdownMenu[`${addPropName}s`]} 
                  placement="bottomLeft" 
                  trigger={["click"]}>
                  <Button
                    onClick={() => {
                      handleDropdownContent(addPropName);
                    }}
                    type="primary"
                    style={{ marginBottom: 16 }}
                  >
                    <PlusOutlined /> {`添加新${entityNameToName[addPropName]}`}
                  </Button>
                </Dropdown>
                <Button
                    onClick={()=>{
                      console.log(new String(entityToMenuIndex[entityName]));
                      setMenuIndex(`${entityToMenuIndex[addPropName]}`)
                      }}
                    type="primary"
                    style={{ marginBottom: 16, marginLeft:5}}
                  >
                    <PlusOutlined />  {`${entityNameToName[addPropName]}管理`}
                  </Button>
                  <Table
                    bordered
                    dataSource={foreignRelationTableData[`${addPropName}s`]}
                    columns={getModalTableColumn(addPropName)}
                  />
                </Form>
              </>
            )
            
          })
        }
      </Modal>
    </>
  );
}
