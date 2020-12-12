import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../../shared/services/product.service';
import { ProductDTO } from '../../../shared/models/product-dto';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  productList: ProductDTO[];

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.cargarProductos();
  }

  cargarProductos() {
    this.productService.getProducts().subscribe(
      response => {
        this.productList = response;
      }, error => {
        Swal.fire({
          icon: 'error',
          title: 'An error has occurred...',
          text: error.error
        });
      }
    );
  }

  deleteProduct(product: ProductDTO) {
    this.productService.deleteProduct(product.id).subscribe(
      response => {
        Swal.fire({
          icon: 'success',
          title: 'Product deleted'
        });
        this.cargarProductos();
      }, error => {
        this.cargarProductos();
        Swal.fire({
          icon: 'error',
          title: 'An error has occurred...',
          text: error.error
        });
      }
    );
  }
}
