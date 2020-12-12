import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MainScreenComponent} from './components/main-screen/main-screen.component';
import {HeaderComponent} from './components/header/header.component';
import {HomeComponent} from './components/home/home.component';
import {PagenotfoundComponent} from './components/pagenotfound/pagenotfound.component';
import {RouterModule} from '@angular/router';
import {ProductService} from './services/product.service';
import { HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    HeaderComponent,
    HomeComponent,
    PagenotfoundComponent,
    MainScreenComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule
  ],
  providers: [ProductService],
})
export class SharedModule {
}
