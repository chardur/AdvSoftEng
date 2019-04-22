import { Injectable, NgModule } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
    constructor(private router: Router) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const user = localStorage.getItem('user');
        let newRequest = request.clone();
        if (user) {
          const token = JSON.parse(user).token;
            newRequest = request.clone({
                setHeaders: {
                    Authorization: token
                }
            });
        }

        // Temporary hardcoding
        newRequest = request.clone({
            setHeaders: {
                Authorization: 'ImALittleTeapot'
            }
        });

        return next.handle(newRequest);
    }

    handleTokenError(error: HttpErrorResponse) {
        if (error.status === 401 || error.status === 0) {
            // What we do here is tbd
            // localStorage.clear();
            // this.router.navigateByUrl('/Login');
        }
    }
}

@NgModule({
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: HttpRequestInterceptor,
            multi: true
        }
    ]
})
export class HttpInterceptorModule { }
