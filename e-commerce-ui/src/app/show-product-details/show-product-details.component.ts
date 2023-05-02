import { ImageProcessingService } from './../image-processing.service';
import { ProductService } from './../_services/product.service';
import { Product } from './../_model/product.model';
import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { ShowProductImagesDialogComponent } from '../show-product-images-dialog/show-product-images-dialog.component';
import { map } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-show-product-details',
  templateUrl: './show-product-details.component.html',
  styleUrls: ['./show-product-details.component.css']
})
export class ShowProductDetailsComponent implements OnInit  {


  showLoadButton = false;
  showTable = false;
  pageNumber: number = 0;
  productDetails: Product[] = [];



  displayedColumns: string[] = ['Id', 'Product Name', 'description', 'Product Discounted Price' , 'Product Actual Price' ,'Actions'];
  constructor(private productService: ProductService , public imagesDialog : MatDialog , private imageProcessingService:ImageProcessingService , private router : Router){}

  ngOnInit(): void {
      this.getProductDetails();
  }




  searchByKeyword(searchkeyword){
    console.log(searchkeyword);
    this.pageNumber = 0;
    this.productDetails = [];
    this.getProductDetails(searchkeyword);
  }

  public getProductDetails(searchKeyword:string = ""){
    this.showTable = false;
    this.productService.getAllProducts(this.pageNumber, searchKeyword)
    .pipe(
      map((x: Product[] , i) =>x.map((product:Product) => this.imageProcessingService.createImages(product)))
    )
    .subscribe(
      (resp: Product[]) =>{
        // console.log(resp);
        // this.productDetails = resp;
        resp.forEach(product => this.productDetails.push(product))
        this.showTable = true;

        if(resp.length == 4){
          this.showLoadButton = true;
        }else {
          this.showLoadButton = false;
        }

      },(error : HttpErrorResponse) =>{
        console.log(error);
      }
    );
  }

  deleteProduct(productId){
    this.productService.deleteProduct(productId).subscribe(
      (resp)=>{
        console.log(resp);
        this.getProductDetails();
      },
      (error:HttpErrorResponse) =>{
        console.log(error);
      }
    );

  }

  showImages(product : Product){
    console.log(product);
    this.imagesDialog.open(ShowProductImagesDialogComponent , {
      data:{
        images: product.productImages
      },
      height: '500Px',
      width:'700px'
    });

  }


  editProductDetailes(productId){
    this.router.navigate(['/addNewProduct' , {productId: productId}]);

  }

  loadMoreProduct(){
    this.pageNumber = this.pageNumber + 1;
    this.getProductDetails();
  }
}
