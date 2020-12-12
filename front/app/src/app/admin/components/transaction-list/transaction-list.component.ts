import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../../shared/services/product.service';
import { TransactionDTO } from '../../../shared/models/transaction-dto';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-transaction-list',
  templateUrl: './transaction-list.component.html',
  styleUrls: ['./transaction-list.component.css']
})
export class TransactionListComponent implements OnInit {

  transactionList: TransactionDTO[];

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.cargarTransactions();
  }

  cargarTransactions() {
    this.productService.getTransactions().subscribe(
      response => {
        this.transactionList = response;
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
