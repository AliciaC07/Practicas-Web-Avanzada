import  Role  from "./role";

export class User {
       id?: number;
       username?: string;
       password?: string;
       name?: string;
       lastName?: string;
       email?: string;
       role!: Role;

}