import { Component, OnInit } from '@angular/core';
import { Location, DatePipe, formatDate } from '@angular/common';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';  

import { DataService } from '../../core/data.service';
import { ICustomer, IOrder, IOrderItem } from '../../shared/interfaces';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-add-order',
  templateUrl: './add-order.component.html',
  styleUrls: [ './add-order.component.css' ]
})
export class AddOrderComponent implements OnInit {

  customerOrder= {} as IOrder;
  customer: ICustomer;
  items: IOrderItem[] = [];
  isordersubmitted = false;
  addorderform: FormGroup;
  orderDate: Date = new Date();

 

  constructor(private dataService: DataService,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private location: Location) { }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('customerId');
    console.log(id)
    console.log(this.orderDate)

    this.dataService.getCustomer(id).subscribe((customer: ICustomer) => {
      this.customer = customer;
      console.log(customer)
    });

    this.dataService.getItems().subscribe((items: IOrderItem[]) => {
      this.items = items;
      console.log(this.items)
    });

    this.addorderform = this.fb.group({
      orderItems: this.fb.array([
        this.getItem()
      ]),
      orderDate: [this.orderDate, [Validators.required]]
    });


  }

  getItem() {
    return this.fb.group({
      itemId: [0],
      itemName: ['', Validators.required],
      itemCost: [{value:0, disabled:true}]
    });
  }

  addItem() {
    const control = <FormArray>this.addorderform.controls['orderItems'];
    control.push(this.getItem());
  }

  removeItem(i: number) {
    const control = <FormArray>this.addorderform.controls['orderItems'];
    control.removeAt(i);
  }
  submitOrder() {
    
    const customerItems = <IOrderItem[]>this.addorderform.controls['orderItems'].value
    this.customerOrder.customerId = this.customer.customerId
    this.customerOrder.orderDate = this.addorderform.controls['orderDate'].value
    this.customerOrder.orderItems = customerItems;
    this.saveOrder()  
    
  }

  saveOrder() {
    console.log("On Save Order")
    console.log(this.customerOrder)
    this.dataService.addCustomerOrder(this.customerOrder).subscribe(data => {
      console.log(data)
      this.isordersubmitted = true
    },
      error => {
        console.log(error)
      });

  }

  close() {
    this.location.back();
  }

  onItemSelect(i: number) {
    const control = <FormArray>this.addorderform.controls['orderItems'];
    const selectedItem = <IOrderItem>this.searchForItemIdbyname(control.at(i).value.itemName)[0];
    control.at(i).value.itemCost = selectedItem.itemCost;
    control.at(i).value.itemId = selectedItem.itemId;
    console.log(control.value);
    

  }

  searchForItemIdbyname(itemName: string) {
    return this.items.filter(x => x.itemName === itemName);

  }

}
