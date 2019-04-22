import {Component, OnInit, SimpleChanges} from '@angular/core';
import * as introJs from 'intro.js/intro.js';

@Component({
  selector: 'app-sellerguide',
  templateUrl: './sellerguide.component.html',
  styleUrls: ['./sellerguide.component.css']
})
export class SellerguideComponent implements OnInit {

  intro = introJs();

  constructor() { }

  ngOnInit() {
  }

  startIntro(){
    let options = {

      steps: [
        {
          element: document.querySelector('#sidebarToggle'),
          intro: "Click the Menu icon to open the side menu (if closed), then click 'Selling'",
          disableInteraction: true
        },
        {
          element: document.querySelector('#sidebarToggle'),
          intro: "Click 'Next' to go to the Selling page",
          disableInteraction: true
        },
        {
          element: document.querySelector('#sidebarToggle'),
          intro: "Going to seller page"
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
    window.location.href = 'inventory?multipage=true';
  }
}
