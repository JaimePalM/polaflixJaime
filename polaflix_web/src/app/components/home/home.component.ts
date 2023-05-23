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

  constructor(private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.userId = params['userId'];
    });
    
    this.userService.getStartedSeries(this.userId).subscribe(startedSeries => {
      this.startedSeries = Object.values(startedSeries);
    })
    this.userService.getPendingSeries(this.userId).subscribe(pendingSeries => {
      this.pendingSeries = Object.values(pendingSeries);
    })
    this.userService.getFinishedSeries(this.userId).subscribe(finishedSeries => {
      this.finishedSeries = Object.values(finishedSeries);
    })
  }
}
