import { ImageProcessingService } from './../image-processing.service';
import { ProductService } from './../_services/product.service';
import { UserService } from './../_services/user.service';
import { Router } from '@angular/router';
import { UserAuthService } from './../_services/user-auth.service';
import { Component, OnInit } from '@angular/core';
import { Product } from '../_model/product.model';
import { map } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  pageNumber:number = 0;
  productDetails = [];
  showLoadButton = false;


  ngOnInit(): void {
  
  }

  constructor(
    private userAuthService:UserAuthService,
    private router:Router,
    public userService: UserService,
    private productService:ProductService,
    private imageProcessingService:ImageProcessingService
  ){ }

  public isLoggedIn(){
    return this.userAuthService.isLoggedIn();
  }

  public logout(){
    this.userAuthService.clear();
    this.router.navigate(['/']);
  }

  public isAdmin(){
    return this.userAuthService.isAdmin();
  }

  public isUser(){
    return this.userAuthService.isUser();
  }


}
