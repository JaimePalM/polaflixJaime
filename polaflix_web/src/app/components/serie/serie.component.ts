import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SerieService } from 'src/app/services/serie.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-serie',
  templateUrl: './serie.component.html',
  styleUrls: ['./serie.component.css']
})
export class SerieComponent implements OnInit {

  serie: any = {};
  views: any = {};
  currentSeason: number = 1;
  descriptionView: boolean[] = [];
  userId: number = 1;
  serieId: number = 1;
  errorMessage: string = "";

  constructor(private serieService: SerieService, private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    
    this.route.params.subscribe(params => {
      this.userId = params['userId'];
      this.serieId = params['serieId'];
    });

    this.serieService.getSerie(this.serieId).subscribe({
      next: serie => {
        this.serie = serie;
      },
      error: error => {
        if (error.status == 404) {
          this.errorMessage = "La serie no existe";
        } else if (error.status == 0) {
          this.errorMessage = "El servidor no está disponible";
        } else {
          this.errorMessage = "Error al obtener la serie";
          console.log(error);
        }
      }
    })

    this.userService.getViews(this.userId, this.serieId).subscribe({
      next: views => {
        this.views = views; 
      },
      error: error => {
        if (error.status == 404) {
          this.errorMessage = "La serie o el usuario no existen";
        } else if (error.status == 0) {
          this.errorMessage = "El servidor no está disponible";
        } else {
          this.errorMessage = "Error al obtener los capítulos vistos";
          console.log(error);
        }
      }
    })

    this.userService.getLastChapterViewed(this.userId, this.serieId).subscribe({
      next: response => {
        this.currentSeason = response.season.number;
      },
      error: error => {
        if (error.status == 404) {
          this.errorMessage = "La serie o el usuario no existen";
        } else if (error.status == 0) {
          this.errorMessage = "El servidor no está disponible";
        } else {
          this.errorMessage = "Error al obtener el último capítulo visto";
          console.log(error);
        }
      }
    });

    this.descriptionView = [];

  }

  sortedChapters(chapters: any[]): any[] {
    return chapters.sort((a, b) => a.number - b.number);
  }

  changeSeason(seasonNumber: number): void {
    this.currentSeason = seasonNumber;
  }

  showDescription(chapter: any): void {
    this.descriptionView[chapter.number - 1] = !this.descriptionView[chapter.number - 1];
  }

  viewChapter(serie: any, season: any, chapter: any): void {
    this.userService.viewChapter(this.userId, serie.id, season.number, chapter.number).subscribe({
      next: views => {
        this.views = views;
      },
      error: error => {
        if (error.status == 404) {
          this.errorMessage = "La serie, el usuario o el capítulo no existen";
        } else if (error.status == 0) {
          this.errorMessage = "El servidor no está disponible";
        } else {
          this.errorMessage = "Error al guardar el capítulo como visto";
          console.log(error);
        }
      }
    });
  }

}
