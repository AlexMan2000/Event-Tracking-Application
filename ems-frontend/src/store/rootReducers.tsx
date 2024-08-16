import globalReducer from "./slice/globalSlice/globalSlice"
import userReducer from "./slice/userSlice/userSlice"
import authReducer from "./slice/authSlice/authSlice"
import chatReducer from "./slice/chatSlice/chatSlice"

export const reducer = {
    global: globalReducer,
    user: userReducer,
    auth: authReducer,
    chat: chatReducer
};