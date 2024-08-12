import { createSlice } from "@reduxjs/toolkit";
import { RootState } from "../../store";



interface GlobalState {
    sessionId: string,
    locale: string
}


const getInitialLocale = () => {
    const savedLocale = localStorage.getItem('locale')
   // 默认英文
    return savedLocale ? savedLocale : "en";
}


const initialState: GlobalState = {
    sessionId: '',
    locale: getInitialLocale(),
  }


export const globalSlice = createSlice({
    name: "global",
    initialState,
    reducers: {
        setSessionId: (state, action) => {
            state.sessionId = action.payload.sessionId;
        },
        setLocale: (state, action) => {
            state.locale = action.payload.locale;
            localStorage.setItem("locale", state.locale);
        }
    }
})

  
export const selectGlobalState = (state: RootState) => state.global

export const { setSessionId, setLocale } = globalSlice.actions

export default globalSlice.reducer
