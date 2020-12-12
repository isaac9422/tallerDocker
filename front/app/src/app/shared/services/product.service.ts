import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { ProductDTO } from '../models/product-dto';
import { TransactionDTO } from '../models/transaction-dto';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  basePath = `${environment.api}products`;

  constructor(private httpClient: HttpClient) { }

  getProducts(): Observable<any> {
    return this.httpClient.get<ProductDTO[]>(`${this.basePath}/`);
  }

  getProductById(productId: string): Observable<any> {
    return this.httpClient.get<ProductDTO>(`${this.basePath}/${productId}`);
  }

  saveProduct(productSave: ProductDTO): Observable<any> {
    return this.httpClient.post<ProductDTO>(`${this.basePath}/`, productSave);
  }

  updateProduct(productId: string, productSave: ProductDTO): Observable<any> {
    return this.httpClient.put<ProductDTO>(`${this.basePath}/${productId}`, productSave);
  }

  deleteProduct(productId: string): Observable<any> {
    return this.httpClient.delete<any>(`${this.basePath}/${productId}`);
  }

  getTransactions(): Observable<any> {
    return this.httpClient.get<TransactionDTO[]>(`${environment.api}transactions/`);
  }
}
