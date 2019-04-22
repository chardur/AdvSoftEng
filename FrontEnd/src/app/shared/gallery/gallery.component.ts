import { Component, OnInit } from '@angular/core';
import { NgxGalleryOptions, NgxGalleryImage, NgxGalleryAnimation } from 'ngx-gallery';
import {sanitizeResourceUrl} from '@angular/core/src/sanitization/sanitization';
import {DomSanitizer} from '@angular/platform-browser';
import {MatTableDataSource} from '@angular/material';
import {BuyerItem} from '../../models/buyer-item';
import {BuyingService} from '../../buying/buying.service';

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.css']
})
export class GalleryComponent implements OnInit {
  galleryOptions: NgxGalleryOptions[];
  galleryImages: NgxGalleryImage[];
  walmartButton =  '';
  buyerItems: BuyerItem[];
  products: any = [];
  temp: any = [];

  constructor(private buyingService: BuyingService) { }

  ngOnInit(): void {
    this.getProducts();
    this.populateGalleryImages();
    this.galleryOptions = [
      {
        'imageDescription': true,
        'imageAutoPlay': true,
        'imageAutoPlayPauseOnHover': true,
        'previewAutoPlay': false,
        'previewAutoPlayPauseOnHover': true,
        'thumbnailsAsLinks': true
      },
      { 'breakpoint': 500, 'width': '450px', 'height': '450px', 'thumbnailsColumns': 3 },
      { 'breakpoint': 300, 'width': '100%', 'height': '100px', 'thumbnailsColumns': 2 }
    ];


  }
  private populateGalleryImages() {
    console.log(this.products);
    this.galleryImages = [
      {
        // tslint:disable-next-line:max-line-length
        small: 'https://i5.walmartimages.com/asr/6a58d244-149d-4289-810e-a38cb1809782_1.db9222a98866370ca1692ea6de7ad850.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF',
        // tslint:disable-next-line:max-line-length
        medium: 'https://i5.walmartimages.com/asr/6a58d244-149d-4289-810e-a38cb1809782_1.db9222a98866370ca1692ea6de7ad850.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF',
        // tslint:disable-next-line:max-line-length
        big: 'https://i5.walmartimages.com/asr/6a58d244-149d-4289-810e-a38cb1809782_1.db9222a98866370ca1692ea6de7ad850.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF',
        description: 'Boys\' Long Sleeve Crew T-Shirt with Rib Cuffs',
        // tslint:disable-next-line:max-line-length
        url: 'http://c.affil.walmart.com/t/api01?l=http%3A%2F%2Faffil.walmart.com%2Fcart%2FaddToCart%3Fitems%3D45707349%7C1%26affp1%3D5ng3ElK8UpN4FAdJcQys4Bghacg8YfNH0-Huetnv0oo%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi'
      },
      {
        // tslint:disable-next-line:max-line-length
        small: 'https://i5.walmartimages.com/asr/bb331b76-5693-456f-bce1-57b01189baa3_1.920aa980e78a7a59dccf03deff759c94.jpeg?odnHeight=100&odnWidth=100&odnBg=ffffff',
        // tslint:disable-next-line:max-line-length
        medium: 'https://i5.walmartimages.com/asr/bb331b76-5693-456f-bce1-57b01189baa3_1.920aa980e78a7a59dccf03deff759c94.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff',
        // tslint:disable-next-line:max-line-length
        big: 'https://i5.walmartimages.com/asr/bb331b76-5693-456f-bce1-57b01189baa3_1.920aa980e78a7a59dccf03deff759c94.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff',
        description: 'Trim Nailcare Fingernail & Toenail Clippers',
        url: 'https://www.walmart.com/ip/Trim-Nailcare-Fingernail-Toenail-09625-Clippers-2-ct/14528578'
      },
      {
        // tslint:disable-next-line:max-line-length
        small: 'https://i5.walmartimages.com/asr/c533f391-4c9f-433f-813b-dc177950397d_1.f92d2bf14fdc83d728ca75f8ba229121.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF',
        // tslint:disable-next-line:max-line-length
        medium: 'https://i5.walmartimages.com/asr/c533f391-4c9f-433f-813b-dc177950397d_1.f92d2bf14fdc83d728ca75f8ba229121.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF',
        // tslint:disable-next-line:max-line-length
        big: 'https://i5.walmartimages.com/asr/c533f391-4c9f-433f-813b-dc177950397d_1.f92d2bf14fdc83d728ca75f8ba229121.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF',
        description: 'Voyager Proton Electric Scooter',
        // tslint:disable-next-line:max-line-length
        url: 'http://c.affil.walmart.com/t/api01?l=http%3A%2F%2Faffil.walmart.com%2Fcart%2FaddToCart%3Fitems%3D45707349%7C1%26affp1%3D5ng3ElK8UpN4FAdJcQys4Bghacg8YfNH0-Huetnv0oo%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi'
      },
      {
        // tslint:disable-next-line:max-line-length
        small: 'https://i5.walmartimages.com/asr/5652268b-ec67-4eb9-8768-0dc7d3a9354a_3.5506e228514ae2a327889ac57910feba.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF',
        // tslint:disable-next-line:max-line-length
        medium: 'https://i5.walmartimages.com/asr/5652268b-ec67-4eb9-8768-0dc7d3a9354a_3.5506e228514ae2a327889ac57910feba.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF',
        // tslint:disable-next-line:max-line-length
        big: 'https://i5.walmartimages.com/asr/5652268b-ec67-4eb9-8768-0dc7d3a9354a_3.5506e228514ae2a327889ac57910feba.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF',
        description: 'Sceptre 50" Class FHD (1080P) LED TV (X505BV-FSR)',
        // tslint:disable-next-line:max-line-length
        url: 'http://c.affil.walmart.com/t/api01?l=http%3A%2F%2Faffil.walmart.com%2Fcart%2FaddToCart%3Fitems%3D631796609%7C1%26affp1%3D5ng3ElK8UpN4FAdJcQys4Bghacg8YfNH0-Huetnv0oo%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi'
      },
      {
        // tslint:disable-next-line:max-line-length
        small: 'https://i5.walmartimages.com/asr/5df772df-cee8-406a-b136-72f3f3eac73a_1.9577e1db19d1b8566e1aa4709408109b.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF',
        // tslint:disable-next-line:max-line-length
        medium: 'https://i5.walmartimages.com/asr/5df772df-cee8-406a-b136-72f3f3eac73a_1.9577e1db19d1b8566e1aa4709408109b.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF',
        // tslint:disable-next-line:max-line-length
        big: 'https://i5.walmartimages.com/asr/5df772df-cee8-406a-b136-72f3f3eac73a_1.9577e1db19d1b8566e1aa4709408109b.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF',
        description: 'Coleman RoadTrip LXE Portable Propane Grill',
        // tslint:disable-next-line:max-line-length
        url: 'http://c.affil.walmart.com/t/api01?l=http%3A%2F%2Faffil.walmart.com%2Fcart%2FaddToCart%3Fitems%3D43980087%7C1%26affp1%3D5ng3ElK8UpN4FAdJcQys4Bghacg8YfNH0-Huetnv0oo%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi'
      },
      {
        // tslint:disable-next-line:max-line-length
        small: 'https://i5.walmartimages.com/asr/3bc5a57f-3660-4cb9-b29b-3d4f13a0fa98_1.2cd6cb184b8a8bfafef09f4fc9ba8f87.jpeg?odnHeight=100&odnWidth=100&odnBg=ffffff',
        // tslint:disable-next-line:max-line-length
        medium: 'https://i5.walmartimages.com/asr/3bc5a57f-3660-4cb9-b29b-3d4f13a0fa98_1.2cd6cb184b8a8bfafef09f4fc9ba8f87.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff',
        // tslint:disable-next-line:max-line-length
        big: 'https://i5.walmartimages.com/asr/3bc5a57f-3660-4cb9-b29b-3d4f13a0fa98_1.2cd6cb184b8a8bfafef09f4fc9ba8f87.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff',
        description: 'Great Value 18 oz Party Plastic Cups',
        // tslint:disable-next-line:max-line-length
        url: 'http://c.affil.walmart.com/t/api01?l=http%3A%2F%2Faffil.walmart.com%2Fcart%2FaddToCart%3Fitems%3D122270233%7C1%26affp1%3D5ng3ElK8UpN4FAdJcQys4Bghacg8YfNH0-Huetnv0oo%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi'
      },
    ];
  }
  getProducts() {
    this.products = [];
    this.buyingService.getBuyerItems(2).subscribe((data: {}) => {
      this.products = data;
      this.temp = [...this.products];
      console.log(this.products);
    });
  }
}
