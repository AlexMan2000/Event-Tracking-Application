// features/auth/authSlice.js
import { createSlice } from '@reduxjs/toolkit';
import { UserState } from '../userSlice/userSlice';
import { RootState } from '@/store/store';

export interface AuthState {
    isAuthenticated: boolean | false,
    user: UserState | null,
    token: string | null,
    // Add other user-related fields here if needed
  }


const initialState = {
  isAuthenticated: false,
  user: null,
  token: null,
};



const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    login: (state, action) => {
      state.isAuthenticated = true;
      state.user = action.payload.user;
      state.token = action.payload.token;
    },
    logout: (state) => {
      state.isAuthenticated = false;
      state.user = null;
      state.token = null;
    },
  },
});

// login and logout are action creators, when called, they will return an action object
// that can be passed to the dispatch function.
export const { login, logout } = authSlice.actions;

export const selectAuthentication = (state: RootState) => state.auth.isAuthenticated;

// Reducer object, used to configure store
export default authSlice.reducer;
