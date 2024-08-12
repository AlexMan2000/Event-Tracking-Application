import type {
    AxiosRequestConfig,
    AxiosResponse,
    InternalAxiosRequestConfig,
  } from 'axios'
  


/**
 * 1. AxiosRequestConfig, add additional configurations to our HTTP request header
 *  const config: AxiosRequestConfig = {
        url: '/user',
        method: 'get',
        params: { id: 123 },
        headers: { 'Authorization': 'Bearer token' },
    };
 * 
 * 2. AxiosReponse: when a request is successful, axios returns a reponse object that contains 
 * the data received from the server, as well as some additional  information about the request 
 * and response
 * 
 * Some of these attributes of the axiosResponse obj
 *    data: The data returned by the server (e.g., JSON data, HTML, text).
      status: The HTTP status code of the response (e.g., 200, 404).
      statusText: The HTTP status text (e.g., "OK", "Not Found").
      headers: The HTTP headers returned by the server.
      config: The AxiosRequestConfig object that was used to make the request.
      request: The request object, which is the underlying XMLHttpRequest or Node.js 
 * 
    const handleResponse = (response: AxiosResponse) => {
    console.log('Data:', response.data);
    console.log('Status:', response.status); status code
    console.log('Headers:', response.headers);  response headers
    };
 * 
 * 3. InternalAxiosRequestConfig:  internal to Axios and extends AxiosRequestConfig with additional properties that Axios uses internally when processing requests. This type is not typically used directly by developers but is instead used within the Axios library to manage requests.
 * 
 * 
 * const config: InternalAxiosRequestConfig = {
    url: '/user',
    method: 'get',
    baseURL: 'https://api.example.com',
    transformRequest: [(data) => {
        // Custom transformation logic
        return data;
    }],
    validateStatus: (status) => {
        return status >= 200 && status < 300; // default
    }
};
 */

  // 定义一个常见后端请求返回
  export type BaseApiResponse<T> = {
    code: number
    message: string
    result: T
  }
  // 拓展 axios 请求配置，加入我们自己的配置
  interface RequestOptions {
    // 是否全局展示请求 错误信息
    globalErrorMessage?: boolean
    // 是否全局展示请求 成功信息
    globalSuccessMessage?: boolean
  }
  
  // 拓展自定义请求配置
  export interface ExpandAxiosRequestConfig<D = any>
    extends AxiosRequestConfig<D> {
    interceptorHooks?: InterceptorHooks // 配置拦截器
    requestOptions?: RequestOptions
  }
  
  // 拓展 axios 请求配置
  export interface ExpandInternalAxiosRequestConfig<D = any>
    extends InternalAxiosRequestConfig<D> {
    interceptorHooks?: InterceptorHooks
    requestOptions?: RequestOptions
  }
  
  // 拓展 axios 返回配置
  export interface ExpandAxiosResponse<T = any, D = any>
    extends AxiosResponse<T, D> {
    config: ExpandInternalAxiosRequestConfig<D>
  }
  
  // 定义拦截器
  export interface InterceptorHooks {
    requestInterceptor?: (
      config: InternalAxiosRequestConfig
    ) => InternalAxiosRequestConfig
    requestInterceptorCatch?: (error: any) => any
    responseInterceptor?: (
      response: AxiosResponse
    ) => AxiosResponse | Promise<AxiosResponse>
    responseInterceptorCatch?: (error: any) => any
  }
  