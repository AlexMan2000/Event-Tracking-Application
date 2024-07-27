
import React from 'react'
import {Tag} from "antd";
import EditableTable from "../../commons/tables/EditableTable"
import { eventEntityColumn, moduleEntityColumn, pageEntityColumn } from "../../commons/statics/entityColumn";
import { stateToTagColor, states } from "../../commons/statics/renderMappings";

import { entityNameInterface } from "../../../interfaces/commonInterface";


function PageManagementIndex({setMenuIndex, databaseName}) {
    const entityName: entityNameInterface = databaseName;

    const entityTableColumns = pageEntityColumn.map(elem => {
        if (elem.title === "页面状态") {
            return {
                title: '页面状态',
                dataIndex: 'pageStatus',
                key: 'pageStatus',
                render: (_, {pageStatus}) => {
                  return (
                      <Tag color={stateToTagColor[pageStatus]} key={pageStatus}>
                        {pageStatus}
                      </Tag>
                )},
              }
        }else {
            return elem;
        }
    })


    const entityCreateFields = [
      {
        title: `Page English Name`,
        dataIndex: `pageEnglishName`,
        key: 'pageEnglishName',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please input page english name'}]
      },
      {
        title: `Page Chinese Name`,
        dataIndex: `pageChineseName`,
        key: 'pageChineseName',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please input page chinese name'}]
      },
      {
        title: `Page Creator`,
        dataIndex: 'creatorName',
        key: 'creatorName',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please input page creator'}]
      },
      {
        title: `Page Auditor`,
        dataIndex: 'auditorName',
        key: 'auditorName',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please input page auditor'}]
      },
      {
        title: 'Page Desc',
        dataIndex: 'pageDesc',
        key: 'pageDesc',
        componentType: 'textArea',
        display: true,
        editable: true,
        rules:[]
      },
      {
        title: 'Page Status',
        dataIndex: 'pageStatus',
        key: 'pageStatus',
        componentType: 'selectStatic',
        options: states.map((elem, index) => ({key: `select-option-${elem}-${index}`,value: elem, label: elem})),
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please select page status'}]
      },
      {
        title: 'Page Type',
        dataIndex: 'pageType',
        key: 'pageType',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please select page type'}]
      },
      {
        title: 'Parent Page',
        dataIndex: 'parentPages',
        key: 'parentPages',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: false, message: 'Please input parent page'}]
      },
      {
        title: 'Parent Client Version',
        dataIndex: 'clientVersion',
        key: 'clientVersion',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please input page version'}]
      },
      {
        title: `Page Sample Image`,
        dataIndex: 'sampleImages',
        key: 'sampleImages',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: false, message: 'Please input page sample image'}]
      },
      {
        title: `Page 编码`,
        dataIndex: 'identifierCode',
        key: 'identifierCode',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please input project creator'}]
      },
]

const entityEditFields = [
    ...entityCreateFields,
    {
        title: `Page ID`,
        dataIndex: 'id',
        key: 'id',
        componentType: 'text',
        display: true,
        editable: false,
        rules:[]
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


const eventModalTableColumns = [
    {
        title: 'Event ID',
        dataIndex: 'id',
        key: 'id',
      },
      {
        title: 'Event 编码',
        dataIndex: 'identifierCode',
        key: 'identifierCode',
      },
      {
        title: 'Event Name',
        dataIndex: 'eventName',
        key: 'eventName',
      },
      {
        title: 'Event Status',
        dataIndex: 'eventStatus',
        key: 'eventStatus',
      },

]


const addprosConfig = {
    foreignRelations : ["parameter", "event"],
    parameter: parameterModalTableColumns,
    event: eventModalTableColumns
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


export default PageManagementIndex;