import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { EventoService } from '../../../../core/services/evento.service';
import { Evento } from '../../../../shared/models/evento.model';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-evento-list',
  standalone: false,
  templateUrl: './evento-list.component.html',
  styleUrls: ['./evento-list.component.scss']
})
export class EventoListComponent implements OnInit {
  displayedColumns: string[] = ['titulo', 'dataHoraEvento', 'local', 'actions'];
  dataSource: Evento[] = [];
  totalElements = 0;
  pageSize = 10;
  pageIndex = 0;
  isLoading = false;

  constructor(private eventoService: EventoService, private cdr: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.loadEventos();
  }

  loadEventos(): void {
    this.isLoading = true;
    this.cdr.detectChanges();
    
    this.eventoService.getEventos(this.pageIndex, this.pageSize).subscribe({
      next: (response) => {
        this.dataSource = response.content;
        this.totalElements = response.totalElements;
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      complete: () => {
        console.log('loadEventos: Observable COMPLETED.');
      }
    });
  }

  handlePageEvent(evento: PageEvent): void {
    this.pageIndex = evento.pageIndex;
    this.pageSize = evento.pageSize;
    this.loadEventos();
  }

  deleteEvento(id: number): void {
    if (confirm('Tem certeza que quer excluir o evento?')) {
      this.eventoService.deleteEvento(id).subscribe(() => {
        this.loadEventos();
      });
    }
  }
}