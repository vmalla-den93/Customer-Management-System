import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';  

import { DataService } from '../core/data.service';
import { ICustomer, IOrder, IOrderItem } from '../shared/interfaces';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: [ './orders.component.css' ]
})
export class OrdersComponent implements OnInit {

  orders: IOrder[] = [];
  customer: ICustomer;
 
  isitemadded = false;
  addorderform: FormGroup;

  constructor(private dataService: DataService,
    private route: ActivatedRoute,
   private fb: FormBuilder) { }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('customerId');
    console.log(id)
    this.dataService.getCustomerOrders(id).subscribe((orders: IOrder[]) => {
      this.orders = orders;
      console.log(this.orders)
    });
    this.dataService.getCustomer(id).subscribe((customer: ICustomer) => {
      this.customer = customer;
      console.log(customer)
    });

  }

  


}
