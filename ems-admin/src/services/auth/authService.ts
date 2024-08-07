import axiosInstance from "../../apis/authInstance";

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


export const loginService = async (credentials: LoginCredentials): Promise<LoginResponse> => {
    const response = await axiosInstance.post<LoginResponse>("/auth/login", credentials);
    return response.data;
};


export const authenticateService = async (credentials: AuthenticateCredentials): Promise<AuthenticateResponse> => {
    const response = await axiosInstance.post<AuthenticateResponse>("/auth/authenticate", credentials);
    return response.data;
};


export const registerService = async (credentials: RegisterCredentials): Promise<RegisterResponse> => {
    const response = await axiosInstance.post<RegisterResponse>("/auth/register", credentials);
    return response.data;
};


export const validationService = async (credentials: ValidationCredentials): Promise<ValidationResponse> => {
    const response = await axiosInstance.post<ValidationResponse>("/auth/check-email-validity", credentials);
    return response.data;
}
