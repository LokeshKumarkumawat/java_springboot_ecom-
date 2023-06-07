import { MyOrderDetails } from './../_model/order.model';
import { ProductService } from './../_services/product.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.css']
})
export class MyOrdersComponent implements OnInit {


  displayedColumns: string[] = ['Name', 'Address','PinCode',  'Contact No', 'Amount' , 'Status'];


  myOrderDetails : MyOrderDetails[] = [];

  ngOnInit(): void {
      this.getOrderDetails();
  }

  constructor(private ProductService: ProductService) {

  }

  getOrderDetails() {
    this.ProductService.getMyOrders().subscribe(
      (resp: MyOrderDetails[]) => {
        console.log(resp);
        this.myOrderDetails = resp;

      },
      (err) => {
        console.log(err);

      }
    )
  }

}
