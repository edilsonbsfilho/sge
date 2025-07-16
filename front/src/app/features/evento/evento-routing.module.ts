import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EventoListComponent } from './components/evento-list/evento-list.component';
import { EventoFormComponent } from './components/evento-form/evento-form.component';
import { EventoDetailComponent } from './components/evento-detail/evento-detail.component';

const routes: Routes = [
  { path: '', component: EventoListComponent, title: 'Lista de Eventos' },
  { path: 'new', component: EventoFormComponent, title: 'Novo Evento' },
  { path: ':id/edit', component: EventoFormComponent, title: 'Editar Evento' },
  { path: ':id', component: EventoDetailComponent, title: 'Detalhes do Evento' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventoRoutingModule { }