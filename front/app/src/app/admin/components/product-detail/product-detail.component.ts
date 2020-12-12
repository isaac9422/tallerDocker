import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ProductService } from '../../../shared/services/product.service';
import { ProductDTO } from '../../../shared/models/product-dto';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  productForm: FormGroup;
  productSave: ProductDTO;
  isEdit = false;
  productId: string;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService
  ) { }

  ngOnInit() {
    this.buildForm();
    this.route.params.subscribe(param => {
      if (param.productId) {
        this.productId = param.productId;
        this.isEdit = true;
        this.getProductById(param.productId);
      }
    });
  }

  buildForm() {
    this.productForm = new FormGroup({
      name: new FormControl(this.productSave ? this.productSave.name : '', [Validators.required, Validators.minLength(3)]),
      value: new FormControl(this.productSave ? this.productSave.value : 0, [Validators.required, Validators.min(1)]),
      quantity: new FormControl(this.productSave ? this.productSave.quantity : 0, [Validators.required, Validators.min(1)])
    });
  }

  getProductById(productId: string) {
    this.productService.getProductById(productId).subscribe(
      response => {
        this.productSave = response;
        this.buildForm();
      }, error => {
        Swal.fire({
          icon: 'error',
          title: 'An error has occurred...',
          text: error.error
        });
      }
    );
  }

  saveProduct() {
    const product: ProductDTO = this.productForm.getRawValue();
    this.productService.saveProduct(product).subscribe(
      response => {
        Swal.fire({
          icon: 'success',
          title: 'Product saved'
        });
        this.productForm.reset();
      }, error => {
        Swal.fire({
          icon: 'error',
          title: 'An error has occurred...',
          text: error.error
        });
      }
    );
  }

  updateProduct() {
    const product: ProductDTO = this.productForm.getRawValue();
    product.id = this.productId;
    this.productService.updateProduct(this.productId, product).subscribe(
      response => {
        Swal.fire({
          icon: 'success',
          title: 'Product updated'
        });
        this.getProductById(this.productId);
      }, error => {
        Swal.fire({
          icon: 'error',
          title: 'An error has occurred...',
          text: error.error
        });
      }
    );
  }
}
