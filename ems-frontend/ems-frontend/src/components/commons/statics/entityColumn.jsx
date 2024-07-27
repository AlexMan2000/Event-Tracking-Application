export const projectEntityColumn = [
    {
        title: '项目 ID',
        dataIndex: 'id',
        key: 'id',
        valueType: 'text',
      },
      {
        title: '创建时间',
        dataIndex: 'gmtCreate',
        key: 'gmtCreate',
        valueType: 'dateTime',
      },
      {
        title: '修改时间',
        dataIndex: 'gmtModify',
        key: 'gmtModify',
        valueType: 'dateTime',
      },
      {
        title: '项目名称',
        dataIndex: 'projectName',
        key: 'projectName',
        valueType: 'text',
      },
      {
        title: '项目描述',
        dataIndex: 'projectDesc',
        key: 'projectDesc',
        valueType: 'text',
      },
      {
        title: '项目URL',
        dataIndex: 'projectUrl',
        key: 'projectUrl',
        valueType: 'text',
      },
      {
        title: '项目状态',
        dataIndex: 'projectStatus',
        key: 'projectStatus',
        valueType: 'text',
      },
      {
        title: '项目上线时间',
        dataIndex: 'projectOnlineTime',
        key: 'projectOnlineTime',
        valueType: 'dateTime',
      },
      {
        title: '项目下线时间',
        dataIndex: 'projectOfflineTime',
        key: 'projectOfflineTime',
        valueType: 'dateTime',
      },
      {
        title: '项目经理',
        dataIndex: 'projectManager',
        key: 'projectManager',
        valueType: 'text',
      },
      {
        title: '产品经理',
        dataIndex: 'productManager',
        key: 'productManager',
        valueType: 'text',
      },
      {
        title: '创建者',
        dataIndex: 'creator',
        key: 'creator',
        valueType: 'text',
      },
      {
        title: '标识码',
        dataIndex: 'identifierCode',
        key: 'identifierCode',
        valueType: 'text',
      }
      
]


export const moduleEntityColumn = [
  {
      title: 'Module ID',
      dataIndex: 'id',
      key: 'id',
      valueType: 'text',
    },
    {
      title: 'Module Creation Time',
      dataIndex: 'gmtCreate',
      key: 'gmtCreate',
      valueType: 'dateTime',
    },
    {
      title: 'Module Modified Time',
      dataIndex: 'gmtModify',
      key: 'gmtModify',
      valueType: 'dateTime',
    },
    {
      title: 'Module Name',
      dataIndex: 'moduleName',
      key: 'moduleName',
      valueType: 'text',
    },
    {
      title: 'Module 编码',
      dataIndex: 'identifierCode',
      key: 'identifierCode',
      valueType: 'text',
    },
    {
      title: '模块负责人',
      dataIndex: 'moduleOwner',
      key: 'moduleOwner',
      valueType: 'text',
    },
    {
      title: '模块描述',
      dataIndex: 'moduleDesc',
      key: 'moduleDesc',
      valueType: 'text',
    },
    {
      title: '所属页面',
      dataIndex: 'belongPages',
      key: 'belongPages',
      valueType: 'text',
    },
    {
      title: '模块状态',
      dataIndex: 'moduleStatus',
      key: 'moduleStatus',
      valueType: 'text',
    },
    {
      title: '触发次数',
      dataIndex: 'triggerTimes',
      key: 'triggerTimes',
      valueType: 'text',
    },
    {
      title: '示例图片',
      dataIndex: 'sampleImages',
      key: 'sampleImages',
      valueType: 'text',
    },
    {
      title: '模块事件',
      dataIndex: 'moduleEvents',
      key: 'moduleEvents',
      valueType: 'text',
    },
    {
      title: '创建者',
      dataIndex: 'creator',
      key: 'creator',
      valueType: 'text',
    },
    {
      title: '上线时间',
      dataIndex: 'moduleOnlineTime',
      key: 'moduleOnlineTime',
      valueType: 'dateTime',
    },
    {
      title: '下线时间',
      dataIndex: 'moduleOfflineTime',
      key: 'moduleOfflineTime',
      valueType: 'dateTime',
    }
    
]


