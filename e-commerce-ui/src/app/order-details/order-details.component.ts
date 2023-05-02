import { ProductService } from './../_services/product.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  ngOnInit(): void {
      
    this.getAllOrderDetailsForAdmin(this.status);
  }


  displayedColumns: string[] = ['Id' , 'Product Name','Name' ,  'Address', 'Contact No', 'Status' , 'Actions'];
  dataSource = [];

  status:string = 'All';

  constructor(private productService : ProductService) {
    
  }

  getAllOrderDetailsForAdmin(status:string){
    this.productService.getAllOrderDetailsForAdmin(status).subscribe(
      (resp) =>{
        console.log(resp);
        this.dataSource = resp;
      },
      (err)=>{
        console.log(err);
        
      }
    );
  }

  markAsDelivered(orderId){
    console.log(orderId);
    this.productService.markAsDelivered(orderId).subscribe(
      (response) => {
        this.getAllOrderDetailsForAdmin(this.status);
      },
      (err)=>{
        console.log(err);
        
      }
    )
  }


}
