import React from "react"
import {Tag} from "antd";
import EditableTable from "../../commons/tables/EditableTable"
import { projectEntityColumn } from "../../commons/statics/entityColumn";
import { stateToTagColor, states } from "../../commons/statics/renderMappings";

import { entityNameInterface } from "../../../interfaces/commonInterface";


const ProjectManagementIndex = ({setMenuIndex, databaseName}) => {

    const entityName: entityNameInterface = databaseName;

    const entityTableColumns = projectEntityColumn.map(elem => {
        if (elem.title === "项目状态") {
            return {
                title: '项目状态',
                dataIndex: 'projectStatus',
                key: 'projectStatus',
                render: (_, {projectStatus}) => {
                  return (
                      <Tag color={stateToTagColor[projectStatus]} key={projectStatus}>
                        {projectStatus}
                      </Tag>
                )},
              }
        }else {
            return elem;
        }
    })
    

    const entityCreateFields = [
          {
            title: `Project Name`,
            dataIndex: `projectName`,
            key: 'projectName',
            componentType: 'text',
            display: true,
            editable: true,
            rules:[{ required: true, message: 'Please input project name'}]
          },
          {
            title: `Project Creator`,
            dataIndex: 'creator',
            key: 'creator',
            componentType: 'text',
            display: true,
            editable: true,
            rules:[{ required: true, message: 'Please input project creator'}]
          },
          {
            title: `Project Url`,
            dataIndex: `projectUrl`,
            key: `projectUrl`,
            componentType: 'text',
            display: true,
            editable: true,
            rules:[{ required: true, message: 'Please input project url'}]
          },
          {
            title: 'Project Desc',
            dataIndex: 'projectDesc',
            key: 'projectDesc',
            componentType: 'textArea',
            display: true,
            editable: true,
            rules:[]
          },
          {
            title: 'Project Manager',
            dataIndex: 'projectManager',
            key: 'projectManager',
            componentType: 'text',
            display: true,
            editable: true,
            rules:[{ required: true, message: 'Please input project manager'}]
          },
          {
            title: 'Product Manager',
            dataIndex: 'productManager',
            key: 'productManager',
            componentType: 'text',
            display: true,
            editable: true,
            rules:[{ required: true, message: 'Please input product manager'}]
          },
          {
            title: 'Project Status',
            dataIndex: 'projectStatus',
            key: 'projectStatus',
            componentType: 'selectStatic',
            options: states.map((elem, index) => ({key: `select-option-${elem}-${index}`,value: elem, label: elem})),
            display: true,
            editable: true,
            rules:[{ required: true, message: 'Please select project status'}]
          },
          {
            title: `Project 编码`,
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
            title: `Project ID`,
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
    
    const moduleModalTableColumns = [
        {
            title: 'Module ID',
            dataIndex: 'id',
            key: 'id',
          },
          {
            title: 'Module 编码',
            dataIndex: 'identifierCode',
            key: 'identifierCode',
          },
          {
            title: 'Module Name',
            dataIndex: 'moduleName',
            key: 'moduleName',
          },
          {
            title: 'Module Status',
            dataIndex: 'moduleStatus',
            key: 'moduleStatus',
          },
    ]

    const addprosConfig = {
        foreignRelations : ["parameter", "module"],
        parameter: parameterModalTableColumns,
        module: moduleModalTableColumns
    }

    return ( 
        <EditableTable
            setMenuIndex = {setMenuIndex}
            entityName = {entityName}
            entityTableColumns = {entityTableColumns}
            entityCreateFields = {entityCreateFields}
            entityEditFields = {entityEditFields}
            addprosConfig = {addprosConfig}
        ></EditableTable>
    )
} 

export default ProjectManagementIndex;