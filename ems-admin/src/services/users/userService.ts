import axiosInstance from "../../apis/authInstance";

export const getAllUsers = async (params: any, sorter:any, filter:any): Promise<any> => {
    const res = await axiosInstance.get("/users/all", {
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