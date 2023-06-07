import { OrderQuantity } from "./order-quantity.model";

export interface OrderDetails{
  firstName:string;
  lastName: string;
  fullName: string;
  fullAddress: string;
  cityTown:string;
  postCode: string;
  emailAddress:string;
  contactNumber: string;
  alternateContactNumber: string;
  orderMessage:string;
  orderProductQuantityList : OrderQuantity[];

}
