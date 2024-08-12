import {SERVER_BASE} from "../api/commonApi"
import { userInterceptors } from "../config/interceptorHooks"
import Request from "../config/Request"


export const userRequest = new Request({
    baseURL: SERVER_BASE,
    interceptorHooks: userInterceptors
})


export const getAllUsers = async (params: any, sorter:any, filter:any): Promise<any> => {
    const res = await userRequest.get("/users/all", {
        params: {
          ...params,
          sorter: sorter && Object.keys(sorter).length ? sorter : null,
          filter,
        },
      });
      return Promise.resolve({
        data: res.data.length != 0 ? res.data: [],
        success: true,
        total: parseInt(res.headers['x-total-count'], 10),
      });
};

