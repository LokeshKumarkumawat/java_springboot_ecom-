import { UserService } from './../_services/user.service';
import { Router } from '@angular/router';
import { ImageProcessingService } from './../image-processing.service';
import { count, map } from 'rxjs';
import { ProductService } from './../_services/product.service';
import { Component, OnInit } from '@angular/core';
import { Product } from '../_model/product.model';
import { HttpErrorResponse } from '@angular/common/http';



declare var $: any;
declare function countdown(): any;
declare function getToday(): any;




@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  pageNumber:number = 0;
  productDetails = [];

  showLoadButton = false;

  constructor(


    private userService:UserService,private productService:ProductService , private  imageProcessingService:ImageProcessingService , private router:Router){

    }

  ngOnInit(): void {
    this.getProductDetails();
    countdown();

  }



  public getProductDetails(searchKey : string = ""){
    this.productService.getAllProducts(this.pageNumber ,searchKey)
    .pipe(
      map((x: Product[] , i) =>x.map((product:Product) => this.imageProcessingService.createImages(product)))
    )
    .subscribe(
      (resp: Product[]) =>{
        console.log(resp);

        if(resp.length == 4){
          this.showLoadButton = true;
        }else {
          this.showLoadButton = false;
        }

        resp.forEach(p=>this.productDetails.push(p));
        // this.productDetails = resp;
      },(error : HttpErrorResponse) =>{
        console.log(error);
      }
    );
  }


  showProductDetails(productId){
    this.router.navigate(['/productViewDetails', {productId: productId}])

  }

  loadMoreProduct(){
    this.pageNumber = this.pageNumber +1;
    this.getProductDetails();
  }

  searchByKeyword(searchkeyword){
    console.log(searchkeyword);
    this.pageNumber = 0;
    this.productDetails = [];

    this.getProductDetails(searchkeyword);

  }


}
