import Header from "../../create-mockups/models/Header";
import { User } from "../../login/model/user";

export default class Mock{
    id!: number;
    nameMock!: string;
    mockId!: string;
    endpoint!: string;
    description!: string;
    method!: string;
    contentType!: string;
    charset!: string;
    httpStatus!: string;
    bodyMessage!: string;
    delayResponse!: number;
    expiration!: number;
    expirationDate!: Date;
    jwtValidation!: string;
    jwtValidationActive: boolean = false;
    headers: Header[] = [];
    user!: number;
    
}