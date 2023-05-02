import { ProductService } from './../_services/product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Product } from '../_model/product.model';

@Component({
  selector: 'app-product-view-details',
  templateUrl: './product-view-details.component.html',
  styleUrls: ['./product-view-details.component.css']
})
export class ProductViewDetailsComponent implements OnInit {

  product : Product;
  selectedProductIndex = 0;

  constructor(private activatedRoute:ActivatedRoute , private router :Router , private productService:ProductService) { }

  ngOnInit(): void {
    this.product = this.activatedRoute.snapshot.data['product'];
    console.log(this.product);

  }

  changeIndex(index){
    this.selectedProductIndex = index;
  }

  buyProduct(productId){
    this.router.navigate(['/buyProduct',
    {
      isSingleProductCheckout:true,
      id:productId
    }
  ]);
  }

  addToCart(productId){
    console.log("clicked");
    
    this.productService.addToCart(productId).subscribe(
      (response) =>{
        console.log(response);
      },(error)=>{
        console.log(error);
      }
    );
  }

}
