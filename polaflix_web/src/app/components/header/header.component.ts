import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  userId: number = 1;
  username: string = "";

  constructor(private userService: UserService, private route: ActivatedRoute) {}

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.userId = params['userId'];
    });

    this.userId = 1;

    this.userService.getUserById(this.userId).subscribe({
      next: response => {
        this.username = response.username;
      },
      error: error => {
        if (error.status == 404) {
          console.log("User not found");
        }
        else
          console.log(error);
      }
    });

  }

} 
