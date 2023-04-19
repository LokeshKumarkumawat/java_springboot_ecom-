import { Router } from '@angular/router';
import { UserAuthService } from './../_services/user-auth.service';
import { UserService } from './../_services/user.service';
import { Component } from '@angular/core';
import {NgForm} from '@angular/forms';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private userService: UserService ,
    private userAuthService:UserAuthService,
    private router:Router
    ) { }

  login(loginForm:NgForm){
    this.userService.login(loginForm.value).subscribe(
      (response:any)=>{
        this.userAuthService.setRoles(response.user.role);
        this.userAuthService.setToken(response.jwtToken);

        console.log(response);
        

        const role = response.user.role[0].roleName;
        console.log("ROLE : ",role);
        
        if(role === 'Admin'){
          this.router.navigate(['/admin']);
        }else{
          this.router.navigate(['/user']);
        }

      },
      (error)=>{
        console.log(error);
      }
    );
  }

}
