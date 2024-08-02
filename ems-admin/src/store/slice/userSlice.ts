// features/user/userSlice.ts
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { RootState } from '../store';
import { AuthState } from './authSlice';


export interface UserState {
  email: string | null;
  firstName: string | null;
  lastName: string | null;
  middleName: string | null;
  mobile: string | null;
  intro: string | null;
  profile: any | null;
}


const initialState: UserState = {
  email: "",
  firstName: "",
  lastName: "",
  middleName: "",
  mobile: "",
  intro: "",
  profile: "",
};

export const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    setUserInfo: (state, action: PayloadAction<UserState>) => {
      state.email = action.payload.email;
      state.firstName = action.payload.firstName;
      state.middleName = action.payload.middleName;
      state.lastName = action.payload.lastName;
      state.mobile = state.mobile;
      state.intro = state.intro;
      state.profile = state.profile;
    },
    clearUserInfo: (state) => {
      state.email = null;
      state.firstName = null;
      state.middleName = null;
      state.lastName = null;
      state.mobile = null;
      state.intro = null;
      state.profile = null;
    },
  },
});

export const { setUserInfo, clearUserInfo } = userSlice.actions;

export const selectUser = (state: RootState) => state.auth.user;

export default userSlice.reducer;
