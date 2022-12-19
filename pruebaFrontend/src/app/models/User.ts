export interface User{
    id?:number;
    username: string;
    email: string;
    password: string;
    rol_id?: number;
    token?: string;
}