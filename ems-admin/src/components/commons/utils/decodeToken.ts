// utils/decodeToken.ts
import { jwtDecode } from 'jwt-decode';

interface DecodedToken {
  sub: string;
  iat: number;
  exp: number;
  jti: string;
  // Add any other fields your token contains
}

export const decodeToken = (token: string): DecodedToken => {
  return jwtDecode<DecodedToken>(token);
};
