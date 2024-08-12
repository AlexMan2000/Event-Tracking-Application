export const encodeToBase64 = (input: any): string => {
    if (input === null || input === undefined) {
        throw new Error("Cannot encode empty object");
    }
    return btoa(input.toString());
};
  
export const decodeFromBase64 = (input: any): string => {
    if (input === null || input === undefined) {
        throw new Error("Cannot encode empty object");
    }
    return atob(input.toString());
};
