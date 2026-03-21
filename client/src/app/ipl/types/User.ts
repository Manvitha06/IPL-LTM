
export class User {
    [x: string]: any;
    userId: number;
    fullName:string;
    userName:string;
    password:string;
    email:string;
    role:string;
    constructor(userId:number,fullName:string,userName:string,password:string,email:string,role:string){
        this.userId=userId;
        this.fullName=fullName;
        this.userName=userName;
        this.password=password;
        this.email=email;
        this.role=role;
    }
    displayInfo(): void{
        console.log(`User ID: ${this.userId}, Full Name: ${this.fullName}, User Name: ${this.userName}, Password: ${this.password}, Email: ${this.email}, Role: ${this.role}`);
    }
}