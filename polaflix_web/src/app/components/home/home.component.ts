import { Component, OnInit } from '@angular/core';
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

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getStartedSeries(1).subscribe(startedSeries => {
      this.startedSeries = Object.values(startedSeries);
    })
    this.userService.getPendingSeries(1).subscribe(pendingSeries => {
      this.pendingSeries = Object.values(pendingSeries);
    })
    this.userService.getFinishedSeries(1).subscribe(finishedSeries => {
      this.finishedSeries = Object.values(finishedSeries);
    })
  }
}
