import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EventoService } from '../../../../core/services/evento.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Evento } from './../../../../shared/models/evento.model';

@Component({
  selector: 'app-evento-form',
  standalone: false,
  templateUrl: './evento-form.component.html',
  styleUrls: ['./evento-form.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class EventoFormComponent implements OnInit {
  eventoForm: FormGroup;
  isEditMode = false;
  eventoId: number | null = null;
  isLoading = false;

  constructor(
    private fb: FormBuilder,
    private eventoService: EventoService,
    private route: ActivatedRoute,
    private router: Router
  ) {  
    this.eventoForm = this.fb.group({
      titulo: ['', [Validators.required, Validators.maxLength(100)]],
      descricao: ['', [Validators.required, Validators.maxLength(1000)]],
      dataHoraEvento: [new Date(), [Validators.required]],
      local: ['', [Validators.required, Validators.maxLength(200)]]
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.isEditMode = true;
        this.eventoId = +params['id'];
        this.loadEvento();
      }
    });
  }

  loadEvento(): void {
    if (this.eventoId) {
      this.isLoading = true;
      this.eventoService.getEventoById(this.eventoId).subscribe({
        next: (evento) => {
          this.eventoForm.patchValue(evento);
          this.isLoading = false;
        },
        error: () => {
          this.isLoading = false;
        }
      });
    }
  }

  onSubmit(): void {
    if (this.eventoForm.valid) {
      this.isLoading = true;
      const evento: Evento = {
          ...this.eventoForm.value,
          eventDateTime: this.eventoForm.value.dataHoraEvento.toISOString()
        };
      
      const request = this.isEditMode && this.eventoId
        ? this.eventoService.updateEvento({id: this.eventoId, ...evento})
        : this.eventoService.createEvento(evento);

      request.subscribe({
        next: () => {
          this.isLoading = false;
          this.router.navigate(['/events']);
        },
        error: () => {
          this.isLoading = false;
        }
      });
    }
  }
}