import { Component, Input, OnInit } from '@angular/core';
import { Location } from '@angular/common';

import { ICustomer } from '../../shared/interfaces';
import { DataService } from '../../core/data.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';



@Component({
    selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrls: ['./edit-customer.component.css']  

})
export class EditCustomerComponent implements OnInit {
  errorMessage: string;
  customer = {} as ICustomer;
  submitted = false;

  constructor(private dataService: DataService,
    private location: Location,
    private route: ActivatedRoute) { };
 

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('customerId');
    this.dataService.getCustomer(id).subscribe((customer: ICustomer) => {
      this.customer = customer;
      
    });
  }

  save() {
    this.dataService.updateCustomer(this.customer)
      .subscribe(data => {
        
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


  onSubmit() {
    
    this.save()
    
  }


  cancel() {
    this.location.back();
  }

}
