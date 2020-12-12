import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComprarProductoComponent } from './components/comprar-producto/comprar-producto.component';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule} from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

const routes: Routes = [
  { path: '', redirectTo: 'venta', pathMatch: 'full' },
  { path: 'comprarProducto', component: ComprarProductoComponent },
];

@NgModule({
  declarations: [ComprarProductoComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes)
  ]
})
export class VentaModule { }
