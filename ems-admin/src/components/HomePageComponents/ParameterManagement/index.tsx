import React, {useState} from "react"
import ParameterTable from "./ParameterTable"

import {Tag} from "antd";
import EditableTable from "../../commons/tables/EditableTable"
import { eventEntityColumn, moduleEntityColumn, pageEntityColumn } from "../../commons/statics/entityColumn";
import { stateToTagColor, states } from "../../commons/statics/renderMappings";

import { entityNameInterface } from "../../../interfaces/commonInterface";
import { parameterEntityColumn } from "../../commons/statics/entityColumn";

function ParameterManagementIndex({setMenuIndex, databaseName}) {
    const entityName: entityNameInterface = databaseName;



    const entityCreateFields = [
      {
        title: `Parameter Name`,
        dataIndex: `parameterName`,
        key: 'parameterName',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please input page parameter name'}]
      },
      {
        title: `Parameter Creator`,
        dataIndex: 'creator',
        key: 'creator',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please input parameter creator'}]
      },
      {
        title: 'Parameter Desc',
        dataIndex: 'parameterDesc',
        key: 'parameterDesc',
        componentType: 'textArea',
        display: true,
        editable: true,
        rules:[]
      },
      {
        title: 'Parameter Value',
        dataIndex: 'parameterValue',
        key: 'parameterValue',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please input parameter value'}]
      },
      {
        title: 'Parameter Type',
        dataIndex: 'parameterType',
        key: 'parameterType',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please select parameter type'}]
      },
      {
        title: `Parameter 编码`,
        dataIndex: 'identifierCode',
        key: 'identifierCode',
        componentType: 'text',
        display: true,
        editable: true,
        rules:[{ required: true, message: 'Please input parameter 编码'}]
      },
]

const entityEditFields = [
    ...entityCreateFields,
    {
        title: `Parameter ID`,
        dataIndex: 'id',
        key: 'id',
        componentType: 'text',
        display: true,
        editable: false,
        rules:[]
    },
]





const addprosConfig = {
    foreignRelations : [],
}


  return (
    <>
      <EditableTable setMenuIndex={setMenuIndex}
          entityName = {entityName}
          entityTableColumns = {parameterEntityColumn}
          entityCreateFields = {entityCreateFields}
          entityEditFields = {entityEditFields}
          addprosConfig = {addprosConfig}
      />
    </>
  )
}


export default ParameterManagementIndex;
