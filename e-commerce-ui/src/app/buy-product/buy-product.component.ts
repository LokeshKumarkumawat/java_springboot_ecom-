import { ProductService } from './../_services/product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderDetails } from './../_model/order-details.model';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Product } from '../_model/product.model';
// import * as Razorpay from 'razorpay';


declare var Razorpay: any;
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
    firstName:'',
    lastName: '',
    fullName: '',
    fullAddress: '',
    cityTown:'',
    postCode:'',
    emailAddress:'',
    contactNumber: '',
    alternateContactNumber: '',
    orderMessage:'',
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
      (response)=>{
        console.log(response);
        this.openTransactionModal(response);

      },(err)=>{
        console.log("GETTING ERROR");

        console.log(err);


      }
    )

  }


  onPaymentHandler(response) {
    console.log("payment response", response);
    if (response.razorpay_payment_id) {
      // Payment successful
    } else {
      console.log("ERROR");
    }
  }


  openTransactionModal(response:any){
    console.log("responce Option => " , response);


    var options= {

      order_id: response.orderId,
      key: 'rzp_test_6EZOz6rfKiINoc',
      amount: response.amount,
      currency: response.currency,
      name:'Lokesh FROM NAME',
      description: 'Payment of online shopping',
      image:'https://cdn.pixabay.com/photo/2023/04/04/00/51/sunset-7898136_640.jpg',
      handler:(response:any) =>{

        console.log("llllllllllllllllllllkkkkkkkkkkkkk");

        this.processResponse(response);

        alert(response.Razorpay_payment_id);

        alert(response.Razorpay_order_id);

        alert(response.Razorpay_signature);
        alert(response);

      },
      prefill : {
        name:'LPY',
        email:'gaurav.kumar@example.com',
        contact:'9090909099'
      },
      notes:{
        address:'Online Shopping'
      },
      theme:{
        color:'#F37254'
      }
    };




    var razorPayObject =  new Razorpay(options);

    console.log("RRRR",razorPayObject);

    razorPayObject.open();
    console.log("RRARR",razorPayObject);
  }

  processResponse(resp:any){
    console.log("RR=RR",resp);
  }

}
