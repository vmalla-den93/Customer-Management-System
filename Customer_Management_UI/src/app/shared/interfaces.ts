export interface ICustomer {
    customerId: number;
    name: string;
    city: string;
    streetAddress: string;
    state: string;
    zipCode: string;
    emailId: string;
    orderTotal?: number;
}

export interface IOrder {
  customerId: number;
  orderDate: Date;
    orderItems: IOrderItem[];
}

export interface IOrderItem {
    itemId: number;
    itemName: string;
    itemCost: number;
}
