import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { ProductDetailComponent } from './components/product-detail/product-detail.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { TransactionListComponent } from './components/transaction-list/transaction-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'admin', pathMatch: 'full' },
  { path: 'createProduct', component: ProductDetailComponent },
  { path: 'createProduct/:productId', component: ProductDetailComponent },
  { path: 'getProducts', component: ProductListComponent },
  { path: 'getTransactions', component: TransactionListComponent },
];

@NgModule({
  declarations: [ProductDetailComponent, ProductListComponent, TransactionListComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes)
  ]
})
export class AdminModule { }
