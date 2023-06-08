import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SerieService } from 'src/app/services/serie.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-series',
  templateUrl: './series.component.html',
  styleUrls: ['./series.component.css']
})
export class SeriesComponent {

  alphabet: string[] = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'.split('');
  series: any[] = [];
  allSeries: any[] = [];
  pendingSeries: any[] = [];
  selectedInitial: string = '';
  searchText: string = '';
  descriptionView: boolean[] = [];
  userId: number = 1;
  errorMessage: string = "";

  constructor(private serieService: SerieService, private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.userId = params['userId'];
    });

    this.serieService.getSeries().subscribe({
      next: series => {
        this.series = series;
        this.allSeries = series;
        this.sortSeries();
      }, 
      error: error => {
        if (error.status == 0) {
          this.errorMessage = "El servidor no está disponible";
        } else {
          this.errorMessage = "Error al obtener las series";
          console.log(error);
        }
      }
    });
  }

  changeInitial(initial: string): void {
    this.selectedInitial = initial;
    this.series = this.allSeries.filter(serie => serie.title.charAt(0) === initial);
  }

  clearFilter(): void {
    this.selectedInitial = '';
    this.series = this.allSeries;
  }

  search(searchText: string) {
    console.log(searchText);
    this.series = this.allSeries.filter(serie => 
      serie.title.toLowerCase().includes(searchText.toLowerCase())
    );
    this.sortSeries();
    this.series.forEach(serie => {
      serie.exactMatch = serie.title.toLowerCase() === searchText.toLowerCase();
    });
  }

  addPending(serie: any) {
    this.userService.addPendingSerie(this.userId, serie.id).subscribe({
      next: pendingSeries => {
        this.pendingSeries = pendingSeries;
      },
      error: error => {
        if (error.status == 404) {
          this.errorMessage = "La serie o el usuario no existen";
        } else if (error.status == 0) {
          this.errorMessage = "El servidor no está disponible";
        } else {
          this.errorMessage = "Error al añadir la serie a pendientes";
          console.log(error);
        }
      }
    });
  }

  showDescription(serie: any): void {
    this.descriptionView[serie.id] = !this.descriptionView[serie.id];
  }

  sortSeries(): void {
    if (Array.isArray(this.series)) {
      this.series = this.series.sort((a, b) => {
        if (a.title < b.title) {
          return -1;
        }
        if (a.title > b.title) {
          return 1;
        }
        return 0;
      });
    }
  }

  isSeriePending(serie: any): boolean {
    return this.pendingSeries.some((ps: { id: any; }) => ps.id === serie.id);
  }

}