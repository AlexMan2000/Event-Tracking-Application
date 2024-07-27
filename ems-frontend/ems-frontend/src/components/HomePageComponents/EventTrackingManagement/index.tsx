import React from 'react'
import {Tag} from "antd";
import EditableTable from "../../commons/tables/EditableTable"
import { eventEntityColumn } from "../../commons/statics/entityColumn";
import { stateToTagColor, states } from "../../commons/statics/renderMappings";

import { entityNameInterface } from "../../../interfaces/commonInterface";



function EventManagementIndex({setMenuIndex, databaseName}) {

  const entityName: entityNameInterface = databaseName;

    const entityTableColumns = eventEntityColumn.map(elem => {
        if (elem.title === "事件状态") {
            return {
                title: '事件状态',
                dataIndex: 'eventStatus',
                key: 'eventStatus',
                render: (_, {eventStatus}) => {
                  return (
                      <Tag color={stateToTagColor[eventStatus]} key={eventStatus}>
                        {eventStatus}
                      </Tag>
                )},
              }
        }else {
            return elem;
        }
    })


    const entityCreateFields = [
      {
        title: `Event Name`,
        dataIndex: `eventName`,
        key: 'eventName',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please input event name'}]
      },
      {
        title: `Event Creator`,
        dataIndex: 'creator',
        key: 'creator',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please input event creator'}]
      },
      {
        title: 'Event Desc',
        dataIndex: 'eventDesc',
        key: 'eventDesc',
        componentType: 'textArea',
        display: true,
        editable: true,
        rules:[]
      },
      {
        title: 'Event Status',
        dataIndex: 'eventStatus',
        key: 'eventStatus',
        componentType: 'selectStatic',
        options: states.map((elem, index) => ({key: `select-option-${elem}-${index}`,value: elem, label: elem})),
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please select event status'}]
      },
      {
        title: 'Event Type',
        dataIndex: 'eventType',
        key: 'eventStatus',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please select event type'}]
      },
      {
        title: `Event 编码`,
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
        title: `Event ID`,
        dataIndex: 'id',
        key: 'id',
        componentType: 'text',
        display: true,
        editable: false,
        rules:[]
    },
    {
      title: 'Event Trigger Times',
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



const addprosConfig = {
    foreignRelations : ["parameter"],
    parameter: parameterModalTableColumns
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

export default EventManagementIndex;