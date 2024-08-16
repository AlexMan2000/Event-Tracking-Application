import { RootState } from "@/store/store";
import { createSlice, PayloadAction } from "@reduxjs/toolkit";



export interface ChatSlice {
    pagesStatus: string


}


const initialState = {
    pageStatus: "chat"
}


export const chatSlice = createSlice({
    name: "chat",
    initialState,
    reducers: {
        switchMenu: (state, action: PayloadAction<string>) => {
            state.pageStatus = action.payload;
        }
    }
})


export const selectChat = (state: RootState) => state.chat;

export const { switchMenu }  = chatSlice.actions; 

export default chatSlice.reducer;