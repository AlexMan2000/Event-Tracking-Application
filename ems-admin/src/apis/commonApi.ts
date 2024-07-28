import { entityNameInterface } from "../interfaces/commonInterface"

const SERVER_BASE:string = "http://localhost:8083"

export const getAllMetadataApi = (propName: entityNameInterface) => {
    return `${SERVER_BASE}/${propName}s/meta`
}

export const getRequestBaseUrlApi = (propName: entityNameInterface) => {
    return `${SERVER_BASE}/${propName}s`
}


export const getByIdApi = (id: number, propName: entityNameInterface) => {
    return `${SERVER_BASE}/${propName}s/${id}`
}