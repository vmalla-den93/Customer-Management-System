import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { SharedModule } from '../shared/shared.module';
import { OrdersComponent } from './orders.component';
import { AddOrderComponent } from './add-order/add-order.component';
import { OrdersRoutingModule } from './orders-routing.module';

@NgModule({
  imports: [CommonModule, FormsModule,ReactiveFormsModule ,SharedModule, OrdersRoutingModule ],
  declarations: [OrdersComponent, AddOrderComponent ]
})
export class OrdersModule { }
