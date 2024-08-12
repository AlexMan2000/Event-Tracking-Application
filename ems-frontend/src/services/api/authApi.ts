import {SERVER_BASE} from "../api/commonApi"
import { authInterceptors } from "../config/interceptorHooks"
import Request from "../config/Request"
import { AppDispatch, RootState } from '../../store/store';
import { login, logout } from '@/store/slice/authSlice/authSlice';  // Action creators


export const authRequest = new Request({
    baseURL: SERVER_BASE,
    interceptorHooks: authInterceptors
})



export interface LoginCredentials {
    email: string;
    password: string;
}


export interface RegisterCredentials {
    email: string;
    password: string;
    firstName: string;
    middleName: string;
    lastName: string;
    mobile: string;
    intro: string;
    profile: string;
    profileImageId: string;
}

export interface AuthenticateCredentials {
    token: string;
}



export interface LoginResponse {
    token: string;
    userInfo: any;
}



export interface RegisterResponse {
}


export interface AuthenticateResponse {
    token: string;
    userInfo: any;
}


export interface ValidationCredentials {
    email: string;
}

export interface ValidationResponse {
    okToAdd: boolean;
}




export const handleLogin = (credentials: LoginCredentials) => {
    // Async Actions syntax
    return async (dispatch: AppDispatch) => {
        try {
          const { token, userInfo } = await authRequest.post<LoginResponse>("/auth/login", credentials);
          dispatch(login({ token, user: userInfo }));
          localStorage.setItem('token', token); // Save token to localStorage
          return Promise.resolve({ status: 200});
        } catch (error) {
          console.error('Login failed', error);
          // Handle error (e.g., show error message to user)
          return Promise.reject(error);
        }
      };
};


export const handleRegister = (credentials: RegisterCredentials) => {
  return async () => {
    await authRequest.post<RegisterResponse>("/auth/register", credentials);
  }
}



export const handleLogout = () => {
  return async (dispatch: AppDispatch) => {
    localStorage.clear();
    dispatch(logout());
  };
}

// check authentication status
export const checkAuthStatus = (credentials: AuthenticateCredentials) => {
  return async (dispatch: AppDispatch) => {
    const token = localStorage.getItem('token');
    if (token) {
      const { token, userInfo } = await authRequest.post<AuthenticateResponse>("/auth/authenticate", credentials);
      console.log("authenticate", token, userInfo);
      dispatch(login({token, user: userInfo}));
    }
  };
};
