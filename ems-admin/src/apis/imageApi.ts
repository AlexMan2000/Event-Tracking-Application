
export const SERVER_BASE:string = "http://localhost:8083"

export const getUploadImageUrl = () => {
    return `${SERVER_BASE}/uploads/upload-temp-image`
}



export const getProfileImageUrl = (imageId: string) => {
    return `${SERVER_BASE}/images/${imageId}/download`
}
