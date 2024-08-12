import globalReducer from "./slice/globalSlice/globalSlice"
import userReducer from "./slice/userSlice/userSlice"
import authReducer from "./slice/authSlice/authSlice"

export const reducer = {
    global: globalReducer,
    user: userReducer,
    auth: authReducer
};