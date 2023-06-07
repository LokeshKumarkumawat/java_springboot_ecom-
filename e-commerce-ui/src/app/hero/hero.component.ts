import { Component, OnInit } from '@angular/core';




declare function rev1(): any;
declare function rev2(): any;


@Component({
  selector: 'app-hero',
  templateUrl: './hero.component.html',
  styleUrls: ['./hero.component.css']
})



export class HeroComponent implements OnInit {

  constructor(){

  }

  ngOnInit(): void {

    rev1()
    rev2()
  }







}
