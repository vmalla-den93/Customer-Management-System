import { Component, Input, OnInit } from '@angular/core';

import { ICustomer } from '../../shared/interfaces';
import { SorterService } from '../../core/sorter.service';
import { DataService } from '../../core/data.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-customers-list',
    templateUrl: './customers-list.component.html'
})
export class CustomersListComponent implements OnInit {
    private _customers: ICustomer[] = [];
    @Input() get customers(): ICustomer[] {
        return this._customers;
    }
    set customers(value: ICustomer[]) {
        if (value) {
            this.filteredCustomers = this._customers = value;
            this.calculateOrders();
        }
    }
    filteredCustomers: ICustomer[] = [];
    customersOrderTotal: number;
  currencyCode = 'USD';
  isupdated = false;


  constructor(private sorterService: SorterService,
    private dataService: DataService,
    private _router: Router) { }

    ngOnInit() {

    }

    calculateOrders() {
        this.customersOrderTotal = 0;
        this.filteredCustomers.forEach((cust: ICustomer) => {
            this.customersOrderTotal += cust.orderTotal;
        });
    }

    filter(data: string) {
        if (data) {
            this.filteredCustomers = this.customers.filter((cust: ICustomer) => {
                return cust.name.toLowerCase().indexOf(data.toLowerCase()) > -1 ||
                       cust.city.toLowerCase().indexOf(data.toLowerCase()) > -1 ||
                       cust.orderTotal.toString().indexOf(data) > -1;
            });
        } else {
            this.filteredCustomers = this.customers;
        }
        this.calculateOrders();
    }

    sort(prop: string) {
        this.sorterService.sort(this.filteredCustomers, prop);
    }

    customerTrackBy(index: number, customer: ICustomer) {
        return customer.customerId;
  }

  reloadData() {
    this.dataService.getCustomers().subscribe(data => {
      this.filteredCustomers = <ICustomer[]>data
      this.calculateOrders();
    });
    
  }
   
  deleteCustomer(customerId: number) {
    this.dataService.deleteCustomer(customerId)
      .subscribe(
        data => {
          this.reloadData()
          
        },
        error => console.log(error));
  }


}
