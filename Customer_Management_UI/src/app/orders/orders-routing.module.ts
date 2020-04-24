import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { OrdersComponent } from '../orders/orders.component';
import { AddOrderComponent } from '../orders/add-order/add-order.component';

const routes: Routes = [
  { path: 'orders/:customerId', component: OrdersComponent },
  { path: 'addorder/:customerId', component: AddOrderComponent }
];

@NgModule({
    imports: [ RouterModule.forChild(routes) ],
    exports: [ RouterModule ]
})
export class OrdersRoutingModule {

}
