import { ProductService } from './../_services/product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderDetails } from './../_model/order-details.model';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Product } from '../_model/product.model';

@Component({
  selector: 'app-buy-product',
  templateUrl: './buy-product.component.html',
  styleUrls: ['./buy-product.component.css']
})
export class BuyProductComponent implements OnInit {

  isSingleProductCheckout:string = '';
  productDetails: Product[] = [];


  constructor(private activatedRoute: ActivatedRoute, private productService: ProductService , private router : Router) { }

  ngOnInit(): void {


    this.productDetails = this.activatedRoute.snapshot.data['productDetails'];
    this.isSingleProductCheckout = this.activatedRoute.snapshot.paramMap.get("isSingleProductCheckout");

    this.productDetails.forEach(
      x => this.orderDetails.orderProductQuantityList.push(
        { productId: x.productId, quantity: 1 }
      )
    );


    console.log(this.productDetails);
    console.log(this.orderDetails);

  }

  orderDetails: OrderDetails = {
    fullName: '',
    fullAddress: '',
    contactNumber: '',
    alternateContactNumber: '',
    orderProductQuantityList: []
  }


  public placeOrder(orderForm: NgForm) {
    this.productService.placeOrder(this.orderDetails, this.isSingleProductCheckout).subscribe(
      (resp) =>{
        console.log(resp);
        orderForm.reset();
        this.router.navigate(["/orderConfirm"])
      },
      (err)=>{
        console.log(err);

      }
    )
  }



  getQuantityForProduct(productId){
    const filteredProduct = this.orderDetails.orderProductQuantityList.filter(
      (productQuantity) =>productQuantity.productId === productId
    );

    return filteredProduct[0].quantity;

  }


  getCalculatedTotal(productId , productDiscountedPrice ){
   const filteredProduct =  this.orderDetails.orderProductQuantityList.filter(
      (productQuantity) => productQuantity.productId === productId
    );
    return filteredProduct[0].quantity * productDiscountedPrice;
  }

  onQuantityChanged(q , productId){
    this.orderDetails.orderProductQuantityList.filter(
      (orderProduct) => orderProduct.productId === productId
    )[0].quantity = q;
  }


  getCalculatedGrandTotal(){
    let grandTotal = 0;
    this.orderDetails.orderProductQuantityList.forEach(
      (productQuantity) =>{
        const price = this.productDetails.filter(product => product.productId === productQuantity.productId)[0].productDiscountedPrice;

        grandTotal = grandTotal + price * productQuantity.quantity;
      }
    );

    return grandTotal;
  }

  createTransactionAndplaceOrder(orderForm:NgForm){
    let amount = this.getCalculatedGrandTotal();
    this.productService.createTransaction(amount).subscribe(
      (resp)=>{
        console.log(resp);
        
      },(err)=>{
        console.log(err);
        
      }
    )

  }

}
