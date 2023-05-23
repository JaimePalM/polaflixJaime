import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component'; 
import { HomeComponent } from './components/home/home.component';
import { SerieComponent } from './components/serie/serie.component';
import { SeriesComponent } from './components/series/series.component';
import { BillComponent } from './components/bill/bill.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'users/:userId/home', component: HomeComponent },
  { path: 'users/:userId/serie/:serieId', component: SerieComponent },
  { path: 'users/:userId/series', component: SeriesComponent },
  { path: 'users/:userId/bills', component: BillComponent },
  { path: '**', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
