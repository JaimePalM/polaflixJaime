import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component'; 
import { HomeComponent } from './components/home/home.component';
import { SerieComponent } from './components/serie/serie.component';
import { SeriesComponent } from './components/series/series.component';
import { BillComponent } from './components/bill/bill.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'serie/:serieId', component: SerieComponent },
  { path: 'series', component: SeriesComponent },
  { path: 'bills', component: BillComponent },
  { path: '**', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
