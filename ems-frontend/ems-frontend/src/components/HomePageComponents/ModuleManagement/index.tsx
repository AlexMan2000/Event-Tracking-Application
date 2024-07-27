import React from 'react'
import {Tag} from "antd";
import EditableTable from "../../commons/tables/EditableTable"
import { eventEntityColumn, moduleEntityColumn } from "../../commons/statics/entityColumn";
import { stateToTagColor, states } from "../../commons/statics/renderMappings";

import { entityNameInterface } from "../../../interfaces/commonInterface";

function ModuleManagementIndex({setMenuIndex, databaseName}) {
    const entityName: entityNameInterface = databaseName;

    const entityTableColumns = moduleEntityColumn.map(elem => {
        if (elem.title === "模块状态") {
            return {
                title: '模块状态',
                dataIndex: 'moduleStatus',
                key: 'moduleStatus',
                render: (_, {moduleStatus}) => {
                  return (
                      <Tag color={stateToTagColor[moduleStatus]} key={moduleStatus}>
                        {moduleStatus}
                      </Tag> 
                )},
              }
        }else {
            return elem;
        }
    })


    const entityCreateFields = [
      {
        title: `Module Name`,
        dataIndex: `moduleName`,
        key: 'moduleName',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please input module name'}]
      },
      {
        title: `Module Owner`,
        dataIndex: 'moduleOwner',
        key: 'moduleOwner',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please input module owner'}]
      },
      {
        title: 'Module Desc',
        dataIndex: 'moduleDesc',
        key: 'moduleDesc',
        componentType: 'textArea',
        display: true,
        editable: true,
        rules:[]
      },
      {
        title: 'Module Status',
        dataIndex: 'moduleStatus',
        key: 'moduleStatus',
        componentType: 'selectStatic',
        options: states.map((elem, index) => ({key: `select-option-${elem}-${index}`,value: elem, label: elem})),
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please select module status'}]
      },
      {
        title: `Event Sample Image`,
        dataIndex: 'sampleImages',
        key: 'sampleImages',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please input module sample image'}]
      },
      {
        title: `Module 编码`,
        dataIndex: 'identifierCode',
        key: 'identifierCode',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please input module identifierCode'}]
      },
      {
        title: `Module Trigger Frequency`,
        dataIndex: 'triggerTimes',
        key: 'triggerTimes',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please input module triggerTimes'}]
      },
      {
        title: `Module Creator`,
        dataIndex: 'creator',
        key: 'creator',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please input module creator'}]
      },
]

const entityEditFields = [
    ...entityCreateFields,
    {
        title: `Module ID`,
        dataIndex: 'id',
        key: 'id',
        componentType: 'text',
        display: true,
        editable: false,
        rules:[]
    },
    {
      title: 'Module Trigger Times',
      dataIndex: 'triggerTimes',
      key: 'triggerTimes',
      componentType: 'text',
      display: true,
      editable: true,
      rules:[{ required: true, message: 'Please input new trigger times'}]
    },
]

const parameterModalTableColumns = [
      {
        title: '属性ID',
        dataIndex: 'id',
        key: 'id',
      },    
      {
        title: '属性编码',
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
]


const pageModalTableColumns = [
    {
        title: 'Page ID',
        dataIndex: 'id',
        key: 'id',
      },
      {
        title: 'Page 编码',
        dataIndex: 'identifierCode',
        key: 'identifierCode',
      },
      {
        title: 'Page English Name',
        dataIndex: 'pageEnglishName',
        key: 'pageEnglishName',
      },
      {
        title: 'Page Chinese Name',
        dataIndex: 'pageChineseName',
        key: 'pageChineseName',
      },
      {
        title: 'Page Status',
        dataIndex: 'pageStatus',
        key: 'pageStatus',
      },
      {
        title: 'Page Client Version',
        dataIndex: 'clientVersion',
        key: 'clientVersion',
      },
]


const addprosConfig = {
    foreignRelations : ["parameter", "page"],
    parameter: parameterModalTableColumns,
    page: pageModalTableColumns
}


  return (
    <>
      <EditableTable setMenuIndex={setMenuIndex}
          entityName = {entityName}
          entityTableColumns = {entityTableColumns}
          entityCreateFields = {entityCreateFields}
          entityEditFields = {entityEditFields}
          addprosConfig = {addprosConfig}
      />
    </>
  )
}


export default ModuleManagementIndex;
