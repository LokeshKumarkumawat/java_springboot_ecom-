import { ProductService } from './../_services/product.service';
import { NgForm } from '@angular/forms';
import { Product } from './../_model/product.model';
import { Component } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-add-new-product',
  templateUrl: './add-new-product.component.html',
  styleUrls: ['./add-new-product.component.css']
})
export class AddNewProductComponent {
  product: Product={
    productName:"",
    productDescription:"",
    productDiscountedPrice:0,
    productActualPrice:0,
  }

  constructor(private productService: ProductService){}

  addProduct(productForm:NgForm){
    this.productService.addProduct(this.product).subscribe(
      (response:Product)=>{
        console.log(response);

      },
      (error:HttpErrorResponse)=>{
        console.log(error);
      }

    );

  }
}
