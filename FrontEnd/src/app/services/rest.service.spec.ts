import { TestBed, inject } from '@angular/core/testing';

import { RESTService } from './rest.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('RESTService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [RESTService]
    });
  });

  it('should be created', inject([RESTService], (service: RESTService) => {
    expect(service).toBeTruthy();
  }));
});
