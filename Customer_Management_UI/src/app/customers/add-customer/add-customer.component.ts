import { Component, Input, OnInit } from '@angular/core';
import { Location } from '@angular/common';

import { ICustomer } from '../../shared/interfaces';
import { DataService } from '../../core/data.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';



@Component({
    selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']  

})
export class AddCustomerComponent implements OnInit {
  errorMessage: string;
  customer = {} as ICustomer;
  submitted = false;
  addedCustomerId: number;

  constructor(private dataService: DataService,
    private location: Location) { };
 

  ngOnInit() {

  }

  save() {
    this.dataService.addCustomer(this.customer)
      .subscribe(data => {
        this.customer = data
        this.submitted = true
      },
        error => {
          console.log(error)
          if (error instanceof HttpErrorResponse) {
            if (error.status === 409) {
              this.errorMessage = "EmailId Already Exists"
            }
          }
          else
            this.errorMessage = error;
        });
  }


  onSubmit(customerForm: NgForm) {
    this.save()

    customerForm.resetForm()
    
  }

  newCustomer() {
    this.submitted = false
    this.customer = {} as ICustomer;
  }

  cancel() {
    this.location.back();
  }

}
