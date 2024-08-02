
import axios from 'axios';
import { SERVER_BASE } from './commonApi';


const axiosInstance = axios.create({
  baseURL: SERVER_BASE, // Replace with your API base URL
});


// Request interceptor
axiosInstance.interceptors.request.use(
  config => {
    const token = localStorage.getItem('token'); // or get token from Redux store
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

// Response Interceptor
axiosInstance.interceptors.response.use(
  response => response,
  error => {
    if (error.response && error.response.status === 401) {
      // Handle unauthorized access
      console.log("User Unauthroized")
    }
    return Promise.reject(error);
  }
);

export default axiosInstance;
