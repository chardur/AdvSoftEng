import { Component, OnInit } from '@angular/core';
import * as introJs from 'intro.js/intro.js';

@Component({
  selector: 'app-buyerguide',
  templateUrl: './buyerguide.component.html',
  styleUrls: ['./buyerguide.component.css']
})
export class BuyerguideComponent implements OnInit {

  intro = introJs();

  constructor() { }

  ngOnInit() {
  }

  startIntro(){
    let options = {

      steps: [
        {
          element: document.querySelector('#sidebarToggle'),
          intro: "Click the Menu icon to open the side menu (if closed), then click 'Buying'",
          disableInteraction: true
        },
        {
          element: document.querySelector('#sidebarToggle'),
          intro: "Click 'Next' to go to the Buying page",
          disableInteraction: true
        },
        {
          element: document.querySelector('#sidebarToggle'),
          intro: "Going to buying page"
        }
      ]
    };
    this.intro.setOptions(options);
    this.intro.start().onchange(() =>{
      if (this.intro._currentStep == "1") {
        document.getElementById('sidebarToggle').click();
      }else if (this.intro._currentStep == "2"){
        this.changePage();
      }
    });

  }

  changePage() {
    window.location.href = 'buying?multipage=true';
  }

}
