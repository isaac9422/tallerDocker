import { ProductDTO } from './product-dto';

export class TransactionDTO {
  id: string;
  dateCreated: Date;
  productDTO: ProductDTO;
  valueTransaction: number;
  type: boolean;
  quantity: number;
}
