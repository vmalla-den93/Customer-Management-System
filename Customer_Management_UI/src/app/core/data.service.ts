import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

import { ICustomer, IOrder, IOrderItem } from '../../app/shared/interfaces';

@Injectable()
export class DataService {
   
    // Use the following properties if running the app stand-alone with no external dependencies
    //customersUrl = 'assets/customers.json';
  allcustomersUrl = 'http://localhost:8080/cms/getCustomers';
  customerUrl = 'http://localhost:8080/cms/getCustomer';
   // ordersUrl = 'assets/orders.json';
  customerOrdersUrl = 'http://localhost:8080/cms/getCustomerOrders';
  itemsUrl = 'http://localhost:8080/cms/getItems';
  addCustomerUrl = 'http://localhost:8080/cms/addCustomer';
  deleteCustomerUrl = 'http://localhost:8080/cms/deleteCustomer';
  updateCustomerUrl = 'http://localhost:8080/cms/updateCustomer';
  addCustomerOrderUrl = 'http://localhost:8080/cms/addCustomerOrder';
  
    constructor(private http: HttpClient) { }

    getCustomers(): Observable<ICustomer[]> {
      return this.http.get<ICustomer[]>(this.allcustomersUrl)
        .pipe(
          catchError(this.handleError)
        );

    }

    getCustomer(customerId: number): Observable<ICustomer> {
      return this.http.get<ICustomer>(`${this.customerUrl}/${customerId}`)
        .pipe(
          catchError(this.handleError)
        );
  }

  getCustomerOrders(customerId: number): Observable<IOrder[]> {
    return this.http.get<IOrder[]>(`${this.customerOrdersUrl}/${customerId}`)
      .pipe(
        catchError(this.handleError)
      );
  }

   addCustomer(customer: ICustomer): Observable<ICustomer> {
    return this.http.post<ICustomer>(this.addCustomerUrl, customer)
      .pipe(
        catchError(this.handleError)
      );;
  }

  updateCustomer(customer: ICustomer): Observable<ICustomer> {
    return this.http.post<ICustomer>(this.updateCustomerUrl, customer)
      .pipe(
        catchError(this.handleError)
      );;
  }

  deleteCustomer(customerId: number): Observable<any> {
    return this.http.delete(`${this.deleteCustomerUrl}/${customerId}`)
      .pipe(
        catchError(this.handleError)
      );;
  }

  getItems(): Observable<IOrderItem[]> {
    return this.http.get<IOrderItem[]>(this.itemsUrl)
      .pipe(
        catchError(this.handleError)
      );

  }

  addCustomerOrder(customerOrder: IOrder): Observable<IOrder> {
    return this.http.post<IOrder>(this.addCustomerOrderUrl, customerOrder)
      .pipe(
        catchError(this.handleError)
      );;
  }

    private handleError(error: any) {
      console.error('server error:', error);
      if (error.error instanceof Error) {
          const errMessage = error.error.message;
          return Observable.throw(errMessage);
      }
      throw(error || 'Server error');
    }

}