export const eventEntityColumn = [
  {
    title: '事件 ID',
    dataIndex: 'id',
    key: 'id',
    valueType: 'text',
  },
  {
    title: '标识码',
    dataIndex: 'identifierCode',
    key: 'identifierCode',
    valueType: 'text',
  },
  {
    title: '创建时间',
    dataIndex: 'gmtCreate',
    key: 'gmtCreate',
    valueType: 'dateTime',
  },
  {
    title: '修改时间',
    dataIndex: 'gmtModify',
    key: 'gmtModify',
    valueType: 'dateTime',
  },
  {
    title: '事件名称',
    dataIndex: 'eventName',
    key: 'eventName',
    valueType: 'text',
  },
  {
    title: '事件描述',
    dataIndex: 'eventDesc',
    key: 'eventDesc',
    valueType: 'text',
  },
  {
    title: '事件类型',
    dataIndex: 'eventType',
    key: 'eventType',
    valueType: 'text',
  },
  {
    title: '事件状态',
    dataIndex: 'eventStatus',
    key: 'eventStatus',
    valueType: 'text',
  },
  {
    title: '触发次数',
    dataIndex: 'triggerTimes',
    key: 'triggerTimes',
    valueType: 'text',
  },
  {
    title: '创建者',
    dataIndex: 'creator',
    key: 'creator',
    valueType: 'text',
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
    title: '示例图片',
    dataIndex: 'sampleImages',
    key: 'sampleImages',
    valueType: 'text',
  }
  
]


export const parameterEntityColumn = [
  {
    title: '参数 ID',
    dataIndex: 'id',
    key: 'id',
    valueType: 'text',
  },
  {
    title: '创建时间',
    dataIndex: 'gmtCreate',
    key: 'gmtCreate',
    valueType: 'dateTime',
  },
  {
    title: '修改时间',
    dataIndex: 'gmtModify',
    key: 'gmtModify',
    valueType: 'dateTime',
  },
  {
    title: '参数名称',
    dataIndex: 'parameterName',
    key: 'parameterName',
    valueType: 'text',
  },
  {
    title: '参数描述',
    dataIndex: 'parameterDesc',
    key: 'parameterDesc',
    valueType: 'text',
  },
  {
    title: '参数值',
    dataIndex: 'parameterValue',
    key: 'parameterValue',
    valueType: 'text',
  },
  {
    title: '参数类型',
    dataIndex: 'parameterType',
    key: 'parameterType',
    valueType: 'text',
  },
  {
    title: '创建者',
    dataIndex: 'creator',
    key: 'creator',
    valueType: 'text',
  },
  {
    title: '标识码',
    dataIndex: 'identifierCode',
    key: 'identifierCode',
    valueType: 'text',
  }
  
]


export const pageEntityColumn = [
  {
    title: '页面 ID',
    dataIndex: 'id',
    key: 'id',
    valueType: 'text',
  },
  {
    title: '创建时间',
    dataIndex: 'gmtCreate',
    key: 'gmtCreate',
    valueType: 'dateTime',
  },
  {
    title: '修改时间',
    dataIndex: 'gmtModify',
    key: 'gmtModify',
    valueType: 'dateTime',
  },
  {
    title: '页面英文名称',
    dataIndex: 'pageEnglishName',
    key: 'pageEnglishName',
    valueType: 'text',
  },
  {
    title: '页面中文名称',
    dataIndex: 'pageChineseName',
    key: 'pageChineseName',
    valueType: 'text',
  },
  {
    title: '页面描述',
    dataIndex: 'pageDesc',
    key: 'pageDesc',
    valueType: 'text',
  },
  {
    title: '页面类型',
    dataIndex: 'pageType',
    key: 'pageType',
    valueType: 'text',
  },
  {
    title: '页面状态',
    dataIndex: 'pageStatus',
    key: 'pageStatus',
    valueType: 'text',
  },
  {
    title: '客户端版本',
    dataIndex: 'clientVersion',
    key: 'clientVersion',
    valueType: 'text',
  },
  {
    title: '创建者名称',
    dataIndex: 'creatorName',
    key: 'creatorName',
    valueType: 'text',
  },
  {
    title: '审核者名称',
    dataIndex: 'auditorName',
    key: 'auditorName',
    valueType: 'text',
  },
  {
    title: '父页面',
    dataIndex: 'parentPages',
    key: 'parentPages',
    valueType: 'text',
  },
  {
    title: '示例图片',
    dataIndex: 'sampleImages',
    key: 'sampleImages',
    valueType: 'text',
  },
  {
    title: '标识码',
    dataIndex: 'identifierCode',
    key: 'identifierCode',
    valueType: 'text',
  }
  
]