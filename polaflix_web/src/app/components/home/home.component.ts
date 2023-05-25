import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  startedSeries: any = {};
  pendingSeries: any = {};
  finishedSeries: any = {};
  userId: number = 1;
  errorMessage: string = "";

  constructor(private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.userId = params['userId'];
    });
    
    this.userService.getUserById(this.userId).subscribe({
      next: user => {
        this.startedSeries = Object.values(user.startedSeries);
        this.pendingSeries = Object.values(user.pendingSeries);
        this.finishedSeries = Object.values(user.finishedSeries);
      },
      error: error => {
        if (error.status == 404) {
          this.errorMessage = "El usuario no existe";
        } else if (error.status == 0) {
          this.errorMessage = "El servidor no está disponible";
        } else {
          this.errorMessage = "Error al obtener el usuario";
          console.log(error);
        }
      }
    })
  }
}
