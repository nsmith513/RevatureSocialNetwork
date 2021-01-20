import IUser from "./IUser";

export default interface IPost {
    id:number, 
    content:string,
    images:any[],
    user:IUser,
    likes: IUser[];


}
