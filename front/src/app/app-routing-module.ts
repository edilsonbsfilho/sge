import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'events', loadChildren: () => import('./features/evento/evento.module').then(m => m.EventoModule) },
  { path: '', redirectTo: '/events', pathMatch: 'full', title: 'Home' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
