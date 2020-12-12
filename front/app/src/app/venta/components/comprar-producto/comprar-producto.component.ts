import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../../shared/services/product.service';
import { ProductDTO } from '../../../shared/models/product-dto';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-comprar-producto',
  templateUrl: './comprar-producto.component.html',
  styleUrls: ['./comprar-producto.component.css']
})
export class ComprarProductoComponent implements OnInit {

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

  sellProduct(product: ProductDTO) {
    product.quantity--;
    this.productService.updateProduct(product.id, product).subscribe(
      response => {
        Swal.fire({
          icon: 'success',
          title: 'Product sell'
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
