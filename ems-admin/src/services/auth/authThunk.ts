import { AppDispatch, RootState } from '../../store/store';
import { login, logout, AuthState } from '../../store/slice/authSlice';  // Action creators
import { decodeToken } from '../../utils/decodeToken';
import { useSelector  } from 'react-redux';
import { authenticateService, loginService, registerService } from './authService';
import { LoginCredentials, RegisterCredentials, AuthenticateCredentials } from './authService';
import { setUserInfo, clearUserInfo, UserState } from "../../store/slice/userSlice";

export const handleLogin = (credentials: LoginCredentials) => {
    // Async Actions syntax
    return async (dispatch: AppDispatch) => {
        try {
          const { token, userInfo } = await loginService(credentials);

          console.log("login", token, userInfo);
    
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


export const handleRegister = () => {

}


export const handleLogout = () => {
  
}

// check authentication status
export const checkAuthStatus = (credentials: AuthenticateCredentials) => {
  return async (dispatch: AppDispatch) => {
    const token = localStorage.getItem('token');
    // console.log(user)
    if (token) {
      const { token, userInfo } = await authenticateService(credentials);

      console.log("authenticate", token, userInfo);

      dispatch(login({token, user: userInfo}));
    }
  };
};