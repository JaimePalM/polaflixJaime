import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SerieService } from 'src/app/services/serie.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.css']
})
export class BillComponent implements OnInit {

  bills: any[] = [];
  currentMonth: string = '';
  currentBill: any = {};

  constructor(private serieService: SerieService, private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.userService.getBills(1).subscribe(bills => {
      this.bills = bills;
      this.currentMonth = new Date().toISOString().slice(0, 8) + '01';
      this.currentBill = this.bills.find(bill => bill.date.slice(0, 7) == this.currentMonth);
    })

  }

  previousMonthAvailable(): boolean {
    const previousMonth = new Date(this.currentMonth);
    previousMonth.setMonth(previousMonth.getMonth() - 1);
    return this.bills.some(bill => bill.monthBilled === previousMonth.toISOString().slice(0, 7));
  }

    nextMonthAvailable(): boolean {
    const nextMonth = new Date(this.currentMonth);
    nextMonth.setMonth(nextMonth.getMonth() + 1);
    return this.bills.some(bill => bill.monthBilled === nextMonth.toISOString().slice(0, 7));
  }

  changeMonth(newMonth: string): void {
    this.currentMonth = newMonth;
  }

}
