import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { SharedModule } from '../shared/shared.module';
import { CustomersListComponent } from './customers-list/customers-list.component';
import { AddCustomerComponent } from './add-customer/add-customer.component';
import { EditCustomerComponent } from './edit-customer/edit-customer.component';
import { FilterTextboxComponent } from './customers-list/filter-textbox.component';
import { CustomersComponent } from './customers.component';
import { CustomersRoutingModule } from './customers-routing.module';

@NgModule({
    imports: [ CommonModule, FormsModule, SharedModule, CustomersRoutingModule ],
  declarations: [CustomersListComponent, FilterTextboxComponent, AddCustomerComponent,
                    EditCustomerComponent, CustomersComponent]
})
export class CustomersModule { }
